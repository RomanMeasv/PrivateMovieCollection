package pmcollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.bll.MovieLogic;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class MovieModel {

    private MovieLogic movieLogic;
    private ObservableList<Movie> movies;

    public MovieModel() throws Exception {
        init();
    }

    private void init() throws Exception {
        movieLogic = new MovieLogic();
        movies = FXCollections.observableList(movieLogic.getAllMovies());
    }
    public ObservableList<Movie> getMovieList() {
        return this.movies;
    }


    public void addMovie(Movie response) throws Exception {
        Movie addedToDB = this.movieLogic.addMovie(response);
        this.movies.add(addedToDB);
    }

    public void editMovie(Movie selected, Movie response) throws Exception {
        this.movieLogic.update(selected, response);
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

    public List<Movie> getBadOldMovies()
    {
        return movieLogic.getBadOldMovies(new ArrayList<Movie>(this.movies));
    }

}
