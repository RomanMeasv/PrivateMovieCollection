package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pmcollection.be.Category;
import pmcollection.gui.view.dialogs.CategoryDialog;
import pmcollection.gui.view.dialogs.CategoryEditDialog;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieDialogController {

    @FXML
    public TextField txtFieldName;
    @FXML
    public TextField txtFieldRating;
    @FXML
    public DatePicker dpLastView;
    @FXML
    public TextField txtFieldLink;
    @FXML
    public ComboBox<Category> comboBoxCategories;

    private List<Category> allCategories;

    public MovieDialogController(){
        allCategories = new ArrayList<>();
    }

    public String getName() {
        return this.txtFieldName.getText();
    }

    public List<Category> getCategories() {
        //roman take care of this after you are finished with checkcombobox pls
        List<Category> categories = new ArrayList<>();
        categories.add(this.comboBoxCategories.getSelectionModel().getSelectedItem());
        return categories;
    }

    public float getRating() {
        String text = this.txtFieldRating.getText();
        return Float.parseFloat(text);
    }

    public LocalDate getLastview() {
        return this.dpLastView.getValue();
    }

    public String getFilelink() {
        return this.txtFieldLink.getText();
    }

    public void setName(String name) {
        this.txtFieldName.setText(name);
    }

    public void setCategories(List<Category> categories) {

        this.comboBoxCategories.setItems(FXCollections.observableList(categories));
    }

    public void setRating(float rating) {
        this.txtFieldRating.setText(""+rating);
    }

    public void setLastView(LocalDate time) {
        this.dpLastView.getValue();
    }

    public void setLink(String link) {
        this.txtFieldLink.setText(link);
    }

    public void handleChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file resource");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP4 File", "*.mp4")
        );
        Node source = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            String filePath = file.getPath();
            txtFieldLink.setText(filePath);
        }
    }

    public void editCategories(ActionEvent actionEvent) {
        CategoryEditDialog dialog = new CategoryEditDialog(this.allCategories, this.getCategories());
        Optional<List<Category>> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.setCategories(response);
            } catch (Exception ignored) {

            }
        });
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }
}
