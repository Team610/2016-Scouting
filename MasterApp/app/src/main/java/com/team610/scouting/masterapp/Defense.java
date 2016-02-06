package com.team610.scouting.masterapp;

/**
 * Created by Tate on 2016-02-06.
 */
public enum Defense
{
    PORTCULLIS('A'),
    CHEVAL_DE_FRISE('A'),
    MOAT('B'),
    RAMPARTS('B'),
    DRAWBRIDGE('C'),
    SALY_PORT('C'),
    ROCK_WALL('D'),
    ROUGH_TERRAIN('D'),
    LOW_BAR('E');

    final char category;


    Defense(char cat){
        category = cat;
    }

    public char getCategory() {
        return category;
    }
}
