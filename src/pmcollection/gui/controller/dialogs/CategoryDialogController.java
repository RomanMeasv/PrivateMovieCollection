package pmcollection.gui.controller.dialogs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pmcollection.be.Category;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryDialogController {

    @FXML
    public TextField nameField;

    public String getName() {
        return this.nameField.getText();
    }

    public void setName(String name) {
        this.nameField.setText(name);
    }

}
