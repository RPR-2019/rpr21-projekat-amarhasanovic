package ba.unsa.etf.rpr.projekat.Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    @FXML
    private GridPane topBar;
    private Button btnSelektovanaSlika;
    private double x = 0, y = 0;
    @FXML
    public void initialize(){
        try {
            screenDragSetUp();
            sideBarSetUp();
            tilePane.getChildren().clear();
            tilePane.getChildren().add(advertisment("demand"));
            tilePane.getChildren().add(advertisment("offer"));
            tilePane.getChildren().add(advertisment("demand"));
            tilePane.getChildren().add(advertisment("offer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void screenDragSetUp() {
        topBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        topBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }
    private VBox advertisment(String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Advertisment.fxml"));
        AdvertismentController ctrl = null;
        if(type.equals("offer"))
            ctrl = new AdvertismentController("/pictures/jobOffer.png");
        else if(type.equals("demand"))
            ctrl = new AdvertismentController("/pictures/jobDemand.png");
        loader.setController(ctrl);
        return loader.load();
    }
    private void sideBarSetUp() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
        drawer.setSidePane(anchorPane);
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
