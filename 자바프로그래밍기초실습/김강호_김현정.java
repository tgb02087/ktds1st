import java.time.LocalDate;
import java.util.*;
import java.io.*;

class User {
    String name;
    String tel;
    String id;
    String pw;
    int borrowCount = 3;
    int reservationCount = 2;
    public User(String name, String tel, String id, String pw) {
        this.name = name;
        this.tel = tel;
        this.id = id;
        this.pw = pw;
    }
}

class Book {
    String title;
    String author;
    boolean is_booked;
    String date = "-";

    String userId = "-";

    String reserve_userId = "-";

    public Book(String title, String author, boolean is_booked) {
        this.title = title;
        this.author = author;
        this.is_booked = is_booked;
    }

    public Book(String title, String author, boolean is_booked, String date, String userId) {
        this.title = title;
        this.author = author;
        this.is_booked = is_booked;
        this.date = date;
        this.userId = userId;
    }
}

public class Project {
    public static void main(String[] args) throws Exception{
        List<Book> booklist = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        userList.add(new User("김강호", "010", "aa", "1234"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        booklist.add(new Book("자바 프로그래밍", "김민수", false));
        booklist.add(new Book("스프링 처음하기", "이재한", false));
        booklist.add(new Book("자바스크립트", "한민우", false));

        //C:\Users\KTDS\Desktop
        String filePath = "C:\\Users\\KTDS\\Desktop\\도서목록.txt";
        try {
            BufferedReader txtBr = new BufferedReader(new FileReader(filePath));
            String str;
            while((str = txtBr.readLine()) != null){
                String[] str2 = str.split(",");
                booklist.add(new Book(str2[0].trim(), str2[1].trim(), Boolean.parseBoolean(str2[2].trim()),str2[3].trim(), str2[4].trim()));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        String rootPW = "1234";

        boolean is_login = false;

        int nowUserIndex = -1;

        //관리자 모드
        while (true) {
            System.out.println("1) 관리자모드 | 2) 참가자모드 | 3) 종료");
            String admin_answer = br.readLine();
            if(admin_answer.equals("1")) {
                System.out.println("관리자 비밀번호를 입력하세요.");
                String admin_inputPW = br.readLine();
                if(rootPW.equals(admin_inputPW)) {
                    System.out.println("관리자모드로 접속했습니다.");
                    //관리자 기능
                    while (true) {
                        System.out.println("1) 도서 추가 | 2) 도서 대출자 확인");
                        String admin_select = br.readLine();
                        if(admin_select.equals("1")) {
                            System.out.println("도서명과 저자를 쉼표를 기준으로 입력해주세요.");
                            String[] bookInfo = br.readLine().split(",");
                            booklist.add(new Book(bookInfo[0].trim(), bookInfo[1].trim(), false));
                            System.out.println("새로운 도서가 추가되었습니다.");
                        }
                        else if(admin_select.equals("2")) {
                            System.out.println("도서명         | 저자    | 대출여부    | 반납예정일     | 대출한 유저");
                            for(int i=0; i<booklist.size(); i++) {
                                System.out.println(booklist.get(i).title+"  |"+booklist.get(i).author+"  |"+booklist.get(i).is_booked+"  |"+booklist.get(i).date+"  |"+booklist.get(i).userId);
                            }
                        }
                    }
                }
                else {
                    System.out.println("비밀번호가 틀립니다.");
                }
            }
            else if(admin_answer.equals("2")){
                //1. 로그인
                //2. 회원가입
                while (!is_login) {
                    System.out.println("1) 로그인 | 2) 회원가입");
                    String answer = br.readLine();
                    if(answer.equals("1")) {
                        System.out.println("아이디와 비밀번호를 쉼표를 기준으로 입력해주세요.");
                        String[] userInfo = br.readLine().split(",");
                        for(int i=0; i<userList.size(); i++) {
                            if(userList.get(i).id.equals(userInfo[0].trim()) && userList.get(i).pw.equals(userInfo[1].trim())) {
                                is_login = true;
                                nowUserIndex = i;
                                break;
                            }
                        }
                        if(is_login) {
                            System.out.println("로그인이 완료되었습니다.");
                        }
                        else {
                            System.out.println("로그인에 실패했습니다.");
                        }
                    }
                    else if(answer.equals("2")) {
                        System.out.println("이름, 전화번호, 아이디와 비밀번호를 쉼표를 기준으로 입력해주세요.");
                        String[] user = br.readLine().split(",");
                        userList.add(new User(user[0].trim(), user[1].trim(), user[2].trim(), user[3].trim()));
                    }
                    else {
                        System.out.println("잘못된 형식입니다. 다시입력해주세요.");
                    }
                }
                //로그인 완료
                while (true) {
                    System.out.println("1) 도서 대출하기 | 2) 도서 목록 확인하기 | 3) 내가 빌린 도서확인 | 4) 도서 검색");
                    String book_answer = br.readLine();
                    if(book_answer.equals("1")) {
                        System.out.println("대출하고 싶은 도서를 입력해주세요.");
                        String bookTitle = br.readLine();
                        boolean flag = false;

                        for(int i=0; i<booklist.size(); i++) {
                            if(booklist.get(i).title.equals(bookTitle)) {
                                flag = true;
                                //이미 대출한경우 - 예약여부
                                if(booklist.get(i).is_booked) {
                                    System.out.println(bookTitle + " 은 이미 대출중입니다.");

                                    if(booklist.get(i).reserve_userId.equals("-")) {
                                        int count = userList.get(nowUserIndex).reservationCount;
                                        if(count==0) {
                                            System.out.println(userList.get(nowUserIndex).name+"님의 남은예약횟수가 없습니다.");
                                            break;
                                        }
                                        System.out.println(userList.get(nowUserIndex).name+"님의 남은예약횟수는 "+count+"(회) 입니다. "+"예약하시겠습니까? 1)예 | 2)아니오");
                                        String inputReserve = br.readLine();
                                        if(inputReserve.equals("1")) {
                                            booklist.get(i).reserve_userId = userList.get(nowUserIndex).id;
                                            userList.get(nowUserIndex).reservationCount -=1;
                                            System.out.println("예약이 완료되었습니다.");
                                        }
                                        else if(inputReserve.equals("2")) {
                                            System.out.println("예약을 취소했습니다.");
                                        }
                                        else {
                                            System.out.println("잘못된 형식입니다. 다시입력해주세요.");
                                        }
                                    }
                                    else {
                                        System.out.println("다른 유저가 이미 예약한 도서입니다.");
                                    }
                                }
                                // 대출하기
                                else {
                                    if(userList.get(nowUserIndex).borrowCount!=0) {
                                        booklist.get(i).date = LocalDate.now().plusDays(14).toString();
                                        booklist.get(i).is_booked = true;
                                        booklist.get(i).userId = userList.get(nowUserIndex).id;
                                        userList.get(nowUserIndex).borrowCount -=1;
                                        System.out.println(bookTitle + "(이)가 대출되었습니다." + " || 남은 잔여횟수 : "+userList.get(nowUserIndex).borrowCount);
                                    }
                                    else {
                                        System.out.println("대출가능 횟수를 모두 사용했습니다.");
                                    }
                                }
                            }
                        }
                        if (!flag) {
                            System.out.println("없는 도서명 입니다.");
                        }
                    }
                    else if(book_answer.equals("2")) {
                        System.out.println("도서명         | 저자    | 대출여부    | 반납예정일     |");
                        for(int i=0; i<booklist.size(); i++) {
                            System.out.println(booklist.get(i).title+"  |"+booklist.get(i).author+"  |"+booklist.get(i).is_booked+"  |"+booklist.get(i).date+"  |");
                        }
                    }
                    else if(book_answer.equals("3")) {
                        System.out.println("도서명         | 저자    | 대출여부    | 반납예정일     |");
                        for(int i=0; i<booklist.size(); i++) {
                            if(booklist.get(i).userId.equals(userList.get(nowUserIndex).id)) {
                                System.out.println(booklist.get(i).title+"  |"+booklist.get(i).author+"  |"+booklist.get(i).is_booked+"  |"+booklist.get(i).date+"  |");
                            }
                        }
                    }
                    else if(book_answer.equals("4")) {
                        boolean flag = false;
                        System.out.println("검색할 도서명을 입력해주세요.");
                        String bookInputTitle = br.readLine();
                        for(int i=0; i<booklist.size(); i++) {
                            if(booklist.get(i).title.equals(bookInputTitle)) {
                                System.out.println("도서명         | 저자    | 대출여부    | 반납예정일     |");
                                System.out.println(booklist.get(i).title+"  |"+booklist.get(i).author+"  |"+booklist.get(i).is_booked+"  |"+booklist.get(i).date+"  |");
                                flag = true;
                                break;
                            }
                        }
                        if(!flag) {
                            System.out.println("없는 도서명입니다.");
                        }
                    }
                    else {
                        System.out.println("잘못된 형식입니다. 다시입력해주세요.");
                    }

                }
            }
            else if(admin_answer.equals("3")){
                System.out.println("프로그램 종료.");
                break;
            }
            else {
                System.out.println("잘못된 형식입니다. 다시입력해주세요.");
            }
        }
    }
}
