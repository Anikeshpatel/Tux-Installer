package grp.anon.tux.main;

import grp.anon.tux.Design;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ScrollPane scroller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.setTitle("Tux Alert");
        alert.show();
    }
}
