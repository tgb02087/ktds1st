import java.util.HashMap;

interface PostService {

    void create(String title, String content, String author);

    Post read(int id);

    HashMap<Integer, Post> readAll();

    void update(int id, String title, String content, String author);

    void delete(int id);
}
