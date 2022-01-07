package pmcollection.gui.model;

import javafx.collections.FXCollections;
import pmcollection.be.Category;
import pmcollection.bll.CategoryLogic;

import java.util.List;

public class CategoryModel {
    private CategoryLogic categoryLogic;
    private List<Category> categories;

    public CategoryModel(){
        init();
    }

    private void init() {
        categoryLogic = new CategoryLogic();
        categories = FXCollections.observableList(categoryLogic.getAllCategories());
    }
}
