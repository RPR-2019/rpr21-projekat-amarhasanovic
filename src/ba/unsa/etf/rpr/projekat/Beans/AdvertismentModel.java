package ba.unsa.etf.rpr.projekat.Beans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdvertismentModel {
    private static AdvertismentModel instance = null;

    private PreparedStatement getAllAdvertismentsStatement, findAdvertismentStatement, getAllUsersStatement;
    private PreparedStatement registerNewUserStatement, nextIdStatement;
    private Connection conn;

    public AdvertismentModel(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:advertisment.db");
            preparedStatements();
        }catch (SQLException e){
            reloadDataBase();
            try{
                preparedStatements();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    private void preparedStatements() throws SQLException {
        getAllUsersStatement = conn.prepareStatement("SELECT * FROM users");
        getAllAdvertismentsStatement = conn.prepareStatement("SELECT * FROM advertisment");
        registerNewUserStatement = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?)");
        nextIdStatement = conn.prepareStatement("SELECT max(id) FROM users");
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

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet usersResultSet = getAllUsersStatement.executeQuery();
            while(usersResultSet.next()){
                User newUser = new User(usersResultSet.getInt(1), usersResultSet.getString(2),
                        usersResultSet.getString(3), usersResultSet.getString(4), usersResultSet.getString(5),
                        usersResultSet.getString(6));
                if(usersResultSet.getString(7) != null)
                    newUser.setAddress(usersResultSet.getString(7));
                newUser.setPhoneNumber(usersResultSet.getInt(8));
                if(usersResultSet.getString(9) != null)
                    newUser.setPicture(usersResultSet.getString(9));
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public int getNextId() {
        int id = 0;
        try {
            ResultSet maxID = nextIdStatement.executeQuery();
            id = maxID.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public void registerNewUser(User user){
        try {
            registerNewUserStatement.setString(2, user.getFirstName());
            registerNewUserStatement.setString(3, user.getLastName());
            registerNewUserStatement.setString(4, user.getUsername());
            registerNewUserStatement.setString(5, user.getEmail());
            registerNewUserStatement.setString(6, user.getPassword());
            registerNewUserStatement.setString(7, user.getAddress());
            registerNewUserStatement.setInt(8, user.getPhoneNumber());
            registerNewUserStatement.setString(9, user.getPicture());

            registerNewUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
