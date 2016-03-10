package com.team610.scouting.masterapp;

/**
 * Created by Tate on 2016-02-06.
 */
public enum Defence {
    PORTCULLIS('A',"porticullis"),
    CHEVAL_DE_FRISE('A',"chevaldefrise"),
    MOAT('B',"moat"),
    RAMPARTS('B',"ramparts"),
    DRAWBRIDGE('C',"drawbridge"),
    SALLY_PORT('C',"sallyport"),
    ROCK_WALL('D',"rockwall"),
    ROUGH_TERRAIN('D',"roughterrain"),
    LOW_BAR('E',"lowbar");

    final char category;
    final String id;

    Defence(char cat, String id) {
        category = cat;
        this.id = id;

    }

    public char getCategory() {
        return category;
    }


    public static Defence getDefence(String id){
        for(Defence d : Defence.values()){
            if(d.id.equals(id))
                return d;
        }
        return null;
    }
}
