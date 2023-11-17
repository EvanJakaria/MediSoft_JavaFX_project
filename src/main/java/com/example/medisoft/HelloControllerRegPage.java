package com.example.medisoft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloControllerRegPage {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    TextField name = new TextField();
    @FXML
    TextField userName = new TextField();
    @FXML
    TextField age = new TextField();
    @FXML
    TextField gender = new TextField();
    @FXML
    TextField contact = new TextField();
    @FXML
    TextField mail = new TextField();
    @FXML
    TextField password = new TextField();
    @FXML
    TextField confirmPassword = new TextField();
    @FXML
    CheckBox checkBox = new CheckBox();
    @FXML
    Label msg = new Label();


    @FXML
    public void registrationButton(ActionEvent event) throws SQLException {

        String Name = name.getText();
        String UserName = userName.getText();
        String Age = age.getText();
        String Mail = mail.getText();
        String Contact = contact.getText();
        String Password = password.getText();
        String ConfirmPassword = confirmPassword.getText();
        String Gender = gender.getText();


        try {

            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement stm = connectDB.createStatement();

            if(checkBox.isSelected() && !Name.isEmpty() && !UserName.isEmpty() && !Age.isEmpty() && !Mail.isEmpty() && !Password.isEmpty() && !ConfirmPassword.isEmpty() && !Gender.isEmpty()){
                msg.setText(null);
                if(Integer.parseInt(Age) >= 18){
                    msg.setText(null);
                    if(Password.equals(ConfirmPassword)) {
                        msg.setText(null);
                        String sql = "Insert into LoginInfo values ('"+Name+"','"+UserName+"','"+Age+"','"+Gender+"','"+Password+"','"+Contact+"','"+Mail+"')";
                        stm.executeUpdate(sql);

                        System.out.println("Registration Successful");

                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                        root = fxmlLoader.load();
                        scene = new Scene(root);

                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    }else{
                        msg.setText("Password & Confirm Password must be same");
                        System.out.println("Registration Failed");
                    }
                }else{
                    msg.setText("Minimum age should be 18");
                    System.out.println("Registration Failed");
                }
            }else{
                msg.setText("Fill up all requires");
                System.out.println("Registration Failed");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void backToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}








