package pmcollection.gui.controller.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import pmcollection.be.Movie;
import pmcollection.gui.model.MovieModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BadOldMoviesDialogController implements Initializable{

    @FXML
    public ListView<Movie> moviesKeepLV;
    @FXML
    public ListView<Movie> moviesDeleteLV;

    private MovieModel moviesToKeepModel;
    private MovieModel moviesToDeleteModel;

    public BadOldMoviesDialogController(){
        try{
            this.moviesToKeepModel = new MovieModel();
            this.moviesToDeleteModel = new MovieModel();
            this.moviesToKeepModel.loadBadMovies();
        } catch (Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.moviesKeepLV.setItems(this.moviesToKeepModel.getMovieList());
        this.moviesDeleteLV.setItems(this.moviesToDeleteModel.getMovieList());
    }

    public void movieToDelete() {
        Movie selected = this.moviesKeepLV.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.moviesDeleteLV.getItems().add(selected);
            this.moviesKeepLV.getItems().remove(selected);
        }
    }

    public void movieToKeep() {
        Movie selected = this.moviesDeleteLV.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.moviesKeepLV.getItems().add(selected);
            this.moviesDeleteLV.getItems().remove(selected);
        }
    }

    public List<Movie> getMoviesToDelete() {
        return this.moviesToDeleteModel.getMovieList().stream().toList();
    }
}
