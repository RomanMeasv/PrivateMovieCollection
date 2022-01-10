package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.interfaces.IMovieDA;
import pmcollection.dal.dao.MovieDAO;

import java.io.IOException;
import java.util.List;

public class MovieLogic {
    private IMovieDA movieDAO;

    public MovieLogic() throws IOException {
        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies()throws Exception{
        return movieDAO.getAllMovies();
    }

    public Movie addMovie(Movie movie) throws Exception {
        return this.movieDAO.createMovie(movie);
    }
    public void update(Movie selected) throws Exception {
        this.movieDAO.updateMovie(selected);
    }

    public void delete(Movie selected) throws Exception {
        this.movieDAO.deleteMovie(selected);
    }
}
