package pmcollection.dal;

import pmcollection.be.Movie;

import java.util.List;

public interface IMovieDA {
    void createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovie(int id);
    void updateMovie(Movie movie);
    void deleteMovie(Movie movie);
}
