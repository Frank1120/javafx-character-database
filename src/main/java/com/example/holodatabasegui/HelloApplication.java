package com.example.holodatabasegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Selections.fxml"));
        Parent root = fxmlLoader.load();

        SelectionsController selectionsController = fxmlLoader.getController();
        Library library = new Library();
        SceneManager sceneManager = new SceneManager(stage, library);

        selectionsController.setLibrary(library);
        selectionsController.setSceneManager(sceneManager);

        Scene scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Hololive Database");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            if(library.getLanguageChoice() == 0){
                return;
            }
            
            event.consume();
            logout(stage, library);
        });
    }

    public void logout(Stage stage, Library library){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK){
            if(library.saveLists(library.getLanguageChoice())){
                stage.close();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}