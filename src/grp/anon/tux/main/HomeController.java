package grp.anon.tux.main;

import grp.anon.tux.Design;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Pane dragPane;

    @FXML
    private Label dragMsg;

    @FXML
    private Label logoText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void dragDroped(DragEvent event) {
        try {
            List<File> files = event.getDragboard().getFiles();
            if (!(files.size() > 2)){
                File software = files.get(0);
                File icon = files.get(1);
                Design design = new Design(software,icon);
            }
            else {
                showAlert("Please Drag Only AppImage & Icon");
            }
        }catch (Exception e){

        }
    }

    @FXML
    void dragOver(DragEvent event) {
        if (event.getDragboard().hasFiles())
            event.acceptTransferModes(TransferMode.ANY);
    }

    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.setTitle("Tux Alert");
        alert.show();
    }
}
