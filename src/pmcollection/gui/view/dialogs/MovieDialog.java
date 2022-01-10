package pmcollection.gui.view.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.gui.controller.dialogs.MovieDialogController;

import java.io.IOException;
import java.util.List;

public class MovieDialog extends Dialog<Movie> {

    private MovieDialogController controller;

    public MovieDialog(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MovieDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add/Edit Song");
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

    public void setCategories(List<Category> categories){
        controller.setCategories(categories);
    }
}