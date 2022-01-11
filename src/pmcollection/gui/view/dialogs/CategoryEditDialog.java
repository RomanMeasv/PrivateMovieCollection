package pmcollection.gui.view.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import pmcollection.be.Category;
import pmcollection.gui.controller.dialogs.CategoryEditController;

import java.io.IOException;
import java.util.List;

public class CategoryEditDialog extends Dialog<List<Category>> {

    private CategoryEditController controller;

    public CategoryEditDialog(List<Category> allCategories, List<Category> movieCategories){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryEditView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            controller.setAllCategories(allCategories);
            controller.setMovieCategories(movieCategories);
            this.setTitle("Movie categories");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return controller.getMovieCategories();
                }
                return null;
            });

        } catch (IOException ioex){
            //System.out.println("Couldn't load view!");
        }
    }
}
