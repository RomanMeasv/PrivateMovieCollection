package pmcollection.gui.model;

import javafx.collections.FXCollections;
import pmcollection.be.Movie;
import pmcollection.bll.MovieLogic;

import java.io.IOException;
import java.util.List;

public class MovieModel {

    private MovieLogic movieLogic;
    private List<Movie> movies;

    public MovieModel() throws IOException {
        init();
    }

    private void init() throws IOException {
        movieLogic = new MovieLogic();
        movies = FXCollections.observableList(movieLogic.getAllMovies());
    }
}
