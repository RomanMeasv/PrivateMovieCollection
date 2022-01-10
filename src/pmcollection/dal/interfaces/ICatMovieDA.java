package pmcollection.dal.interfaces;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pmcollection.be.Category;
import pmcollection.be.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ICatMovieDA {
    HashMap<Movie, Category> getAllCatMovies() throws Exception;
    void createCatMovie(Movie movie, Category category) throws Exception;
    void deleteCatMovie(Movie movie, Category category) throws Exception;
    List<Category> getCategoriesOfMovieById(int id) throws Exception;
}
