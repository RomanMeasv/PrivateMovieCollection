package pmcollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pmcollection.be.Category;
import pmcollection.bll.CategoryLogic;

import java.util.ArrayList;

public class CategoryFilterModel {
    private CategoryLogic categoryLogic;
    private ObservableList<Category> categories;

    public CategoryFilterModel() throws Exception {
        init();
    }

    private void init() throws Exception {
        categoryLogic = new CategoryLogic();
        categories = FXCollections.observableList(new ArrayList<>());
    }

    public ObservableList<Category> getCategoryList() {
        return this.categories;
    }

    public void addCategory(Category response) throws Exception {
        Category addedToDB = this.categoryLogic.addCategory(response);
        this.categories.add(addedToDB);
    }

    public void editCategory(Category selected, Category response) throws Exception {
        this.categoryLogic.update(response);
        this.categories.set(this.categories.indexOf(selected), response);
    }

    public void deleteCategory(Category selected) throws Exception {
        this.categoryLogic.delete(selected);
        this.categories.remove(selected);
    }
}
