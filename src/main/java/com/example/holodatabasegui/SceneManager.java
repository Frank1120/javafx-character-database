package com.example.holodatabasegui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private Stage stage;
    private Scene scene;
    private Library library;

    public SceneManager(Stage stage, Library library) {
        this.stage = stage;
        this.library = library;
    }

    public void switchToAllTalents() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllTalents.fxml"));
        Parent root = loader.load();

        AllTalentsController allTalentsController = loader.getController();
        allTalentsController.setSceneManager(this);
        allTalentsController.setLibrary(this.library);

        scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToOshiLists() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OshiLists.fxml"));
        Parent root = loader.load();

        OshiListsController oshiListsController = loader.getController();
        oshiListsController.setSceneManager(this);
        oshiListsController.setLibrary(this.library);

        scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToStatistics() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistics.fxml"));
        Parent root = loader.load();

        StatisticsController statisticsController = loader.getController();
        statisticsController.setSceneManager(this);
        statisticsController.setLibrary(this.library);

        scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToDebutAnniversaries() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DebutAnniversaries.fxml"));
        Parent root = loader.load();

        DebutAnniversaryController debutAnniversaryController = loader.getController();
        debutAnniversaryController.setSceneManager(this);
        debutAnniversaryController.setLibrary(this.library);

        scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToQuizGame() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizGame.fxml"));
        Parent root = loader.load();

        QuizGameController quizGameController = loader.getController();
        quizGameController.setSceneManager(this);
        quizGameController.setLibrary(this.library);

        scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToQuizGame(int numberOfRounds) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizGame.fxml"));
        Parent root = loader.load();

        QuizGameController quizGameController = loader.getController();
        quizGameController.setSceneManager(this);
        quizGameController.setLibrary(this.library);

        scene = new Scene(root);

        quizGameController.playQuiz(numberOfRounds);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();

        HelloController helloController = loader.getController();
        helloController.setSceneManager(this);
        helloController.setLibrary(this.library);

        scene = new Scene(root);

        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

}
