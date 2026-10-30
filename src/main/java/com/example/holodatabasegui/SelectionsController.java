package com.example.holodatabasegui;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectionsController implements Initializable {
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private ComboBox<String> measurementComboBox;
    @FXML
    private Button continueButton;
    private int languageChoice = 0;
    private int measurementChoice = 0;

    private SceneManager sceneManager;
    private Library library;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BooleanBinding languageBoxEmpty = languageComboBox.valueProperty().isNull();
        BooleanBinding measurementBoxEmpty = measurementComboBox.valueProperty().isNull();

        languageComboBox.getItems().add("Japanese");
        languageComboBox.getItems().add("English");
        languageComboBox.getItems().add("Native");

        measurementComboBox.getItems().add("Imperial");
        measurementComboBox.getItems().add("Metric");

        continueButton.setDisable(false);

        continueButton.disableProperty().bind(languageBoxEmpty.or(measurementBoxEmpty));
    }

    public void switchToHome() throws IOException {
        if(languageComboBox.getValue().equalsIgnoreCase("Japanese")){
            languageChoice = 1;
        } else if(languageComboBox.getValue().equalsIgnoreCase("English")){
            languageChoice = 2;
        } else {
            languageChoice = 3;
        }
        if(measurementComboBox.getValue().equalsIgnoreCase("Imperial")){
            measurementChoice = 1;
        } else {
            measurementChoice = 2;
        }
        library.setUserPreferences(languageChoice, measurementChoice);
        library.readData(languageChoice);
        library.loadLists(languageChoice);
        sceneManager.switchToHome();
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
