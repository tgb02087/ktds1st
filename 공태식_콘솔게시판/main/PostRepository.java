package main;

import java.util.ArrayList;

public interface PostRepository {
    public void save(String title, String content, String author);

    public void update(int id, String title, String content, String author);

    public Post search(int id);

    public int[] searchAll();

    public void delete(int id);
}
