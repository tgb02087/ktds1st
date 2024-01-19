import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<Member> memberList = new ArrayList<Member>();

        try {
            // Member.txt 파일을 읽어오는 코드
            String memberFile = readMemberFile();
            // Member.txt 파일의 내용을 한 줄씩 읽어와서 Member 객체를 생성
            String[] memberArray = memberFile.split("\n");
            for (int i = 0; i < memberArray.length; i++) {
                String[] memberInfoArray = memberArray[i].split(",");
                String name = memberInfoArray[0];
                String phoneNumber = memberInfoArray[1];
                String id = memberInfoArray[2];
                String password = memberInfoArray[3];
                Member member = new Member(name, phoneNumber, id, password);
                memberList.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Book> bookList = new ArrayList<Book>();

        try {
            // Book.txt 파일을 읽어오는 코드
            String bookFile = readBookFile();
            // Book.txt 파일의 내용을 한 줄씩 읽어와서 Book 객체를 생성
            String[] bookArray = bookFile.split("\n");
            for (int i = 0; i < bookArray.length; i++) {
                String[] bookInfoArray = bookArray[i].split(",");
                String title = bookInfoArray[0];
                String author = bookInfoArray[1];
                String status = bookInfoArray[2];
                String returnDate = bookInfoArray[3];
                Book book = new Book(title, author, status, returnDate);
                bookList.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        Scanner sc = new Scanner(System.in);

        boolean isLoginSuccess = false;

        while (isLoginSuccess == false) {
        // 로그인할지 회원가입할지 선택하는 화면
        System.out.println("1) 로그인 | 2) 회원가입");
        int select = sc.nextInt();


            if (select == 1) {
                // 로그인 화면
                System.out.println("아이디와 비밀번호를 쉼표를 기준으로 입력해주세요.");
                String idAndPassword = sc.next();
                String[] idAndPasswordArray = idAndPassword.split(",");
                String id = idAndPasswordArray[0];
                String password = idAndPasswordArray[1];

                // 로그인 성공 여부를 확인하는 코드
                // Member list에 id가 존재하는지 확인
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getId().equals(id)) {
                        if (memberList.get(i).getPassword().equals(password)) {
                            System.out.println("로그인에 성공하였습니다.");
                            isLoginSuccess = true;

                            System.out.println("1) 도서 대출하기 | 2) 도서 목록 확인하기");
                            int select2 = sc.nextInt();

                            if (select2 == 1) {
                                // 도서 대출하기
                                System.out.println("도서 대출하기");
                                System.out.println("대출하고 싶은 도서를 입력해주세요.");
                                sc.nextLine();
                                String title = sc.nextLine();
                                // 도서 대출 성공 여부
                                boolean isBookBorrowSuccess = false;
                                // 도서 대출 성공 여부를 확인하는 코드
                                // Book list에 title이 존재하는지 확인
                                for (int j = 0; j < bookList.size(); j++) {
                                    if (bookList.get(j).getTitle().equals(title)) {
                                        if (bookList.get(j).getStatus().equals("X")) {
                                            System.out.println(title + "(이)가 대출되었습니다.");
                                            bookList.get(j).setStatus("O");
                                            // 도서 대출 성공 시, 반납예정일을 오늘+14일로 설정
                                            bookList.get(j).setReturnDate(LocalDate.now().plusDays(14).toString());
                                            // 도서 대출 성공 시, 도서 대출 상태를 Book.txt 파일에 저장
                                            // Book.txt 파일에 도서 대출 상태를 덮어쓰는 코드
                                            BufferedWriter bw = new BufferedWriter(new FileWriter("Book.txt"));
                                            for (int k = 0; k < bookList.size(); k++) {
                                                bw.write(bookList.get(k).getTitle() + "," + bookList.get(k).getAuthor() + "," + bookList.get(k).getStatus() + "," + bookList.get(k).getReturnDate());
                                                bw.newLine();
                                            }
                                            bw.flush();
                                            bw.close();
                                            isBookBorrowSuccess = true;
                                            break;
                                        } else {
                                            System.out.println(title + "(이)가 대출중입니다.");
                                            break;
                                        }
                                    }
                                }
                                if (isBookBorrowSuccess == false) {
                                    System.out.println("도서 대출에 실패하였습니다.");
                                }
                            } else if (select2 == 2) {
                                // 도서 목록 확인하기
                                System.out.println("도서 목록 확인하기");
                                System.out.println("도서명, 저자, 대출여부, 반납예정일");
                                for (int j = 0; j < bookList.size(); j++) {
                                    System.out.println(bookList.get(j).getTitle() + ", " + bookList.get(j).getAuthor() + ", " + bookList.get(j).getStatus() + ", " + bookList.get(j).getReturnDate());
                                }
                            } else {
                                System.out.println("잘못된 입력입니다.");
                            }

                            break;
                        } else {
                            System.out.println("비밀번호가 틀렸습니다.");
                            break;
                        }
                    }
                }

            } else if (select == 2) {
                // 회원가입 화면
                System.out.println("회원가입 화면");
                System.out.println("이름, 전화번호, 아이디와 비밀번호를 쉼표를 기준으로 입력해주세요.");
                String nameAndPhoneNumberAndIdAndPassword = sc.next();
                String[] nameAndPhoneNumberAndIdAndPasswordArray = nameAndPhoneNumberAndIdAndPassword.split(",");

                String name = nameAndPhoneNumberAndIdAndPasswordArray[0];
                String phoneNumber = nameAndPhoneNumberAndIdAndPasswordArray[1];
                String id = nameAndPhoneNumberAndIdAndPasswordArray[2];
                String password = nameAndPhoneNumberAndIdAndPasswordArray[3];

                // 회원가입 성공 여부
                boolean isSignUpSuccess = false;

                // 회원가입 성공 여부를 확인하는 코드
                // Member list에 id가 존재하는지 확인
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getId().equals(id)) {
                        System.out.println("이미 존재하는 아이디입니다.");
                        break;
                    } else {
                        // Member 객체 생성
                        Member member = new Member(name, phoneNumber, id, password);
                        memberList.add(member);

                        // Member 객체를 Member.txt 파일에 저장
                        // Member.txt 파일에 Member 객체를 저장하는 코드
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Member.txt", true));

                        bw.write(member.getName() + "," + member.getPhoneNumber() + "," + member.getId() + "," + member.getPassword());
                        bw.newLine();
                        bw.flush();
                        bw.close();

                        isSignUpSuccess = true;
                        System.out.println("회원가입에 성공하였습니다.");
                        break;
                    }
                }


            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // Member.txt 파일을 읽어오는 메소드
    public static String readMemberFile() throws IOException {
        // Member.txt 파일을 읽어오는 코드
        BufferedReader br = new BufferedReader(new FileReader("Member.txt"));
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {
            // 읽어온 파일의 내용을 ArrayList에 저장
            result += line + "\n";
        }
        return result;
    }

    // Book.txt 파일을 읽어오는 메소드
    public static String readBookFile() throws IOException {
        // Book.txt 파일을 읽어오는 코드
        BufferedReader br = new BufferedReader(new FileReader("Book.txt"));
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {
            // 읽어온 파일의 내용을 ArrayList에 저장
            result += line + "\n";
        }
        return result;
    }

    // 로그인 성공 시, 도서를 대출하는 메서드




}
