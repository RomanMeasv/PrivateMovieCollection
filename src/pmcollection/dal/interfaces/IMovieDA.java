package pmcollection.dal.interfaces;

import pmcollection.be.Movie;

import java.util.List;

public interface IMovieDA {
    Movie createMovie(Movie movie) throws Exception;
    List<Movie> getAllMovies() throws Exception;
    Movie getMovie(int id) throws Exception;
    void updateMovie(Movie movie) throws Exception;
    void deleteMovie(Movie movie) throws Exception;
}
