package com.example.holodatabasegui;

import java.util.ArrayList;

public class Generation {

    private final String genName;
    private final ArrayList<Talent> generationTalents;
    private final Branch genBranch;

    public Generation(String genName, Branch genBranch){
        this.genName = genName;
        this.generationTalents = new ArrayList<>();
        this.genBranch = genBranch;
    }

    public void addGenTalent(Talent talent) {
        if(this.generationTalents.contains(talent)){
        } else {
            this.generationTalents.add(talent);
        }
    }

    public String getGenName(){
        return genName;
    }

    public Branch getGenBranch(){
        return genBranch;
    }


    public ArrayList<Talent> getGenerationTalents(){
        return generationTalents;
    }

    @Override
    public String toString(){
        return genName;
    }
}
