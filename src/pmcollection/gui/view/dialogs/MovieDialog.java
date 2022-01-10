package pmcollection.gui.view.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.gui.controller.dialogs.MovieDialogController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieDialog extends Dialog<Movie> {

    private MovieDialogController controller;

    public MovieDialog(List<Category> allCategories){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MovieDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
            controller.setAllCategories(allCategories);
>>>>>>> Stashed changes
=======
            controller.setAllCategories(allCategories);
>>>>>>> Stashed changes
            this.setTitle("Add/Edit Movie");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Movie(controller.getName(), controller.getCategories(), controller.getRating(), controller.getLastview(), controller.getFilelink());
                }
                return null;
            });

        } catch (IOException ioex){
            //System.out.println("Couldn't load view!");
        }
    }

    public void setFields(Movie movie){
        controller.setName(movie.getName());
        controller.setCategories(movie.getCategories());
        controller.setRating(movie.getRating());
        controller.setLastView(movie.getLastview());
        controller.setLink(movie.getFilelink());
    }
}
