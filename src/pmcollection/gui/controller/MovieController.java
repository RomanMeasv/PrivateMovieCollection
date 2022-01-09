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

import java.io.IOException;
import java.net.URL;
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
    }

    public void movieEdit(ActionEvent event) {
    }

    public void movieRemove(ActionEvent event) {
    }

    public void ratingEdit(ActionEvent event) {
    }
}
