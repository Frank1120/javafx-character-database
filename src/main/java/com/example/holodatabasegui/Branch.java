package com.example.holodatabasegui;

import java.util.ArrayList;

public class Branch {
    private final ArrayList<Generation> generations;
    private final String branchName;

    public Branch(String branchName) {
        this.generations = new ArrayList<>();
        this.branchName = branchName;
    }

    public String getBranchName(){
        return branchName;
    }

    public ArrayList<Generation> getGenerations(){
        return generations;
    }

    @Override
    public String toString(){
        return branchName;
    }
}
