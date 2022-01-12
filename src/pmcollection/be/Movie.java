package pmcollection.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String filelink;
    private LocalDate lastview;
    private List<Category> categories;

    public Movie(int id, String name, float rating, String filelink, LocalDate lastview) {
        this.id = id;
        this.name = name;
        this.categories = new ArrayList<>();
        this.rating = rating;
        this.lastview = lastview;
        this.filelink = filelink;
    }

    public Movie(String name, List<Category> categories, float rating, LocalDate lastview, String filelink) {
        this.name = name;
        this.categories = categories;
        this.rating = rating;
        this.lastview = lastview;
        this.filelink = filelink;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public SimpleStringProperty getCategoriesString(){
        SimpleStringProperty ssp = new SimpleStringProperty();
        String ret = "";
        for (Category c : this.categories){
            ret += c.getName()+", ";
        }
        ret = ret.trim();
        ssp.setValue(ret);
        return ssp;
    }

    public List<Category> getCategories()
    {
        return this.categories;
    }

    public float getRating() {
        return this.rating;
    }

    public LocalDate getLastview() {
        return this.lastview;
    }

    public String getFilelink() {
        return this.filelink;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategories(List<Category> categories) { this.categories = categories;}

    //Rating should be 0-10
    public void setRating(float rating) {
        this.rating = rating < 0 ? 0 : rating > 10 ? 10 : rating;
    }

    public void setLastview(LocalDate lastview) {
        this.lastview = lastview;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    /*
    One movie should only have one category only ONCE! But this is taken care of int the UI!
     */
    public void addCategory(Category category)
    {
            categories.add(category);
    }

    public void removeCategory(Category category)
    {
        categories.remove(category);
    }
}
