package pmcollection.gui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.gui.model.CategoryModel;
import pmcollection.gui.model.MovieModel;
import pmcollection.gui.view.dialogs.BadOldMoviesDialog;
import pmcollection.gui.view.dialogs.CategoryDialog;
import pmcollection.gui.view.dialogs.CategoryEditDialog;
import pmcollection.gui.view.dialogs.MovieDialog;

import java.io.File;
import java.net.URL;

import java.time.LocalDate;
import java.util.*;


public class MovieController implements Initializable {
    @FXML
    private TableView<Category> categoryTBV;
    @FXML
    private TableView<Movie> movieTBV;
    @FXML
    private TextField nameFilterField;
    @FXML
    private TextField categoryFilterField;
    @FXML
    private TextField ratingMinField, ratingMaxField;
    @FXML
    private TableColumn<Category, String> categoryNameColumn;
    @FXML
    private TableColumn<Movie, String> movieTBVName;
    @FXML
    private TableColumn<Movie, String> movieTBVLastView;
    @FXML
    private TableColumn<Movie, String> movieTBVRating;
    @FXML
    private TableColumn<Movie, String> movieTBVCategories;

    private CategoryModel categoryModel;
    private MovieModel movieModel;

    public MovieController() {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDatabaseData();
        initTables();
        checkBadOldMovies();
    }

    private void loadDatabaseData(){
        try {
            movieModel.loadAllMovies();
            categoryModel.loadAllCategories();
        } catch (Exception e) {
            popAlertDialog(e);
        }
    }

    private void initTables() {
        this.categoryTBV.setItems(categoryModel.getCategoryList());
        this.categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.movieTBV.setItems(movieModel.getMovieList());
        this.movieTBVName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.movieTBVRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        this.movieTBVCategories.setCellValueFactory(cellData -> cellData.getValue().getCategoriesString());
        this.movieTBVLastView.setCellValueFactory(new PropertyValueFactory<>("lastview"));
    }

    private void checkBadOldMovies() {
        try{
            List<Movie> badOldMovies = this.movieModel.getBadOldMovies();
            if (!badOldMovies.isEmpty()) {
                BadOldMoviesDialog dialog = new BadOldMoviesDialog();
                Optional<List<Movie>> result = dialog.showAndWait();
                result.ifPresent(response -> {
                    try {
                        this.movieModel.deleteMovies(response);
                    } catch (Exception e) {
                        popAlertDialog(e);
                    }
                });
            }
        } catch (Exception e){
            popAlertDialog(e);
        }


    }

    public void categoryCreate() {
        CategoryDialog dialog = new CategoryDialog();
        Optional<Category> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.categoryModel.createCategory(response);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        });
    }

    public void categoryUpdate() {
        Category selected = categoryTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            CategoryDialog dialog = new CategoryDialog();
            dialog.setFields(selected);
            Optional<Category> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setId(selected.getId());
                    this.categoryModel.updateCategory(selected, response);
                    movieModel.loadAllMovies();
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            });
        }
    }

    public void categoryDelete() {
        Category selected = categoryTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                this.categoryModel.deleteCategory(selected);
                movieModel.loadAllMovies();
            } catch (Exception e) {
                popAlertDialog(e);
            }
        }
    }


    public void movieCreate() {
        MovieDialog dialog = new MovieDialog(new ArrayList<>(this.categoryModel.getCategoryList()));
        Optional<Movie> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.movieModel.createMovie(response);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        });
    }

    public void movieUpdate() {
        Movie selected = movieTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            MovieDialog dialog = new MovieDialog(new ArrayList<>(this.categoryModel.getCategoryList()));
            dialog.setFields(selected);
            Optional<Movie> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setId(selected.getId());
                    this.movieModel.updateMovie(selected, response);
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            });
        }
    }

    public void movieDelete() {
        Movie selected = movieTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                this.movieModel.deleteMovie(selected);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        }
    }

    public void movieTableClick(MouseEvent event) throws Exception {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            Movie selected = this.movieTBV.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Movie response = selected;
                response.setLastview(LocalDate.now());
                movieModel.updateMovie(selected, response);

                try {
                    File file = new File(selected.getFilelink());
                    Media media = new Media(file.toURI().toURL().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setAutoPlay(false);

                    //load fxml file, create new scene and new stage
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MoviePlayerView.fxml"));
                    Parent root = loader.load();
                    MoviePlayerController controller = loader.getController();
                    Scene scene = new Scene(root);
                    Stage videoStage = new Stage();
                    videoStage.setTitle("Video Player");
                    videoStage.setWidth(1000);
                    videoStage.setHeight(700);

                    //init controller & show stage
                    controller.init(mediaPlayer);
                    videoStage.setScene(scene);
                    videoStage.setOnHidden(e ->{
                        controller.shutDown();
                    });
                    controller.fullScreenHandle();
                    videoStage.show();
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            }
        }
    }

    public void filterHandle() {
        try {
            this.movieModel.filterMovies(nameFilterField.getText(),
                    queryToList(categoryFilterField.getText()),
                    queryToFloat(ratingMinField.getText()),
                    queryToFloat(ratingMaxField.getText()));
        } catch (Exception e) {
            popAlertDialog(e);
        }
    }

    public void editCategoryFilter() {
        CategoryEditDialog dialog = new CategoryEditDialog(queryToList(this.categoryFilterField.getText()));
        Optional<List<Category>> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                categoryFilterField.setText(listToQuery(response));
            } catch (Exception e) {
                popAlertDialog(e);
            }
        });
    }

    private void popAlertDialog(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Dialog");
        alert.setContentText(exception.getMessage());

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(response -> {
            if(response == ButtonType.OK){
                //user chose ok
            } else {
                //user chose cancel or closed the dialog
            }
        });
    }

    private String listToQuery(List<Category> response) {
        String query = "";
        for (Category category :
                response) {
            query += category.getName() + ", ";
        }
        return query;
    }

    private List<Category> queryToList(String query) {
        List<Category> categories = new ArrayList<>();
        for (String separated :
                query.split(", ")) {
            for (Category category :
                    categoryTBV.getItems()) {
                if (separated.equalsIgnoreCase(category.getName())) {
                    categories.add(category);
                }
            }
        }
        return categories;
    }

    private float queryToFloat(String query) {
        try {
            return Float.parseFloat(query);
        } catch (Exception exception) {
            return 0;
        }
    }

    public void clearFiltershandle() {
        nameFilterField.clear();
        categoryFilterField.clear();
        ratingMinField.clear();
        ratingMaxField.clear();
        try {
            movieModel.loadAllMovies();
        } catch (Exception e) {
            popAlertDialog(e);
        }
    }
}