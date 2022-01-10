package pmcollection.dal.interfaces;

import pmcollection.be.Category;
import pmcollection.be.Movie;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ICatMovieDA {
    HashMap<Movie, Category> getAllCategoryMovieLinks() throws Exception;
    List<Category> getCategoriesOfMovie(Movie movie) throws Exception;
    void linkMovieToItsCategories(Movie movie) throws Exception;
    void unlinkMovieFromItsCategories(Movie movie) throws Exception;
    void linkCategoryToMovie(Category category, Movie movie) throws Exception;
    void unlinkCategoryFromMovie(Category category, Movie movie) throws SQLException;
    void unlinkCategoryFromMovies(Category category) throws Exception;
}
