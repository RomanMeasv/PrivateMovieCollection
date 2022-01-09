package pmcollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import pmcollection.be.Category;
import pmcollection.bll.CategoryLogic;

import javax.swing.text.TableView;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    private CategoryLogic categoryLogic;
    private ObservableList<Category> categories;

    public CategoryModel() throws Exception {
        init();
    }

    private void init() throws Exception {
        categoryLogic = new CategoryLogic();
        categories = FXCollections.observableList(categoryLogic.getAllCategories());
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
