package com.example.holodatabasegui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OshiListsController implements Initializable {
    private SceneManager sceneManager;
    private Library library;

    @FXML
    private ListView<OshiList> myOshiListListView;
    @FXML
    TableView<Talent> myOshiListTableView;
    @FXML
    private TableColumn<Talent, String> nameColumn, branchColumn, generationColumn;
    @FXML
    private Button addTalentButton;
    @FXML
    private Button removeTalentButton;

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setLibrary(Library library) {
        this.library = library;
        myOshiListListView.setItems(library.getOshiLists());
    }

    public void createList(){
        final boolean[] alreadyMade = {false};

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Create New Oshi List");
        dialog.setHeaderText("Enter the name for your new list.");
        dialog.setContentText("List Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(listName -> {
            if(!listName.trim().isEmpty()) {
                for (OshiList list : library.getOshiLists()) {
                    if (list.getListName().equals(listName)) {
                        alreadyMade[0] = true;
                        break;
                    }
                }
                if (!alreadyMade[0]) {
                    ArrayList<Talent> oshiListTalents = new ArrayList<>();
                    OshiList list = new OshiList(listName, oshiListTalents);
                    library.addList(list);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Duplicate List");
                    alert.setHeaderText("A list with this name already exists.");
                    alert.showAndWait();
                }
                alreadyMade[0] = false;
            }
        });
    }

    public void removeList(){
        OshiList list = myOshiListListView.getSelectionModel().getSelectedItem();
        myOshiListListView.getItems().remove(list);
        library.removeList(list);
        myOshiListListView.refresh();
    }

    public void addTalent(){
        OshiList list = myOshiListListView.getSelectionModel().getSelectedItem();
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Add Talent");
        dialog.setHeaderText("Type talent name, any talent with the string of characters in a row will be added.");
        dialog.setContentText("example: Kia will add Kiara and Kaela Kovalskia (hint: type at least their first or last name)");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(searchText -> {
            if(!searchText.trim().isEmpty()){
                for(Talent talent : library.getTalentsList()){
                    if(talent.getTalentName().toLowerCase().contains(searchText.trim().toLowerCase())){
                        library.addTalentToList(talent, list);
                    }
                }
            }
        });
        myOshiListTableView.setItems(FXCollections.observableArrayList(list.getListMembers()));
        myOshiListTableView.refresh();
    }

    public void removeTalent(){
        Talent talent = myOshiListTableView.getSelectionModel().getSelectedItem();
        OshiList list = myOshiListListView.getSelectionModel().getSelectedItem();
        library.removeTalentFromList(talent, list);
        myOshiListTableView.setItems(FXCollections.observableArrayList(list.getListMembers()));
        myOshiListTableView.refresh();
    }

    public void switchToHome() throws IOException {
        sceneManager.switchToHome();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addTalentButton.disableProperty().bind(myOshiListListView.getSelectionModel().selectedItemProperty().isNull());
        removeTalentButton.disableProperty().bind(myOshiListListView.getSelectionModel().selectedItemProperty().isNull());

        myOshiListListView.setCellFactory(listView -> new ListCell<OshiList>(){
            protected void updateItem(OshiList oshiList, boolean empty){
                super.updateItem(oshiList, empty);
                if(empty || oshiList == null){
                    setText(null);
                } else {
                    setText(oshiList.getListName());
                }
            }
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("talentName"));
        generationColumn.setCellValueFactory(new PropertyValueFactory<>("gen"));
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        myOshiListListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OshiList>() {
            @Override
            public void changed(ObservableValue<? extends OshiList> observableValue, OshiList oldSelection, OshiList newSelection) {
                if(newSelection != null){
                    ObservableList<Talent> talents = FXCollections.observableArrayList(newSelection.getListMembers());
                    myOshiListTableView.setItems(talents);
                } else {
                    myOshiListTableView.getItems().clear();
                }

            }
        });

    }
}
