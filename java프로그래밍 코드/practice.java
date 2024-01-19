import java.time.LocalDate;
import java.util.*;

class Book {
    String bookName;
    String bookAuthor;
    String bookCheck;
    LocalDate bookDate;
}

class User {r
    String name;
    String number;
    String userPassword;

}

public class practice {
    public static void main(String[] args) {

        Map<String, Book> books = new HashMap<>();
        Book book1 = new Book();
        book1.bookName = "자바 프로그래밍";
        book1.bookAuthor = "김민수";
        book1.bookCheck = "O";
        book1.bookDate = LocalDate.of(2000, 3, 11);
        books.put(book1.bookName, book1);

        Book book2 = new Book();
        book2.bookName = "스프링 처음하기";
        book2.bookAuthor = "이재한";
        book2.bookCheck = "X";
        books.put(book2.bookName, book2);

        Map<String, User> users = new HashMap<>();



        while(true) {
            System.out.println("1)로그인 | 2) 회원가입");
            Scanner input = new Scanner(System.in);
            String inputString = input.nextLine();

            if (inputString.equals("1")) {
                System.out.println("아이디와 비밀번호를 쉼표 기준으로 입력해주세요");
                String loginString = input.nextLine();

                String[] userSplit = loginString.split(",");

                if (users.containsKey(userSplit[0])) {
                    if(users.get(userSplit[0]).userPassword.equals(userSplit[1])){
                        while(true) {
                            System.out.println("1) 도서 대출하기 | 2) 도서 목록 확인하기 | 3) 도서 검색하기 | 4) 로그아웃");
                            String mainString = input.nextLine();

                            if (mainString.equals("1")) {
                                System.out.println("대출하고 싶은 도서를 입력해주세요.");
                                String bookString = input.nextLine();
                                if (books.containsKey(bookString)) {

                                    if (books.get(bookString).bookCheck.equals("X")) {
                                        System.out.println("대출되었습니다.");
                                        books.get(bookString).bookCheck = "O";
                                        books.get(bookString).bookDate = LocalDate.now().plusDays(14);
                                    }
                                    else {
                                        System.out.println("이미 대출 중입니다.");
                                    }

                                }
                                 else {
                                    System.out.println("책이 없습니다.");
                                }
                            } else if (mainString.equals("2")) {
                                Set<String> keySet = books.keySet();
                                System.out.println("도서명, 저자, 대출여부, 반납예정일");
                                for (String key : keySet) {
                                    System.out.println(books.get(key).bookName + "," + books.get(key).bookAuthor + "," + books.get(key).bookCheck + "," + books.get(key).bookDate);

                                }
                            }
                            else if (mainString.equals("3")) {
                                System.out.println("검색할 도서를 입력해주세요.");
                                String bookSearch = input.nextLine();
                                if (books.containsKey(bookSearch)) {
                                    System.out.println(books.get(bookSearch).bookName + "," + books.get(bookSearch).bookAuthor + "," + books.get(bookSearch).bookCheck + "," + books.get(bookSearch).bookDate);
                                }
                                else{
                                    System.out.println("검색한 도서는 없습니다.");
                                }

                            }

                            else if (mainString.equals("4")) {
                                break;
                            }
                        }
                    }
                }


            } else if (inputString.equals("2")) {
                System.out.println("이름, 전화번호, 아이디와 비밀번호를 쉼표 기준으로 입력해주세요");
                String signString = input.nextLine();
                String[] userSplit = signString.split(",");
                User user = new User();

                user.name = userSplit[0];
                user.number = userSplit[1];
                user.userPassword = userSplit[3];

                users.put(userSplit[2], user);


            }
        }
    }
}

