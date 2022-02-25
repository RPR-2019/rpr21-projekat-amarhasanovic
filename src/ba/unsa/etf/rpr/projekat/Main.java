package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScreen.fxml"));
//        LoginScreenController ctrl = new LoginScreenController();
//        loader.setController(ctrl);
//        Parent root = loader.load();
//        primaryStage.setTitle("Login");
//
//        Scene scene = new Scene(root);
//        scene.setFill(Color.TRANSPARENT);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(scene);
//        ctrl.setStage(primaryStage);
//        primaryStage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeScreen.fxml"));
        HomeScreenController ctrl = new HomeScreenController();
        loader.setController(ctrl);
        Parent root = loader.load();
        primaryStage.setTitle("Login");

        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        ctrl.setStage(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
