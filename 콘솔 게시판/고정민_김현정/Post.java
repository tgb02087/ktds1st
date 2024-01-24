import java.time.LocalDate;

public class Post {
    private String title;
    private String content;
    private String author;
    private String date;

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = String.valueOf(LocalDate.now());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void printPost() {
        System.out.println("제목: " + title);
        System.out.println("작성자: " + author);
        System.out.println("내용: " + content);
    }

}
