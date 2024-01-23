package main;

import java.util.Scanner;

public class Main {

    static PostService postService = new PostServiceImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loop : while (true){
            System.out.println("1: 게시글 작성, 2: 게시글 조회, 3: 모든 게시글 보기, 4: 게시글 수정, 5: 게시글 삭제, 6: 종료");
            System.out.print("옵션을 선택하세요:");
            int input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 1 :
                    postService.save();
                    break;
                case 2 :
                    postService.search();
                    break;
                case 3 :
                    postService.searchAll();
                    break;
                case 4 :
                    postService.update();
                    break;
                case 5 :
                    postService.delete();
                    break;
                case 6 :
                    break loop;
                default:
            }
        }


    }
}
