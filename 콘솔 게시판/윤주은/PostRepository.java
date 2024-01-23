import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface PostRepository {
  void create(String title, String content, String author);
  Post read(int id);
  HashMap<Integer, Post> readAll();
  void update(int id, String title, String content, String author);
  void delete(int id);
}
