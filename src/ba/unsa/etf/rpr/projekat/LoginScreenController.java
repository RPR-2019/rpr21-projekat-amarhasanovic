package ba.unsa.etf.rpr.projekat;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginScreenController {
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

    @FXML
    public void createAccountAction(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterScene.fxml"));
        RegisterSceneController ctrl = new RegisterSceneController();
        loader.setController(ctrl);
        Parent root = null;
        try {
            Stage secondaryStage = new Stage();
            root = loader.load();
            secondaryStage.setTitle("Register");

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);
            secondaryStage.setScene(scene);
            ctrl.setStage(secondaryStage);
            secondaryStage.show();
            this.stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
