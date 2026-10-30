package com.example.holodatabasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloController {

    private SceneManager sceneManager;
    private Library library;

    public void switchToAllTalents() throws IOException{
        sceneManager.switchToAllTalents();
    }

    public void switchToOshiLists() throws IOException{
        sceneManager.switchToOshiLists();
    }

    public void switchToStatistics() throws IOException{
        sceneManager.switchToStatistics();
    }

    public void switchToDebutAnniversaries() throws IOException{
        sceneManager.switchToDebutAnniversaries();
    }

    public void switchToQuizGame() throws IOException{
        ChoiceDialog<Integer> rounds = new ChoiceDialog<>();
        rounds.setTitle("Round Choice");
        rounds.setHeaderText("How many rounds would you like to play");
        rounds.setContentText("Select number of rounds:");
        rounds.getItems().add(5);
        rounds.getItems().add(10);
        rounds.getItems().add(15);
        Optional<Integer> result = rounds.showAndWait();

        if(result.isPresent()){
            int numberOfRounds = result.get();
            sceneManager.switchToQuizGame(numberOfRounds);
        }
    }

    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    public void setLibrary(Library library){
        this.library = library;
    }


}