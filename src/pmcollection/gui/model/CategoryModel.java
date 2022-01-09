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
    private List<Category> categories;

    public CategoryModel(){
        init();
    }

    private void init() {
        categoryLogic = new CategoryLogic();

        //categoryLogic.getAllCategories()
        categories = FXCollections.observableList(new ArrayList<>());


    }
}
