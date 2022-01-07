package pmcollection.dal;

import pmcollection.be.Movie;

import java.util.List;

public interface IMovieDAO {
    int create();
    List<Movie> read();
    void update();
    void delete();
}
