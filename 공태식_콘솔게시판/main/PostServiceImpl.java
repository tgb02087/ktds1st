package main;

import java.util.Scanner;

public class PostServiceImpl implements PostService{

    PostRepository postRepository = new InMemoryPostRepository();
    Scanner sc = new Scanner(System.in);

    @Override
    public void save() {
        System.out.println("제목을 입력해주세요.");
        String title = sc.nextLine();
        System.out.println("내용을 입력해주세요.");
        String content = sc.nextLine();
        System.out.println("작성자를 입력해주세요.");
        String author = sc.nextLine();
        postRepository.save(title, content, author);
    }

    @Override
    public void search() {
        System.out.println("조회할 게시물의 아이디를 입력해주세요.");
        int id = Integer.parseInt(sc.nextLine());
        Post post = postRepository.search(id);

        System.out.println("제목 : " + post.getTitle() + ", 내용 : " + post.getContent() + ", 작성자 : " + post.getAuthor());
    }

    @Override
    public void searchAll() {
        int[] arr = postRepository.searchAll();

        for(int key : arr){
            System.out.println(key);
        }
    }

    @Override
    public void update() {
        System.out.println("수정할 게시물의 아이디를 입력해주세요.");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("제목을 입력해주세요.");
        String title = sc.nextLine();
        System.out.println("내용을 입력해주세요.");
        String content = sc.nextLine();
        System.out.println("작성자를 입력해주세요.");
        String author = sc.nextLine();
        postRepository.update(id, title, content, author);

    }

    @Override
    public void delete() {
        System.out.println("삭제할 게시물의 아이디를 입력해주세요.");
        int id = Integer.parseInt(sc.nextLine());
        postRepository.delete(id);
    }
}
