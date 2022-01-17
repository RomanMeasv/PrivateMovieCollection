package pmcollection.gui.controller.dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


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
