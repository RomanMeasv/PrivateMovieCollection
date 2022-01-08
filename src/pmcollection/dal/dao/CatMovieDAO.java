package pmcollection.dal.dao;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.interfaces.ICatMovieDA;

import java.util.HashMap;

public class CatMovieDAO implements ICatMovieDA {

    @Override
    public HashMap<Movie, Category> getAllCatMovies() {
        return null;
    }

    @Override
    public int createCatMovie(Movie movie, Category category) {
        return 0;
    }

    @Override
    public void deleteCatMovie(Movie movie, Category category) {

    }
}
