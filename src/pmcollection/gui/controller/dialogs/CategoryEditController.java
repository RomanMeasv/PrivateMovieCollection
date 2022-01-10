package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pmcollection.be.Category;
import pmcollection.be.Movie;

import java.util.List;

public class CategoryEditController {

    @FXML
    public ListView<Category> allCategories;
    @FXML
    public ListView<Category> movieCategories;

    public void removeFromMovie(ActionEvent actionEvent) {
        Category selected = this.movieCategories.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.allCategories.getItems().add(selected);
            this.movieCategories.getItems().remove(selected);
        }
    }

    public void addToMovie(ActionEvent actionEvent) {
        Category selected = this.allCategories.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.movieCategories.getItems().add(selected);
            this.allCategories.getItems().remove(selected);
        }
    }

    public List<Category> getMovieCategories() {
        return this.movieCategories.getItems();
    }

    public void setAllCategories(List<Category> categories){
        this.allCategories.setItems(FXCollections.observableList(categories));
    }

    public void setMovieCategories(List<Category> categories){
        this.movieCategories.setItems(FXCollections.observableList(categories));
    }
}
