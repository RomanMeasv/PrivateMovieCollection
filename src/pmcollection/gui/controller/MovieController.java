package pmcollection.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
import pmcollection.bll.MovieLogic;
import pmcollection.gui.model.CategoryModel;
import pmcollection.gui.model.MovieModel;
import pmcollection.gui.view.dialogs.BadOldMoviesDialog;
import pmcollection.gui.view.dialogs.CategoryDialog;
import pmcollection.gui.view.dialogs.CategoryEditDialog;
import pmcollection.gui.view.dialogs.MovieDialog;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.net.MalformedURLException;
import java.net.URL;

import java.time.LocalDate;
import java.util.*;


public class MovieController implements Initializable {
    @FXML
    public TableView<Category> categoryTBV;
    @FXML
    public TableView<Movie> movieTBV;
    @FXML
    public TextField nameFilterField;
    @FXML
    public TextField categoryFilterField;
    @FXML
    public TextField ratingMinField, ratingMaxField;
    @FXML
    public TableColumn<Category, String> categoryNameColumn;
    @FXML
    public TableColumn<Movie, String> movieTBVName;
    @FXML
    public TableColumn<Movie, String> movieTBVLastView;
    @FXML
    public TableColumn<Movie, String> movieTBVRating;
    @FXML
    public TableColumn<Movie, String> movieTBVCategories;


    private CategoryModel categoryModel;
    private MovieModel movieModel;

    public MovieController() {
        try {
            categoryModel = new CategoryModel();
            movieModel = new MovieModel();
        } catch (Exception e) {
            popAlertDialog(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTables();
        checkBadOldMovies();
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
        List<Movie> badOldMovies = new ArrayList<>(this.movieModel.getBadOldMovies());
        if (!badOldMovies.isEmpty()) {
            BadOldMoviesDialog dialog = new BadOldMoviesDialog(badOldMovies);
            Optional<List<Movie>> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    for (Movie movie :
                            response) {
                        this.movieModel.deleteMovie(movie);
                    }
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            });
        }
    }

    public void categoryAdd(ActionEvent event) {
        CategoryDialog dialog = new CategoryDialog();
        Optional<Category> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.categoryModel.addCategory(response);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        });
    }

    public void categoryEdit(ActionEvent event) {
        Category selected = categoryTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            CategoryDialog dialog = new CategoryDialog();
            dialog.setFields(selected);
            Optional<Category> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setId(selected.getId());
                    this.categoryModel.editCategory(selected, response);
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            });
        }
    }

    public void categoryRemove(ActionEvent event) {
        Category selected = categoryTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                this.categoryModel.deleteCategory(selected);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        }
    }


    public void movieAdd(ActionEvent event) {
        MovieDialog dialog = new MovieDialog(new ArrayList<>(this.categoryModel.getCategoryList()));
        Optional<Movie> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.movieModel.addMovie(response);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        });
    }

    public void movieEdit(ActionEvent event) {
        Movie selected = movieTBV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            MovieDialog dialog = new MovieDialog(new ArrayList<>(this.categoryModel.getCategoryList()));
            dialog.setFields(selected);
            Optional<Movie> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setId(selected.getId());
                    this.movieModel.editMovie(selected, response);
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            });
        }
    }

    public void movieRemove(ActionEvent event) {
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
                movieModel.editMovie(selected, response);

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

                    //init controller & show stage
                    controller.init(mediaPlayer);
                    videoStage.setScene(scene);
                    videoStage.show();
                } catch (Exception e) {
                    popAlertDialog(e);
                }
            }
        }
    }

    public void filterHandle(ActionEvent event) {
        try {
            this.movieModel.filterMovies(nameFilterField.getText(),
                    queryToList(categoryFilterField.getText()),
                    queryToFloat(ratingMinField.getText()),
                    queryToFloat(ratingMaxField.getText()));
        } catch (Exception e) {
            popAlertDialog(e);
        }
    }

    public void editCategoryFilter(ActionEvent actionEvent) {
        CategoryEditDialog dialog = new CategoryEditDialog(new ArrayList<>(categoryTBV.getItems()), queryToList(this.categoryFilterField.getText()));
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

    public void clearFiltershandle(ActionEvent event) {
        nameFilterField.clear();
        categoryFilterField.clear();
        ratingMinField.clear();
        ratingMaxField.clear();
        try {
            movieModel.restoreMovieTBV();
        } catch (Exception e) {
            popAlertDialog(e);
        }
    }
}