package loginsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package loginsystem;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class Controller {
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    String logInUserName="";
    int logInCondition = 00;
    
    //Login Form FXML elements
    @FXML
    TextField userId;
    @FXML
    PasswordField passId;
    
    //Sign Up Form FXML elements
    @FXML
    TextField su_userId;
    @FXML
    TextField su_email;
    @FXML
    PasswordField su_passId;
    @FXML
    Label su_status_label;
    
    
    @FXML 
    Label welcome_label;
    
    //Database Tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    public Connection connectDB(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/new_login?autoReconnect=true&useSSL=false","root","habib1221");
            System.out.println("Connected Successfully");
            return connect;
        }
        catch(Exception e){e.printStackTrace();}
        return null;
    }
    public void login(ActionEvent event)
    {
        connect = connectDB();
        try{
            String sql = ("SELECT * FROM user_info WHERE user_name = ? AND pass = ?");
            statement = connect.prepareStatement(sql);
            statement.setString(1, userId.getText());
            statement.setString(2, passId.getText());
            result = statement.executeQuery(); 
            if(result.next())
            {
                logInUserName = userId.getText();
                root = FXMLLoader.load(getClass().getResource("dashBoard.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                System.out.println("Wrong user name or Password");
            }
            welcome_label.setText("HI");
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    
    
    
    public void signup(ActionEvent event)
    {
        connect = connectDB();
        if(su_userId.getText().equals(""))
        {
            su_status_label.setText("Plese enter valid data");
        }
        else
        {
            try 
            {
                su_status_label.setText("");
                String sql = "INSERT INTO user_info (user_name,pass,email) values(?,?,?)";
                statement = connect.prepareStatement(sql);
                statement.setString(1, su_userId.getText());
                statement.setString(2, su_passId.getText());
                statement.setString(3, su_email.getText());
                statement.execute();
                System.out.println("Successfully entered");
                su_userId.setText("");
                su_passId.setText(null);
                su_email.setText(null);
                su_status_label.setText("sucessfully entered");
            }
            catch(SQLException e){e.printStackTrace();}
        }
        
        
        
    }
    
    
    public void gosignup(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gologin(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        
}
