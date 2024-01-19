public class Book {

    private String title;
    private String author;
    private String status;
    private String returnDate;

    public Book(String title, String author, String status, String returnDate) {
        this.title = title;
        this.author = author;
        this.status = status.trim();
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}
