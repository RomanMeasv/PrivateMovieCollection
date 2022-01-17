package pmcollection.gui.view.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import pmcollection.be.Movie;
import pmcollection.gui.controller.dialogs.BadOldMoviesDialogController;

import java.io.IOException;
import java.util.List;

public class BadOldMoviesDialog extends Dialog<List<Movie>> {

    private BadOldMoviesDialogController controller;

    public BadOldMoviesDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BadOldMoviesDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Delete Bad Old Movies");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return controller.getMoviesToDelete();
                }
                return null;
            });

        } catch (IOException ioex){
            //System.out.println("Couldn't load view!");
        }
    }
}
