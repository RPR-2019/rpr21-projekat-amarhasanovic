package ba.unsa.etf.rpr.projekat;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeScreenController {
    private Stage stage;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private TilePane tilePane;
    private Button btnSelektovanaSlika;

    @FXML
    public void initialize(){
        try {
            sideBarSetUp();

            tilePane.getChildren().clear();
            VBox vbox = FXMLLoader.load(getClass().getResource("/fxml/Advertisment.fxml"));
            tilePane.getChildren().add(vbox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sideBarSetUp() throws IOException {
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
    public void maximizeAction(ActionEvent actionEvent) { this.stage.setMaximized(!this.stage.isMaximized()); }
}
