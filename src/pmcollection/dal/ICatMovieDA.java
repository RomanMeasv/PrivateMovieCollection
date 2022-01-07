package pmcollection.dal;

import pmcollection.be.Category;
import pmcollection.be.Movie;

import java.util.HashMap;

public interface ICatMovieDA {
    HashMap<Movie, Category> getAllCatMovies();
    int createCatMovie(Movie movie, Category category);
    void deleteCatMovie(Movie movie, Category category);
}
