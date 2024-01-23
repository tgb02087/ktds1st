import java.util.HashMap;

public class InmemoryPostRepository implements PostRepository{
    HashMap<Integer, Post> post = new HashMap<>();

    @Override
    public void create(String title, String content, String author) {
        post.put(post.size() + 1, new Post(title, content, author));
    }

    @Override
    public Post read(int id) {
        return post.get(id);
    }

    @Override
    public HashMap<Integer, Post> readAll() {
        return this.post;
    }

    @Override
    public void update(int id, String title, String content, String author) {
        post.put(id, new Post(title, content, author));
    }

    @Override
    public void delete(int id) {
        post.remove(id);
    }
}
