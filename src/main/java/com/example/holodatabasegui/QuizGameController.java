package com.example.holodatabasegui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class QuizGameController {
    private SceneManager sceneManager;
    private Library library;

    @FXML
    private Label phraseLabel, scoreLabel, correctOrIncorrectLabel, answerLabel;
    @FXML
    private TextField answerTextField;
    @FXML
    private Button submitButton, nextButton, playAgainButton;
    int userScore = 0;
    int questionCount = 0;
    ArrayList<Talent> quizTalents;
    int numberOfRoundsClass;

    public void playQuiz(int numberOfRounds){
        quizTalents = library.quizGame(numberOfRounds);
        numberOfRoundsClass = numberOfRounds;
        nextQuestion();
    }

    public void nextQuestion(){
        questionCount++;
        if(numberOfRoundsClass >= questionCount){
            phraseLabel.setText(quizTalents.getFirst().getCatchphrase());
            answerTextField.setText("");
            answerLabel.setText("");
            correctOrIncorrectLabel.setText("");
            nextButton.setDisable(true);
            playAgainButton.setVisible(false);
            while(answerTextField.textProperty() == null) {
                submitButton.setDisable(true);
            }
            submitButton.setDisable(false);
        } else {
            gameOver();
        }
    }

    public void handleSubmitAnswer(){
        String currentTalentName = quizTalents.getFirst().getTalentName();
        String firstName;
        String lastName = "";
        if(currentTalentName.contains(" ")){
            firstName = currentTalentName.substring(0, currentTalentName.indexOf(" "));
            lastName = currentTalentName.substring(currentTalentName.indexOf(" ") + 1);
        } else {
            firstName = currentTalentName;
        }

        answerLabel.setText("The correct answer was: " + quizTalents.getFirst().getTalentName());
        nextButton.setDisable(false);
        String userAnswer = answerTextField.getText().trim();
            if((firstName.equalsIgnoreCase(userAnswer)) || (lastName.equalsIgnoreCase(userAnswer) || (currentTalentName.equalsIgnoreCase(userAnswer)))){
                userScore++;
                correctOrIncorrectLabel.setText("Correct!");
                correctOrIncorrectLabel.setStyle("-fx-color: green;");
            } else {
                correctOrIncorrectLabel.setText("Incorrect :(");
                correctOrIncorrectLabel.setStyle("-fx-color: red;");
            }
        quizTalents.remove(quizTalents.getFirst());
        scoreLabel.setText("Score: " + userScore + "/" + questionCount);
        submitButton.setDisable(true);
    }

    public void gameOver(){
        questionCount--;
        scoreLabel.setText("Final Score: " + userScore + "/" + questionCount);
        submitButton.setDisable(true);
        nextButton.setDisable(true);
        playAgainButton.setVisible(true);
    }

    public void handlePlayQuiz(){
        questionCount = 0;
        userScore = 0;
        answerTextField.setText("");
        answerLabel.setText("");
        correctOrIncorrectLabel.setText("");
        phraseLabel.setText("");
        scoreLabel.setText("");
        submitButton.setDisable(false);
        nextButton.setDisable(false);
        playAgainButton.setVisible(false);
        playQuiz(numberOfRoundsClass);
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void switchToHome() throws IOException{
        sceneManager.switchToHome();
    }

}
