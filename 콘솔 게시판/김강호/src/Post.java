import java.time.LocalDate;

public class Post {
    private final String title;

    private final String content;

    private final String author;

    private final String date;

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = LocalDate.now().toString();
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }
}
