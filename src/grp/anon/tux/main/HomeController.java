package grp.anon.tux.main;

import grp.anon.tux.Launcher;
import grp.anon.tux.process.Process;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private AnchorPane root;
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

    private Stage processStage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        processStage = new Stage();


        appImage.setOnDragOver(e -> {
            if (e.getDragboard().hasFiles()) {
                e.acceptTransferModes(TransferMode.ANY);
            }
        });

        appImage.setOnMouseClicked(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File("/home/"+System.getProperty("user.name")+"/Downloads"));
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Choose AppImage","*.appimage","*.Appimage","*.AppImage","*.APPIMAGE"));
            File softwareFile = chooser.showOpenDialog(Launcher.getMainStage());

            try {
                Process.setSoftwareFile(softwareFile);
                processStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../process/process.fxml"))));
                processStage.initStyle(StageStyle.UNDECORATED);
                processStage.initOwner(Launcher.getMainStage());
                processStage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        appImage.setOnDragDropped(e -> {
            List<File> files = e.getDragboard().getFiles();
            File softwareFile = files.get(0);
            if (softwareFile.getName().contains(".appimage") || softwareFile.getName().contains(".AppImage") || softwareFile.getName().contains(".Appimage")){
                try {
                    Process.setSoftwareFile(softwareFile);
                    processStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../process/process.fxml"))));
                    processStage.initStyle(StageStyle.UNDECORATED);
                    processStage.initOwner(Launcher.getMainStage());
                    processStage.show();
                } catch (IOException e1) {

                }
            }else {
                showAlert("Please Drop AppImage File");
            }
        });

        tar.setOnMouseClicked(e -> {
            showAlert("This Feature Is In Beta Mode");
        });

        executable.setOnMouseClicked(e -> {
            showAlert("This Feature Is In Beta Mode");
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
        exit.setOnMouseClicked(e ->{
            System.exit(0);
        });
    }

    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.setTitle("Tux Alert");
        alert.show();
    }
}
