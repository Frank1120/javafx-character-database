package com.example.holodatabasegui;

import java.util.Comparator;

public class heightComparator implements Comparator<Talent>{

    @Override
    public int compare(Talent talent1, Talent talent2) {
        int compareInt = talent1.getHeight().compareTo(talent2.getHeight());
        if(compareInt < 0) return -1;
        if(compareInt > 0) return 1;
        if(compareInt == 0 ) {
            int compareNumber = talent1.getTalentName().compareTo(talent2.getTalentName());
            if(compareNumber < 0) return -1;
            if(compareNumber > 0) return 1;
        }
        return 0;
    }
}