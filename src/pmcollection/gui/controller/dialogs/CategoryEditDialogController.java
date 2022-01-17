package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import pmcollection.be.Category;
import pmcollection.gui.model.CategoryModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryEditDialogController implements Initializable {

    @FXML
    public ListView<Category> unusedCategoriesLV;
    @FXML
    public ListView<Category> usedCategoriesLV;

    private CategoryModel unusedCategoriesModel;
    private CategoryModel usedCategoriesModel;

    public CategoryEditDialogController(){
        unusedCategoriesModel = new CategoryModel();
        usedCategoriesModel = new CategoryModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.unusedCategoriesModel.loadAllCategories();
        } catch (Exception e){

        }
        this.unusedCategoriesLV.setItems(unusedCategoriesModel.getCategoryList());
        this.usedCategoriesLV.setItems(usedCategoriesModel.getCategoryList());
    }

    public void init(List<Category> usedCategories) {
        usedCategoriesModel.replaceAll(usedCategories);
        unusedCategoriesModel.removeUsed(usedCategories);
    }

    public void removeFromMovie() {
        Category selected = this.usedCategoriesLV.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.unusedCategoriesLV.getItems().add(selected);
            this.usedCategoriesLV.getItems().remove(selected);
        }
    }

    public void addToMovie() {
        Category selected = this.unusedCategoriesLV.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.usedCategoriesLV.getItems().add(selected);
            this.unusedCategoriesLV.getItems().remove(selected);
        }
    }

    public List<Category> getUsedCategories() {
        return this.usedCategoriesModel.getCategoryList().stream().toList();
    }
}
