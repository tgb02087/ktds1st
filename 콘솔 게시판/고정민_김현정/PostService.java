import java.util.List;

public interface PostService {

    // 게시글 작성
    void createPost(String title, String content, String author);

    // 게시글 조회
    Post getPostById(Integer postId);

    // 모든 게시글 조회
    List<Post> getAllPosts();

    // 게시글 업데이트
    void updatePost(Integer postId, String title, String content, String author);

    // 게시글 삭제
    void deletePost(Integer postId);
}
