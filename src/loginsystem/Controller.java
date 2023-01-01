/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Habib
 */
public class Controller {
    @FXML
    TextField userId;
    
    @FXML
    PasswordField passId;
    String userStr; 
    String passStr;
    public void loginBtn(ActionEvent event)
    {
       userStr = userId.getText();
       passStr= passId.getText();
       if(userStr.equals("habib")&&passStr.equals("123"))
       {
           System.out.println("Successfully Logged In");
       }
       System.out.println(userStr+" "+passStr);
    }
}
