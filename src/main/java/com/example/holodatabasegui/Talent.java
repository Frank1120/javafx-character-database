package com.example.holodatabasegui;

import java.time.LocalDate;
import java.time.MonthDay;

public class Talent implements Comparable<Talent>{

    private final String talentName;
    private final Generation gen;
    private final Branch branch;
    private final String status;
    private final int height;
    private final LocalDate debutDate;
    private final MonthDay birthday;
    private final String catchphrase;

    public Talent(String talentName, Generation gen, Branch branch, String status, int height, LocalDate debutDate, MonthDay birthday, String catchphrase) {
        this.talentName = talentName;
        this.gen = gen;
        this.branch = branch;
        this.status = status;
        this.height = height;
        this.debutDate = debutDate;
        this.birthday = birthday;
        this.catchphrase = catchphrase;
    }

    @Override
    public int compareTo(Talent o) {
        // Compare based on talent alphabetization
        int compareInt = this.getTalentName().compareToIgnoreCase(o.getTalentName());
                if(compareInt < 0) return -1;
                if(compareInt > 0) return 1;
                return 0;
    }

    public String toStringBranchImperial(){
        int cm = getHeight();
        double inches = (cm / 2.54);
        int feet = (int) (inches / 12);
        double remainingInches = (inches % 12);
        int remainingInchesRounded = (int) Math.round(remainingInches);

        if(remainingInchesRounded == 12){
            feet++;
            remainingInchesRounded = 0;
        }
        String talentHeight = feet + "'" + remainingInchesRounded + "\"";

        return talentName +" | Generation: " + getGen().getGenName() + " | Status: " + getStatus() + " | Height: " + talentHeight + " | Birthday: " + getBirthday().toString().substring(2) + " | Debut Date: " + getDebutDate();
    }

    public String toStringBranchMetric(){
        return talentName +" | Generation: " + getGen().getGenName() + " | Status: " + getStatus() + " | Height: " + height + "cm | Birthday: " + getBirthday().toString().substring(2) + " | Debut Date: " + getDebutDate();
    }

    public String getFormattedHeight(int measurementChoice) {
            if (measurementChoice == 1) {
                int cm = height;
                double inches = (cm / 2.54);
                int feet = (int) (inches / 12);
                double remainingInches = (inches % 12);
                int remainingInchesRounded = (int) Math.round(remainingInches);

                if (remainingInchesRounded == 12) {
                    feet++;
                    remainingInchesRounded = 0;
                }
                String height = feet + "'" + remainingInchesRounded;
                return height;
            } else {
                return this.height + "cm";
            }
    }

    public String toStringMetric(){
        return talentName + " | Height: " + height + "cm";
    }

    public String getTalentName(){
        return talentName;
    }

    public Generation getGen(){
        return gen;
    }

    public Branch getBranch(){
        return branch;
    }

    public String getStatus(){
        return status;
    }

    public Integer getHeight(){
        return height;
    }

    public LocalDate getDebutDate(){
        return debutDate;
    }

    public MonthDay getBirthday(){
        return birthday;
    }

    public String getCatchphrase(){
        return catchphrase;
    }
}
