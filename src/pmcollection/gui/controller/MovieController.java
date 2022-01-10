package pmcollection.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.gui.model.CategoryModel;
import pmcollection.gui.model.MovieModel;
import pmcollection.gui.view.dialogs.CategoryDialog;
import pmcollection.gui.view.dialogs.MovieDialog;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class MovieController implements Initializable {
    @FXML
    public TableView<Category> categoryTBV;
    @FXML
    public TableView<Movie> movieTBV;
    @FXML
    public TextField filterField;
    @FXML
    public TableColumn<Category, String> categoryNameColumn;
    @FXML
    public TableColumn<Movie, String> movieTBVName;
    @FXML
    public TableColumn<Movie, String> movieTBVRating;
    @FXML
    public TableColumn<Movie, String> movieTBVCategories;

    private CategoryModel categoryModel;
    private MovieModel movieModel;

    public MovieController() {
        try{
            categoryModel = new CategoryModel();
            movieModel = new MovieModel();
        } catch (Exception ignored){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTables();
    }

    private void initTables() {
        this.categoryTBV.setItems(categoryModel.getCategoryList());
        this.categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.movieTBV.setItems(movieModel.getMovieList());
        this.movieTBVName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.movieTBVRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        this.movieTBVCategories.setCellValueFactory(new PropertyValueFactory<>("categories"));


    }

    public void categoryAdd(ActionEvent event) {
        CategoryDialog dialog = new CategoryDialog();
        Optional<Category> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.categoryModel.addCategory(response);
            } catch (Exception ignored) {

            }
        });
    }

    public void categoryEdit(ActionEvent event) {
        Category selected = categoryTBV.getSelectionModel().getSelectedItem();
        if(selected != null){
            CategoryDialog dialog = new CategoryDialog();
            dialog.setFields(selected);
            Optional<Category> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setId(selected.getId());
                    this.categoryModel.editCategory(selected, response);
                } catch (Exception ignored) {

                }
            });
        }
    }

    public void categoryRemove(ActionEvent event) {
        Category selected = categoryTBV.getSelectionModel().getSelectedItem();
        if(selected != null){
            try{
                this.categoryModel.deleteCategory(selected);
            } catch (Exception ignored){

            }
        }
    }

    public void filterHandle(ActionEvent event) {
    }

    public void movieAdd(ActionEvent event) {
        MovieDialog dialog = new MovieDialog(new ArrayList<>(this.categoryTBV.getItems()));
        Optional<Movie> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.movieModel.addMovie(response);
            } catch (Exception ignored) {

            }
        });
    }

    public void movieEdit(ActionEvent event) {
        Movie selected = movieTBV.getSelectionModel().getSelectedItem();
        if(selected != null) {
            MovieDialog dialog = new MovieDialog();
            dialog.setFields(selected);
            Optional<Movie> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setId(selected.getId());
                    this.movieModel.editMovie(selected, response);
                } catch (Exception ignored) {

                }
            });
        }
    }

    public void movieRemove(ActionEvent event) {
        Movie selected = movieTBV.getSelectionModel().getSelectedItem();
        if(selected != null){
            try{
                this.movieModel.deleteMovie(selected);
            } catch (Exception ignored) {
            }
        }
    }

    public void ratingEdit(ActionEvent event) {
    }
}
