package pmcollection.dal.interfaces;

import pmcollection.be.Movie;
import pmcollection.dal.exceptions.MovieNameAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;

public interface IMovieDA {
    Movie createMovie(Movie movie) throws SQLException, MovieNameAlreadyExistsException;
    List<Movie> getAllMovies();
    Movie getMovie(int id);
    void updateMovie(Movie movie);
    void deleteMovie(Movie movie);
}
