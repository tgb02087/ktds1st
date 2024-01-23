import java.util.HashMap;

public class PostServiceImpl implements PostService{
    InmemoryPostRepository inmemoryPostRepository = new InmemoryPostRepository();

    @Override
    public void create(String title, String content, String author) {
        inmemoryPostRepository.create(title, content, author);
    }

    @Override
    public Post read(int id) {
        return inmemoryPostRepository.read(id);
    }

    @Override
    public HashMap<Integer, Post> readAll() {
        return inmemoryPostRepository.readAll();
    }

    @Override
    public void update(int id, String title, String content, String author) {
        inmemoryPostRepository.update(id, title, content, author);
    }

    @Override
    public void delete(int id) {
        inmemoryPostRepository.delete(id);
    }
}
