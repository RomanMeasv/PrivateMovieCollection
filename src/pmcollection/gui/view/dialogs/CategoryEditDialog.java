package pmcollection.gui.view.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import pmcollection.be.Category;
import pmcollection.gui.controller.dialogs.CategoryEditDialogController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryEditDialog extends Dialog<List<Category>> {

    private CategoryEditDialogController controller;

    public CategoryEditDialog(List<Category> usedCategories){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryEditView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            controller.init(usedCategories);
            this.setTitle("Movie categories");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return controller.getUsedCategories();
                }
                return null;
            });

        } catch (IOException ioex){
            //System.out.println("Couldn't load view!");
        }
    }

    private List<Category> getNotAssignedCategories(List<Category> allCategories, List<Category> movieCategories){
        List<Category> notInMovie = new ArrayList<>();
        for (Category category :
                allCategories) {
            if(!movieCategories.contains(category)){
                notInMovie.add(category);
            }
        }
        return notInMovie;
    }
}
