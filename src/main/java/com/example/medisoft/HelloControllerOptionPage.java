package com.example.medisoft;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloControllerOptionPage implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;


    @FXML
    private ImageView exit,logout,menuBar;

    @FXML
    private AnchorPane pane1,pane2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        logout.setOnMouseClicked(mouseEvent -> {
            try {
                LogOut(mouseEvent);
            } catch (Exception e) {
                System.out.println(e);
            }
        });



        pane1.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.15),pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        menuBar.setOnMouseClicked(MouseEvent->{
            pane1.setVisible(true);
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition.setByX(+600);
            translateTransition.play();
        });

        pane1.setOnMouseClicked(MouseEvent ->{
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition.setFromValue(.15);
            fadeTransition.setToValue(0);
            fadeTransition.play();

            fadeTransition1.setOnFinished(MouseEvent1 -> {
                //menuBar.setVisible(false);
                //pane2.setVisible(false);
                pane1.setVisible(false);
            });

            pane1.setVisible(false);

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition.setByX(-600);
            translateTransition.play();
        });

    }




    @FXML
    public void LogOut(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

}
