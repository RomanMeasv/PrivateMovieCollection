package pmcollection.dal;

import pmcollection.be.Category;
import pmcollection.be.Movie;

import java.util.List;

public interface ICatMovieDA {
    int create();
    List<Category> read();
    void update();
    void delete();
}
