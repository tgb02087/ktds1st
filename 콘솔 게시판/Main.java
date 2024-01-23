import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PostService postService = new PostServiceImpl(new InMemoryPostRepository());

        while (true) {
            System.out.println("1: 게시글 작성, 2: 게시글 조회, 3: 모든 게시글 보기, 4: 게시글 수정, 5: 게시글 삭제, 6: 종료");
            System.out.print("옵션을 선택하세요: ");
            int option = sc.nextInt();

            sc.nextLine();

            if (option == 1) {
                System.out.print("제목을 입력하세요: ");
                String title = sc.nextLine();
                System.out.print("내용을 입력하세요: ");
                String content = sc.nextLine();
                System.out.print("작성자를 입력하세요: ");
                String author = sc.nextLine();
                postService.create(title, content, author);
            } else if (option == 2) {
                System.out.print("조회할 게시글 번호를 입력하세요: ");
                int id = sc.nextInt();
                Post post = postService.read(id);
                System.out.println("제목 | 내용 | 작성자 | 작성일");
                System.out.println(post.getTitle() + " | " + post.getContent() + " | " + post.getAuthor() + " | " + post.getDate());
            } else if (option == 3) {
                System.out.println("제목 | 내용 | 작성자 | 작성일");
                postService.readAll().forEach((k, v) -> System.out.println(v.getTitle() + " | " + v.getContent() + " | " + v.getAuthor() + " | " + v.getDate() + " | " + k));
            } else if (option == 4) {
                System.out.print("수정할 게시글 번호를 입력하세요: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("제목을 입력하세요: ");
                String title = sc.nextLine();
                System.out.print("내용을 입력하세요: ");
                String content = sc.nextLine();
                System.out.print("작성자를 입력하세요: ");
                String author = sc.nextLine();
                postService.update(id, title, content, author);
            } else if (option == 5) {
                System.out.print("삭제할 게시글 번호를 입력하세요: ");
                int id = sc.nextInt();
                System.out.println(postService.read(id).getTitle() + " 게시글이 삭제되었습니다.");
                postService.delete(id);
            } else if (option == 6) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 옵션입니다.");
            }
        }
    }
}
