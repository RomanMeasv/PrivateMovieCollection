package pmcollection.bll;

import pmcollection.be.Movie;
import pmcollection.dal.dao.CatMovieDAO;
import pmcollection.dal.interfaces.ICatMovieDA;
import pmcollection.dal.interfaces.IMovieDA;
import pmcollection.dal.dao.MovieDAO;

import java.io.IOException;
import java.util.List;

public class MovieLogic {
    private IMovieDA movieDAO;
    private ICatMovieDA catMovieDAO;

    public MovieLogic() throws Exception {
        movieDAO = new MovieDAO();
        catMovieDAO = new CatMovieDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public Movie getMovie(int id) throws Exception {
        Movie movie = movieDAO.getMovie(id);
        movie.setCategories(catMovieDAO.getCategoriesOfMovieById(id));
        return movie;
    }
}
