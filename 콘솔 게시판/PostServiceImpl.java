import java.util.HashMap;

public class PostServiceImpl implements PostService {

    InMemoryPostRepository inMemoryPostRepository = new InMemoryPostRepository();

    public PostServiceImpl(InMemoryPostRepository repository) {
        this.inMemoryPostRepository = repository;
    }

    @Override
    public void create(String title, String content, String author) {
        inMemoryPostRepository.create(title, content, author);
    }

    @Override
    public Post read(int id) {
        return inMemoryPostRepository.read(id);
    }

    @Override
    public HashMap<Integer, Post> readAll() {
        return inMemoryPostRepository.readAll();
    }

    @Override
    public void update(int id, String title, String content, String author) {
        inMemoryPostRepository.update(id, title, content, author);
    }

    @Override
    public void delete(int id) {
        inMemoryPostRepository.delete(id);
    }
}
