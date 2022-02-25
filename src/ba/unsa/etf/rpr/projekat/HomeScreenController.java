package ba.unsa.etf.rpr.projekat;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HomeScreenController {
    private Stage stage;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;

    @FXML
    public void initialize(){
        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
            drawer.setSidePane(vbox);
            HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * (-1));
                burgerTask.play();
                if(drawer.isOpened()){
                    drawer.close();
                }else{
                    drawer.open();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void exitAction(ActionEvent actionEvent){
        System.exit(0);
    }
    public void minimizeAction(ActionEvent actionEvent){
        this.stage.setIconified(true);
    }
}
