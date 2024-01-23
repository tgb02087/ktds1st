import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostServiceImpl postService = new PostServiceImpl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1: 게시글 작성, 2: 게시글 조회, 3: 모든 게시글 보기, 4: 게시글 수정, 5: 게시글 삭제, 6: 종료");
            System.out.println("옵션을 선택하세요: ");
            String input_Select = scanner.nextLine();
            if(input_Select.equals("1")) {
                System.out.println("게시글 제목을 입력하세요: ");
                String input_title = scanner.nextLine();
                System.out.println("게시글 내용을 입력하세요: ");
                String input_content = scanner.nextLine();
                System.out.println("작성자를 입력하세요 : ");
                String input_author = scanner.nextLine();
                postService.create(input_title, input_content, input_author);
                System.out.println("게시글 작성이 완료되었습니다.");
            }
            else if(input_Select.equals("2")) {
                System.out.println("게시글 번호를 입력하세요: ");
                String input_index = scanner.nextLine();
                Post result = postService.read(Integer.parseInt(input_index));
                System.out.println("제목 | 내용 | 작성자 | 작성일");
                System.out.println(result.getTitle() + " " + result.getContent() + " " + result.getAuthor() + " " + result.getDate());
            }
            else if(input_Select.equals("3")) {
                HashMap<Integer, Post> resultMap = postService.readAll();
                System.out.println("제목 | 내용 | 작성자 | 작성일");
                for(int index : resultMap.keySet()) {
                    Post result = resultMap.get(index);
                    System.out.println(result.getTitle() + " " + result.getContent() + " " + result.getAuthor() + " " + result.getDate());
                }
            }
            else if(input_Select.equals("4")) {
                System.out.println("수정할 게시글번호를 입력하세요: ");
                String input_index = scanner.nextLine();
                System.out.println("수정할 게시글 제목을 입력하세요: ");
                String input_title = scanner.nextLine();
                System.out.println("수정할 게시글 내용을 입력하세요: ");
                String input_content = scanner.nextLine();
                System.out.println("수정할 작성자를 입력하세요 : ");
                String input_author = scanner.nextLine();
                postService.update(Integer.parseInt(input_index), input_title, input_content, input_author);
                System.out.println("게시글이 수정되었습니다.");
            }
            else if(input_Select.equals("5")) {
                System.out.println("삭제할 게시글 번호를 입력하세요.");
                String input_index = scanner.nextLine();
                postService.delete(Integer.parseInt(input_index));
                System.out.println("게시글이 삭제되었습니다.");
            }
            else if(input_Select.equals("6")) {
                System.out.println("프로그램 종료");
                return;
            }
            else {
                System.out.println("유효한 명령어가 아닙니다.");
            }
        }

    }
}
