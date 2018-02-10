package grp.anon.tux.main;

import grp.anon.tux.Design;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ScrollPane scroller;

    @FXML
    private AnchorPane exit;
    @FXML
    private AnchorPane githubPage;
    @FXML
    private AnchorPane appImage;

    @FXML
    private AnchorPane tar;

    @FXML
    private AnchorPane executable;

    @FXML
    private AnchorPane about;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        exit.setOnMouseClicked(e ->{
            System.exit(0);
        });
        githubPage.setOnMouseClicked(e -> {
            if (Desktop.isDesktopSupported()){
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Anikeshpatel/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        appImage.setOnMouseClicked(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File("/home/"+System.getProperty("user.name")+"/Downloads"));
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Choose AppImage","*.appimage","*.Appimage","*.AppImage","*.APPIMAGE"));
            File softwareFile = chooser.showOpenDialog(null);
        });

        tar.setOnMouseClicked(e -> {
            showAlert("This Feature Is In Beta Mode");
        });

        executable.setOnMouseClicked(e -> {
            showAlert("This Feature Is In Beta Mode");
        });
    }
    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.setTitle("Tux Alert");
        alert.show();
    }
}
