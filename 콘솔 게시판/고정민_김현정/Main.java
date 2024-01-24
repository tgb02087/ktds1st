import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PostService postService = new PostServiceImpl(new InMemoryPostRespository());
        boolean flag = true;

        while (flag) {
            System.out.print("1: 게시글 작성, 2: 게시글 조회, 3: 모든 게시글 보기, 4: 게시글 수정, 5: 게시글 삭제, 6: 종료\n" +
                    "옵션을 선택하세요: ");
            int option = Integer.parseInt(sc.nextLine());
            int id;
            String title, author, content;
            boolean exist;

            switch (option) {
                case 1 -> {
//                    sc.nextLine(); // 줄바꿈 문자 제거
                    System.out.println("---게시글 작성---");
                    System.out.print("제목을 입력해주세요: ");
                    title = sc.nextLine();
                    System.out.print("내용을 입력해주세요: ");
                    content = sc.nextLine();
                    System.out.print("작성자를 입력해주세요: ");
                    author = sc.nextLine();
                    postService.createPost(title, content, author);
                }
                case 2 -> {
                    System.out.println("---게시글 조회---");
                    System.out.print("조회할 게시글 ID를 입력해주세요: ");
                    id = Integer.parseInt(sc.nextLine());
                    Post post = postService.getPostById(id);
                    if (post != null) post.printPost();
                    else System.out.println("게시글이 존재하지 않습니다.");
                }
                case 3 -> {
                    System.out.println("---모든 게시글 보기---");
                    List<Post> postList = postService.getAllPosts();
                    if (!postList.isEmpty()) {
//                        System.out.println("제목  | 작성자   | 내용");
                        for (Post p : postList) {
//                            System.out.println(p.getTitle() + "  " + p.getAuthor() + "    " + p.getContent());
                            p.printPost();
                        }
                    } else {
                        System.out.println("게시글이 존재하지 않습니다.");
                    }
                }
                case 4 -> {
                    System.out.println("---게시글 수정---");
                    System.out.print("수정할 게시글 ID를 입력해주세요: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.print("제목을 입력해주세요: ");
                    title = sc.nextLine();
                    System.out.print("작성자를 입력해주세요: ");
                    author = sc.nextLine();
                    System.out.print("내용을 입력해주세요: ");
                    content = sc.nextLine();
                    postService.updatePost(id, title, content, author);
                }
                case 5 -> {
                    System.out.println("---게시글 삭제---");
                    System.out.print("삭제할 게시글 ID를 입력해주세요: ");
                    id = Integer.parseInt(sc.nextLine());
                    postService.deletePost(id);
                }
                case 6 -> {
                    System.out.println("종료");
                    flag = false;
                }
                default -> System.out.println("다시 입력해주세요.");
            }
        }
    }
}