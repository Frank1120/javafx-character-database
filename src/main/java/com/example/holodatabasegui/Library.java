package com.example.holodatabasegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Library{

    Scanner scanner = new Scanner(System.in);
    private final ArrayList<Generation> gens;
    private final ArrayList<Branch> branches;
    private final ObservableList<OshiList> oshiLists;
    private final ObservableList<Talent> talents;
    private int languageChoice;
    private int measurementChoice;

    public Library() {
        this.talents = FXCollections.observableArrayList();
        this.oshiLists = FXCollections.observableArrayList();
        this.gens = new ArrayList<>();
        this.branches = new ArrayList<>();
    }

    public void readData(int languageChoice){
        String filepath = null;
        if(languageChoice == 1){
            filepath = "TalentsJP.txt";
        } else if(languageChoice == 2){
            filepath = "TalentsEN.txt";
        } else {
            filepath = "TalentsNative.txt";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM-dd");

        InputStream stream = getClass().getResourceAsStream("data/" + filepath);

        if(stream == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Could not locate: " + filepath);
            alert.setContentText("Good luck!");
            alert.showAndWait();
            return;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tempArray = line.split(":");
                String branch = tempArray[0].trim();
                String gen = tempArray[1].trim();
                String talentName = tempArray[2].trim();
                String status = tempArray[3].trim();
                int height = Integer.parseInt(tempArray[4].trim());
                LocalDate debutDate = LocalDate.parse(tempArray[5].trim(), formatter);
                MonthDay birthday = MonthDay.parse(tempArray[6].trim(), monthFormatter);
                String catchphrase = tempArray[7].trim();

                Branch currentBranch = null;
                Generation currentGen = null;
                boolean foundBranch = false;
                boolean foundGen = false;

                for (int i = 0; i < this.branches.size(); i++) {
                    if (this.branches.get(i).getBranchName().equals(branch)) {
                        currentBranch = branches.get(i);
                        foundBranch = true;
                        break;
                    }
                }

                if (!foundBranch) {
                    this.branches.add(new Branch(branch));
                    currentBranch = branches.get(branches.size() - 1);
                }


                for (int i = 0; i < this.gens.size(); i++) {
                    if (this.gens.get(i).getGenName().equals(gen)) {
                        if (this.gens.get(i).getGenBranch().equals(currentBranch)) {
                            currentGen = gens.get(i);
                            foundGen = true;
                            break;
                        }
                    }
                }

                if (!foundGen) {
                    this.gens.add(new Generation(gen, currentBranch));
                    currentGen = gens.getLast();
                }
                Talent talent = new Talent(talentName, currentGen, currentBranch, status, height, debutDate, birthday, catchphrase);
                talents.add(talent);
                currentGen.addGenTalent(talent);
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Data could not be loaded");
            alert.showAndWait();
        }
    }

    public void setUserPreferences(int language, int measurement){
        this.measurementChoice = measurement;
        this.languageChoice = language;
    }

    public void addList(OshiList list){
        oshiLists.add(list);
    }

    public void removeList(OshiList list){
        oshiLists.remove(list);
    }

    public void addTalentToList(Talent talentToAdd, OshiList list) {
        list.addTalentToList(talentToAdd);
    }

    public void removeTalentFromList(Talent talentToRemove, OshiList list){
        list.removeTalentFromList(talentToRemove);
    }

    public Map<String, Number> generateStatistics(){
        Map<String, Number> stats = new HashMap<>();

        int jpTalents = 0;
        int enTalents = 0;
        int idTalents = 0;
        for(Talent talent : talents){
            if(talent.getBranch().getBranchName().equals("JP")){
                jpTalents++;
            } else if(talent.getBranch().getBranchName().equals("EN")){
                enTalents++;
            } else {
                idTalents++;
            }
        }
        double averageTalentsPerGen = ((double) talents.size() / gens.size());
        int activeTalents = 0;
        int alumTalents = 0;
        int affiliateTalents = 0;
        for(Talent talent : talents){
            if(talent.getStatus().equals("active")){
                activeTalents++;
            } else if(talent.getStatus().equals("alum")){
                alumTalents++;
            } else {
                affiliateTalents++;
            }
        }
        int talents2017 = 0;
        int talents2018 = 0;
        int talents2019 = 0;
        int talents2020 = 0;
        int talents2021 = 0;
        int talents2022 = 0;
        int talents2023 = 0;
        int talents2024 = 0;
        int talents2025 = 0;
        for(Talent talent : talents) {
            switch(talent.getDebutDate().getYear()) {
                case 2017 -> talents2017++;
                case 2018 -> talents2018++;
                case 2019 -> talents2019++;
                case 2020 -> talents2020++;
                case 2021 -> talents2021++;
                case 2022 -> talents2022++;
                case 2023 -> talents2023++;
                case 2024 -> talents2024++;
                case 2025 -> talents2025++;
            }
        }

        stats.put("total", talents.size());
        stats.put("JP", jpTalents);
        stats.put("EN", enTalents);
        stats.put("ID", idTalents);
        String temp = String.valueOf(averageTalentsPerGen);
        temp = temp.substring(0, 4);
        averageTalentsPerGen = Double.parseDouble(temp);
        stats.put("avg per gen", averageTalentsPerGen);
        stats.put("active", activeTalents);
        stats.put("alum", alumTalents);
        stats.put("affiliate", affiliateTalents);
        stats.put("2017", talents2017);
        stats.put("2018", talents2018);
        stats.put("2019", talents2019);
        stats.put("2020", talents2020);
        stats.put("2021", talents2021);
        stats.put("2022", talents2022);
        stats.put("2023", talents2023);
        stats.put("2024", talents2024);
        stats.put("2025", talents2025);
        for(OshiList list : oshiLists){
            stats.put(list.getListName(), list.getListMembers().size());
        }


        return stats;
    }

    public ArrayList<Talent> debutAnniversariesToday(){
        LocalDate date = LocalDate.now();
        ArrayList<Talent> anniversariesToday = new ArrayList<>();
        for(Talent talent : talents) {
            if(talent.getDebutDate().getDayOfMonth() == date.getDayOfMonth() && talent.getDebutDate().getMonth() == date.getMonth()){
                anniversariesToday.add(talent);
            }
        }
        return anniversariesToday;
    }

    public ArrayList<Talent> birthdaysToday(){
        LocalDate date = LocalDate.now();
        ArrayList<Talent> birthdaysToday = new ArrayList<>();
        for(Talent talent : talents) {
            if(talent.getBirthday().getDayOfMonth() == date.getDayOfMonth() && talent.getBirthday().getMonth() == date.getMonth()) {
                birthdaysToday.add(talent);
            }
        }
        return birthdaysToday;
    }

    public ArrayList<Talent> quizGame(int numberOfRounds) {
        Random random = new Random();
        ArrayList<Talent> quizTalents = new ArrayList<>();
        quizTalents.clear();
        for (int i = 0; i < numberOfRounds; i++) {
            Talent talent = talents.get(random.nextInt(talents.size()));
            if (quizTalents.isEmpty()) {
                quizTalents.add(talent);
            } else {
                if (quizTalents.contains(talent)) {
                    i--;
                } else {
                    quizTalents.add(talent);
                }
            }
        }
        return quizTalents;
    }

    private Path getListPath(int languageChoice){
        String filename;

        if(languageChoice == 1){
            filename = "OshilistsJP.txt";
        } else if(languageChoice == 2){
            filename = "OshilistsEN.txt";
        } else {
            filename = "OshilistsNative.txt";
        }

        return Path.of(
                System.getProperty("user.home"), "HoloDatabaseGUI", filename
        );
    }

    public boolean saveLists(int languageChoice) {
        Path filepath = getListPath(languageChoice);

        try {
            Files.createDirectories(filepath.getParent());
            int count = 0;

            try (FileWriter writer = new FileWriter(filepath.toFile())) {
                if (!oshiLists.isEmpty()) {
                    for (OshiList list : oshiLists) {
                        writer.write("List: " + count + ": " + list.getListName() + "\n");
                        for (Talent talent : list.getListMembers()) {
                            writer.write(talent.getTalentName() + ": ");
                            writer.write(talent.getGen().getGenName() + ": ");
                            writer.write(talent.getBranch().getBranchName() + ": ");
                            writer.write(talent.getStatus() + "\n");
                        }
                        count++;
                    }
                } else {
                    writer.write("");
                }
            }
            return true;
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Good luck!");
            alert.showAndWait();
            return false;
        }
    }

    public void loadLists(int languageChoice) {

        Path filepath = getListPath(languageChoice);

        if (Files.exists(filepath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath.toFile()))) {
                String line;
                int listNumber = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("List: ")) {
                        String[] tempArray = line.split(":", 3);
                        String listNumberAsString = tempArray[1].trim();
                        listNumber = Integer.parseInt(listNumberAsString);

                        String listName = tempArray[2].trim();
                        oshiLists.add(new OshiList(listName, new ArrayList<>()));
                    } else {
                        String[] tempArray = line.split(":");
                        String talentName = tempArray[0].trim();

                        for (Talent talent : talents) {
                            if (talent.getTalentName().equals(talentName)) {
                                oshiLists.get(listNumber).addToListFromSave(talent);
                            }
                        }
                    }
                }
            } catch(FileNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Oshilists could not be located.");
                alert.setContentText("Good luck!");
                alert.showAndWait();
                }
             catch (IOException e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error!");
                 alert.setHeaderText("Something went wrong");
                 alert.setContentText("Good luck!");
                 alert.showAndWait();
            }
        }
    }

    public ObservableList<Talent> getTalentsList(){
        return this.talents;
    }

    public int getMeasurement(){
        return this.measurementChoice;
    }

    public ObservableList<OshiList> getOshiLists(){
        return this.oshiLists;
    }

    public int getLanguageChoice(){
        return this.languageChoice;
    }
}