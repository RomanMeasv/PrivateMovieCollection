package pmcollection.gui.view.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import pmcollection.be.Category;
import pmcollection.gui.controller.dialogs.CategoryDialogController;

import java.io.IOException;

public class CategoryDialog extends Dialog<Category> {

    private CategoryDialogController controller;

    public CategoryDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../CategoryDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add/Edit Category");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Category(controller.getName());
                }
                return null;
            });

        } catch (IOException ioex){
            //System.out.println("Couldn't load view!");
        }
    }

    public void setFields(Category category){
        controller.setName(category.getName());
    }
}
