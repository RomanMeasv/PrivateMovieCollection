package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import pmcollection.be.Category;
import pmcollection.gui.view.dialogs.CategoryEditDialog;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieDialogController implements Initializable{

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


    public void initialize(URL location, ResourceBundle resources) {
        LocalDate maxDate = LocalDate.now();
        dpLastView.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate));
                    }});
    }

    public String getName() {
        return this.txtFieldName.getText();
    }

    public List<Category> getCategories() {
        return new ArrayList<>(this.comboBoxCategories.getItems());
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
        comboBoxCategories.getSelectionModel().select(0);
    }

    public void setRating(float rating) {
        this.txtFieldRating.setText(""+rating);
    }

    public void setLastView(LocalDate time) {
        this.dpLastView.setValue(time);
    }

    public void setLink(String link) {
        this.txtFieldLink.setText(link);
    }

    public void handleChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file resource");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP4/MPEG4 Files", "*.mp4", "*.mpeg4")
        );
        Node source = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            String filePath = file.getPath();
            txtFieldLink.setText(filePath);
        }
    }

    public void editCategories() {
        CategoryEditDialog dialog = new CategoryEditDialog(this.getCategories());
        Optional<List<Category>> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                this.setCategories(response);
            } catch (Exception e) {
                popAlertDialog(e);
            }
        });
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
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
}
