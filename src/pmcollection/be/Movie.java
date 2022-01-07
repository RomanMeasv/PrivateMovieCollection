package pmcollection.be;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private int rating;
    private String filelink;
    private LocalDate lastview;
    private List<Category> categories;

    public Movie(int id, String name, int rating, String filelink, LocalDate lastview) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.filelink = filelink;
        this.lastview = lastview;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public LocalDate getLastview() {
        return lastview;
    }

    public void setLastview(LocalDate lastview) {
        this.lastview = lastview;
    }

    /*
    One movie should only have one category only ONCE!
     */
    public void addCategory(Category category)
    {
        categories.add(category);
    }

    public void removeCategory(Category category)
    {
        categories.remove(category);
    }

    public List<Category> getCategories()
    {
        return categories;
    }
}
