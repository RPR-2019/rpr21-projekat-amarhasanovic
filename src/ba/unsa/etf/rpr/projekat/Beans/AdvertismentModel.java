package ba.unsa.etf.rpr.projekat.Beans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class AdvertismentModel {
    private static AdvertismentModel instance = null;
    private ObservableList<Advertisment> advertisments = FXCollections.observableArrayList();

    private PreparedStatement getAllAdvertismentsStatement, findAdvertismentStatement, getAllUsersStatement;
    private Connection conn;

    public AdvertismentModel(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:advertisment.db");
            getAllUsersStatement = conn.prepareStatement("SELECT * FROM users");
        }catch (SQLException e){
            reloadDataBase();
            try{
                getAllUsersStatement = conn.prepareStatement("SELECT * FROM users");
                getAllAdvertismentsStatement = conn.prepareStatement("SELECT * FROM advertisment");
                findAdvertismentStatement = conn.prepareStatement("SELECT * FROM advertisment WHERE title LIKE %?% OR description LIKE %?%");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    public static AdvertismentModel getInstance(){
        if(instance == null)
            instance = new AdvertismentModel();
        return instance;
    }
    public static void removeInstance(){
        if(instance != null){
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }
    private void reloadDataBase(){
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("advertisment_dump.sql"));
            String sqlUpit = "";
            while(input.hasNext()){
                sqlUpit+=input.nextLine();
                if(sqlUpit.length()>1 && sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt=conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("SQL file does not exist, contiuing with empty file");
            e.printStackTrace();
        }
    }
    public void returnOnDefaultSetUp() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM korisnik");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reloadDataBase();
    }
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Advertisment> getAdvertisments() {
        return advertisments;
    }

    public void setAdvertisments(ObservableList<Advertisment> advertisments) {
        this.advertisments = advertisments;
    }
}
