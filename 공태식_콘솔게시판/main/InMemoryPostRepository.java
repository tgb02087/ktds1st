package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryPostRepository implements PostRepository{

    Map<Integer, Post> posts = new HashMap<>();


    @Override
    public void save(String title, String content, String author) {
        posts.put(posts.size(), new Post(posts.size(), title, content, author ));
    }

    @Override
    public void update(int id, String title, String content, String author) {
        posts.put(id, new Post(id, title, content, author ));
    }

    @Override
    public Post search(int id) {
        if(posts.containsKey(id)) {
            return posts.get(id);
        }

        return null;

    }

    @Override
    public int[] searchAll() {
//
//        posts.put(0, new Post(0,"제목1","내용1","작성자1"));
//        posts.put(1, new Post(1,"제목2","내용2","작성자2"));
//        posts.put(2, new Post(2,"제목3","내용3","작성자3"));
//        posts.put(3, new Post(3,"제목4","내용4","작성자4"));
//        posts.put(4, new Post(4,"제목5","내용5","작성자5"));
//

        int[] arr = new int[posts.size()];
        int num = 0;
        Set<Integer> set = posts.keySet();
        for(Integer key : set){
            arr[num] = key;
            num++;
        }

        return arr;
    }

    @Override
    public void delete(int id) {
        posts.remove(id);
    }
}
