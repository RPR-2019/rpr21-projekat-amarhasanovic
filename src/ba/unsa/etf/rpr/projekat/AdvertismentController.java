package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdvertismentController {
    @FXML
    private ImageView jobImageView;
    private Image picture;

    @FXML
    private void initialize(){
        jobImageView.setImage(picture);
    }
    public AdvertismentController(String picture) {
        this.picture = new Image(picture, true);
    }
}
