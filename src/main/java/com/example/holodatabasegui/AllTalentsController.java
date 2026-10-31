package com.example.holodatabasegui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllTalentsController implements Initializable {

    @FXML
    private TableView<Talent> myTableView;
    @FXML
    private TableColumn<Talent, String> nameColumn, branchColumn, generationColumn, statusColumn, birthdayColumn, debutDateColumn;
    @FXML
    private TableColumn<Talent, Integer> heightColumn;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField searchBox;

    private SceneManager sceneManager;
    private Library library;

    public void switchToHome() throws IOException {
        sceneManager.switchToHome();
    }

    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    public void setLibrary(Library library){
        this.library = library;
        myTableView.setItems(this.library.getTalentsList());
        setFilteredTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // maybe do height calculation here, or make a method in library to do so and call it here
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("talentName"));
        generationColumn.setCellValueFactory(new PropertyValueFactory<>("gen"));
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        heightColumn.setCellFactory(column -> new TableCell<>(){
            @Override
            protected void updateItem(Integer height, boolean empty){
                super.updateItem(height, empty);
                if(empty || height == null) {
                    setText(null);
                } else {
                    Talent talent = getTableView().getItems().get(getIndex());
                    setText(talent.getFormattedHeight(library.getMeasurement()));
                }
            }
        });


        debutDateColumn.setCellValueFactory(new PropertyValueFactory<>("debutDate"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        comboBox.getItems().add("All Branches");
        comboBox.getItems().add("EN");
        comboBox.getItems().add("JP");
        comboBox.getItems().add("ID");

    }

    public void setFilteredTableView(){
        ObservableList<Talent> masterTalentList = library.getTalentsList();
        FilteredList<Talent> filteredList = new FilteredList<>(masterTalentList, p -> true);

        Runnable updateFilter = () -> {
            String search = searchBox.getText().trim().toLowerCase();
            String branch = comboBox.getValue();

            filteredList.setPredicate(talent -> {
                boolean matchesName = talent.getTalentName()
                    .toLowerCase().contains(search);

                boolean matchesBranch = branch == null ||
                    branch.equals("All Branches") ||
                    talent.getBranch().getBranchName()
                        .equalsIgnoreCase(branch);

                return matchesName && matchesBranch;
            });
        };

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> updateFilter.run());
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> updateFilter.run());
        updateFilter.run();

        SortedList<Talent> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(myTableView.comparatorProperty());

        myTableView.setItems(sortedList);

    }
}
