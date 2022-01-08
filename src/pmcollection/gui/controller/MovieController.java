package pmcollection.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.gui.model.CategoryModel;
import pmcollection.gui.model.MovieModel;

import java.io.IOException;


public class MovieController {
    @FXML
    public ListView<Category> categoryLV;
    @FXML
    public ListView<Movie> movieLV;

    private CategoryModel categoryModel;
    private MovieModel movieModel;

    public MovieController() throws IOException {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }
}
