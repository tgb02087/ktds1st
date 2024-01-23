import java.util.HashMap;
import java.util.List;

public class InMemoryPostRepository implements PostRepository {

    HashMap <Integer, Post> posts = new HashMap<>();
    @Override
    public void create(String title, String content, String author) {
        Post post = new Post(title, content, author);
        posts.put(posts.size()+1, post);
    }

    @Override
    public Post read(int id) {
        return posts.get(id);
    }

    @Override
    public HashMap<Integer, Post> readAll() {
        return posts;
    }

    @Override
    public void update(int id, String title, String content, String author) {
        posts.put(id, new Post(title, content, author));
    }

    @Override
    public void delete(int id) {
        posts.remove(id);
    }

}
