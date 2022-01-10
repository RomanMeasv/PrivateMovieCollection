package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.dao.CatMovieDAO;
import pmcollection.dal.interfaces.ICatMovieDA;
import pmcollection.dal.interfaces.IMovieDA;
import pmcollection.dal.dao.MovieDAO;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MovieLogic {
    private IMovieDA movieDAO;
    private ICatMovieDA catMovieDAO;

    public MovieLogic() throws Exception {
        movieDAO = new MovieDAO();
        catMovieDAO = new CatMovieDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        List<Movie> allMovies = movieDAO.getAllMovies();
        for (Movie movie : allMovies) {
            movie.setCategories(catMovieDAO.getCategoriesOfMovieById(movie.getId()));
        }
        return allMovies;
    }

    public Movie getMovie(int id) throws Exception {
        Movie movie = movieDAO.getMovie(id);
        movie.setCategories(catMovieDAO.getCategoriesOfMovieById(id));
        return movie;
    }
    public Movie addMovie(Movie movie) throws Exception {
        Movie mov = this.movieDAO.createMovie(movie);
        catMovieDAO.createCategoryLinksToMovie(movie);
        return mov;
    }
    public void update(Movie selected) throws Exception {
        this.movieDAO.updateMovie(selected);
    }

    public void delete(Movie selected) throws Exception {
        //first delete links then movie
        this.movieDAO.deleteMovie (selected);
    }

}
