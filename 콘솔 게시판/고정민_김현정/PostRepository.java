import java.util.List;

public interface PostRepository {
    public void create(String title, String content, String author);
    public Post search(int id);
    public List<Post> getAll();
    public void update(int id, String title, String content, String author);
    public void delete(int id);
}