package ba.unsa.etf.rpr.projekat.Beans;

import javafx.beans.property.SimpleStringProperty;

public class Advertisment {
    private SimpleStringProperty title, description, type;
    private User creatorOfAd;

    public Advertisment() {

    }
    public Advertisment(SimpleStringProperty title, SimpleStringProperty description, SimpleStringProperty type, User creatorOfAd) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.creatorOfAd = creatorOfAd;
    }
    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public User getCreatorOfAd() {
        return creatorOfAd;
    }

    public void setCreatorOfAd(User creatorOfAd) {
        this.creatorOfAd = creatorOfAd;
    }
}
