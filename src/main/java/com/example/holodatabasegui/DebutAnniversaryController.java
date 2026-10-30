package com.example.holodatabasegui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DebutAnniversaryController {
    private SceneManager sceneManager;
    private Library library;

    @FXML
    private VBox anniversaryVBox, birthdayVBox;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setLibrary(Library library) {
        this.library = library;
        populateData();
    }

    public void switchToHome() throws IOException{
        sceneManager.switchToHome();
    }

    public void populateData(){
        ArrayList<Talent> anniversaries = library.debutAnniversariesToday();
        ArrayList<Talent> birthdays = library.birthdaysToday();
        LocalDate date = LocalDate.now();
        if(!anniversaries.isEmpty()){
            for(Talent talent : anniversaries){
                int anniversaryYear = (date.getYear() - talent.getDebutDate().getYear());
                anniversaryVBox.getChildren().add(new Label(talent.getTalentName() + " - " + anniversaryYear + "th Anniversary!!"));
            }
        } else {
            anniversaryVBox.getChildren().add(new Label("No Debut Anniversaries Today!"));
        }
        if(!birthdays.isEmpty()){
            for(Talent talent : birthdays){
                birthdayVBox.getChildren().add(new Label("Today is " + talent.getTalentName() + "'s Birthday! Happy Birthday!"));
            }
        } else {
            birthdayVBox.getChildren().add(new Label("No Talent's Birthdays Today!"));
        }
    }
}
