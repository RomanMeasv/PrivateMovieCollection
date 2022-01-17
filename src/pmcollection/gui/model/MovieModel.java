package pmcollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.bll.MovieLogic;

import java.util.ArrayList;
import java.util.List;

public class MovieModel {

    private MovieLogic movieLogic;
    private ObservableList<Movie> movies;

    public MovieModel() {
        movieLogic = new MovieLogic();
        movies = FXCollections.observableList(new ArrayList<>());
    }

    public ObservableList<Movie> getMovieList() {
        return this.movies;
    }

    public void createMovie(Movie response) throws Exception {
        Movie addedToDB = this.movieLogic.createMovie(response);
        this.movies.add(addedToDB);
    }

    public void updateMovie(Movie selected, Movie response) throws Exception {
        this.movieLogic.updateMovie(response);
        this.movies.set(this.movies.indexOf(selected), response);
    }

    public void deleteMovie(Movie selected) throws Exception {
        this.movieLogic.delete(selected);
        this.movies.remove(selected);
    }

    public void filterMovies(String name, List<Category> categories, float min, float max)throws Exception{
        movies.clear();
        movies.addAll(movieLogic.filterMovies(name, categories, min, max));
    }

    public List<Movie> getBadOldMovies() throws Exception
    {
        return movieLogic.getBadOldMovies();
    }

    public void loadAllMovies() throws Exception {
        movies.clear();
        movies.addAll(movieLogic.getAllMovies());
    }

    public void loadBadMovies() throws Exception {
        movies.clear();
        movies.addAll(movieLogic.getBadOldMovies());
    }

    public void deleteMovies(List<Movie> movieList) throws Exception {
        for (Movie movie :
                movieList) {
            this.deleteMovie(movie);
        }
    }
}
