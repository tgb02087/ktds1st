import java.util.HashMap;

interface PostRepository {
    public void create(String title, String content, String author);

    public Post read(int id);

    public HashMap<Integer, Post> readAll();

    public void update(int id, String title, String content, String author);

    public void delete(int id);
}
