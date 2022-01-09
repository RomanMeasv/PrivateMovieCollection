package pmcollection.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.gui.model.CategoryModel;
import pmcollection.gui.model.MovieModel;


public class MovieController {
    @FXML
    public TableView<Category> categoryTBV;
    @FXML
    public TableView<Movie> movieTBV;

    private CategoryModel categoryModel;
    private MovieModel movieModel;

    public MovieController(){
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }

    public void handleAddRatingMovie(ActionEvent event) {
    }

    public void handleEditMovie(ActionEvent event) {
    }

    public void handleAddMovie(ActionEvent event) {
    }

    public void handleRemoveMovie(ActionEvent event) {
    }

    public void handleEditMovieRating(ActionEvent event) {
    }

    public void handleAddCat(ActionEvent event) {
    }

    public void handleEditCat(ActionEvent event) {
    }

    public void handleRemove(ActionEvent event) {
    }

    public void handleSearchMovie(ActionEvent event) {
    }
}
