import java.util.List;

public class PostServiceImpl implements PostService {

    private final InMemoryPostRespository inMemoryPostRespository;

    public PostServiceImpl(InMemoryPostRespository inMemoryPostRespository) {
        this.inMemoryPostRespository = inMemoryPostRespository;
    }

    @Override
    public void createPost(String title, String content, String author) {
        inMemoryPostRespository.create(title, content, author);
    }

    public void print(Post post) {
        System.out.println("제목: " + post.getTitle());
        System.out.println("작성자: " + post.getAuthor());
        System.out.println("내용: " + post.getContent());
    }
    @Override
    public Post getPostById(Integer postId) {
        return inMemoryPostRespository.search(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return inMemoryPostRespository.getAll();
    }

    @Override
    public void updatePost(Integer postId, String title, String content, String author) {
        inMemoryPostRespository.update(postId, title, content, author);
    }

    @Override
    public void deletePost(Integer postId) {
        inMemoryPostRespository.delete(postId);
    }
}
