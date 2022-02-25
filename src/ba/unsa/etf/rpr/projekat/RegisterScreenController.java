package ba.unsa.etf.rpr.projekat;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterScreenController {
    public AnchorPane sideBar;
    private Stage stage;
    private double x = 0, y = 0;

    public void initialize(){
        sideBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        sideBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    @FXML
    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
