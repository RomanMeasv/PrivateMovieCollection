package pmcollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import pmcollection.be.Movie;
import pmcollection.bll.MovieLogic;

import java.util.ArrayList;
import java.util.List;

public class MovieModel {

    private MovieLogic movieLogic;
    private ObservableList<Movie> movies;

    public MovieModel(){
        init();
    }

    private void init(){
        movieLogic = new MovieLogic();
        //movieLogic.getAllMovies()
        movies = FXCollections.observableList(new ArrayList<>());


    }
}
