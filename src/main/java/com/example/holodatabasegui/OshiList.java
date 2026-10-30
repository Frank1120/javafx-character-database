package com.example.holodatabasegui;

import java.util.ArrayList;

public class OshiList {


    private final String listName;
    private final ArrayList<Talent> listMembers;

    public OshiList(String listName, ArrayList<Talent> listMembers) {
        this.listName = listName;
        this.listMembers = listMembers;
    }

    public void addTalentToList(Talent talent){
        if(!listMembers.contains(talent)){
            this.listMembers.add(talent);
        }
    }

    public void removeTalentFromList(Talent talent){
        this.listMembers.remove(talent);
    }

    public void addToListFromSave(Talent talent){
        this.listMembers.add(talent);
    }

    public String getListName(){
        return this.listName;
    }

    public ArrayList<Talent> getListMembers(){
        return listMembers;
    }

}
