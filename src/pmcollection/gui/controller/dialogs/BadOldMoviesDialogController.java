package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pmcollection.be.Movie;

import java.util.ArrayList;
import java.util.List;

public class BadOldMoviesDialogController {

    @FXML
    public ListView<Movie> moviesKeepLV;
    @FXML
    public ListView<Movie> moviesDeleteLV;

    private ObservableList<Movie> moviesToKeep;
    private ObservableList<Movie> moviesToDelete;

    public void initDialog(List<Movie> movies){
        this.moviesToKeep = FXCollections.observableList(movies);
        this.moviesToDelete = FXCollections.observableList(new ArrayList<>());
        this.moviesKeepLV.setItems(this.moviesToKeep);
        this.moviesDeleteLV.setItems(this.moviesToDelete);
    }

    public void movieToDelete(ActionEvent actionEvent) {
        Movie selected = this.moviesKeepLV.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.moviesToDelete.add(selected);
            this.moviesToKeep.remove(0);
        }
    }

    public void movieToKeep(ActionEvent actionEvent) {
        Movie selected = this.moviesDeleteLV.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.moviesToKeep.add(selected);
            this.moviesToDelete.remove(selected);
        }
    }

    public List<Movie> getMoviesToDelete() {
        return this.moviesToDelete;
    }
}
