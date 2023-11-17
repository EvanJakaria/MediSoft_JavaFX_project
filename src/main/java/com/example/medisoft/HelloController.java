package com.example.medisoft;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    Label msg = new Label();
    @FXML
    TextField username = new TextField();
    @FXML
    TextField password = new TextField();
    @FXML
    private ImageView exit;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    public void loginButton(ActionEvent event) throws SQLException, IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "Select Username,Password from LoginInfo";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);


            while (queryOutput.next()) {
                if(username.getText().equals(queryOutput.getString("Username")) && password.getText().equals(queryOutput.getString("Password"))){

                    System.out.println("login successfully!");


                    //login scene
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viewOptionPage.fxml"));
                    root = fxmlLoader.load();
                    scene = new Scene(root);

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setX(100);
                    stage.setY(100);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                }else{
                    //System.out.println("login failed");
                    msg.setText("Login failed! (username or password is wrong). Please try agin...");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void signUpButton(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viewRegPage.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
    }
}
