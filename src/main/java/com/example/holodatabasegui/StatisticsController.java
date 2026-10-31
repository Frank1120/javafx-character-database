package com.example.holodatabasegui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Map;

public class StatisticsController {
    private SceneManager sceneManager;
    private Library library;

    @FXML
    private GridPane statsGridPane;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setLibrary(Library library) {
        this.library = library;
        populateStats();
    }

    public void switchToHome() throws IOException {
        sceneManager.switchToHome();
    }

    public void populateStats(){
        Map<String, Number> stats = library.generateStatistics();

        statsGridPane.add(new Label("Total Talents: "), 0, 0);
        statsGridPane.add(new Label(stats.get("total") + ""), 1, 0);

        statsGridPane.add(new Label("Talents Per Branch: "), 0, 1);
        statsGridPane.add(new Label("JP: " + stats.get("JP") + "| EN: " + stats.get("EN") + "| ID: " + stats.get("ID")), 1, 1);

        statsGridPane.add(new Label("Avg Talents Per Generation: "),0, 2);
        statsGridPane.add(new Label(stats.get("avg per gen") + ""),1, 2);

        statsGridPane.add(new Label("Talents Per Status: "),0, 3);
        statsGridPane.add(new Label("Active: " + stats.get("active") + " | Alum: " + stats.get("alum") + " | Affiliate: " + stats.get("affiliate")),1, 3);

        statsGridPane.add(new Label("Talents Per Debut Year: "),0, 4);
        statsGridPane.add(new Label("Talents Per Debut Year: "),1, 4);

        statsGridPane.add(new Label("2017: "),0, 5);
        statsGridPane.add(new Label(stats.get("2017") + ""),1, 5);

        statsGridPane.add(new Label("2018: "),0, 6);
        statsGridPane.add(new Label(stats.get("2018") + ""),1, 6);

        statsGridPane.add(new Label("2019: "),0, 7);
        statsGridPane.add(new Label(stats.get("2019") + ""),1, 7);

        statsGridPane.add(new Label("2020: "),0, 8);
        statsGridPane.add(new Label(stats.get("2020") + ""),1, 8);

        statsGridPane.add(new Label("2021: "),0, 9);
        statsGridPane.add(new Label(stats.get("2021") + ""),1, 9);

        statsGridPane.add(new Label("2022: "),0, 10);
        statsGridPane.add(new Label(stats.get("2022") + ""),1, 10);

        statsGridPane.add(new Label("2023: "),0, 11);
        statsGridPane.add(new Label(stats.get("2023") + ""),1, 11);

        statsGridPane.add(new Label("2024: "),0, 12);
        statsGridPane.add(new Label(stats.get("2024") + ""),1, 12);

        statsGridPane.add(new Label("2025: "),0, 13);
        statsGridPane.add(new Label(stats.get("2025") + ""),1, 13);

        if(!library.getOshiLists().isEmpty()){
            statsGridPane.add(new Label("Talents Per List Created: "), 0, 14);
            int i = 15;
            for(OshiList list : library.getOshiLists()){
                statsGridPane.add(new Label(list.getListName() + ":"),0, i);
                statsGridPane.add(new Label(stats.get(list.getListName()) + ""), 1, i);
                i++;
            }
        }



    }
}
