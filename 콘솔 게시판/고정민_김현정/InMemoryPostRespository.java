import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPostRespository implements PostRepository {

    private final Map<Integer, Post> posts = new HashMap<>();
    private int id = 0;

    @Override
    public void create(String title, String content, String author) {
        Post post = new Post(title, content, author);
        posts.put(id++, post);
    }

    @Override
    public Post search(int id) {
        return posts.get(id);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public void update(int id, String title, String content, String author) {
        Post post = posts.get(id);
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
    }

    @Override
    public void delete(int id) {
        posts.remove(id);
    }

}
