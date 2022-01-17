package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pmcollection.be.Category;

import java.util.List;

public class CategoryEditDialogController {

    @FXML
    public ListView<Category> allCategories;
    @FXML
    public ListView<Category> movieCategories;

    public void removeFromMovie() {
        Category selected = this.movieCategories.getSelectionModel().getSelectedItem();
        if(selected != null){
            if(!this.allCategories.getItems().contains(selected)){
                this.allCategories.getItems().add(selected);
            }
            this.movieCategories.getItems().remove(selected);
        }
    }

    public void addToMovie() {
        Category selected = this.allCategories.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.movieCategories.getItems().add(selected);
            this.allCategories.getItems().remove(selected);
        }
    }

    public List<Category> getMovieCategories() {
        return this.movieCategories.getItems();
    }

    public void setNotAssignedCategories(List<Category> categories){
        this.allCategories.setItems(FXCollections.observableList(categories));
    }

    public void setMovieCategories(List<Category> categories){
        this.movieCategories.setItems(FXCollections.observableList(categories));
    }
}
