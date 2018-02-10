package grp.anon.tux.process;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class Process implements Initializable {
    @FXML
    private AnchorPane iconDropPane;

    private static File softwareFile;
    private static File icon;
    private static File installDir,launcherFile;

    @FXML
    private Label exitBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];
        iconDropPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                iconDropPane.setCursor(Cursor.HAND);
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
            }
        });

        iconDropPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                iconDropPane.getScene().getWindow().setX(event.getScreenX() - xOffset[0]);
                iconDropPane.getScene().getWindow().setY(event.getScreenY() - yOffset[0]);
            }
        });

        iconDropPane.setOnDragOver(e -> {
            if (e.getDragboard().hasFiles()){
                e.acceptTransferModes(TransferMode.ANY);
            }
        });

        iconDropPane.setOnDragDropped(e -> {
            List<File> iconFiles = e.getDragboard().getFiles();
            icon = iconFiles.get(0);
            if (icon.getName().contains(".png") || icon.getName().contains(".jpg")){
                installDir = new File("/opt/"+getFileName(softwareFile.getName()));
                launcherFile = new File(installDir.getAbsolutePath()+"/"+getFileName(softwareFile.getName())+".desktop");
                if (!installDir.exists()){
                    installDir.mkdir();
                }
                try {
                    installThisAppImage();
                } catch (IOException e1) {
                }

            }else {
                showAlert("Please Drop Write Icon File");
            }
        });

        exitBtn.setOnMouseClicked(e -> {
            iconDropPane.getScene().getWindow().hide();
        });
    }

    private void installThisAppImage() throws IOException {
        Files.copy(Paths.get(softwareFile.getAbsolutePath()),Paths.get(installDir.getAbsolutePath()));
        Files.copy(Paths.get(icon.getAbsolutePath()),Paths.get(installDir.getAbsolutePath()));
        FileWriter writer = new FileWriter(launcherFile);
        writer.append("[Desktop Entry]\nName="+getFileName(softwareFile.getName())+"\nComment=Utility Software\nExec="+installDir.getAbsolutePath()+softwareFile.getName()+"\nIcon="+installDir.getAbsolutePath()+icon.getName()+"\nTerminal=false\nType=Application\nCategories=Utility");
        writer.close();
    }
    private String getFileName(String name){
        return name.substring(0,name.indexOf('.'));
    }
    public static void setSoftwareFile(File softwareFile){
        Process.softwareFile = softwareFile;
    }
    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.setTitle("Tux Alert");
        alert.show();
    }
}
