package com.team610.scouting.masterapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.team610.scouting.masterapp.team.TeamData;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by Tate on 2016-02-20.
 */
public class TeamTableDataAdapter  extends TableDataAdapter<TeamData> {
    public TeamTableDataAdapter(Context context, TeamData[] data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
       TeamData team = getRowData(rowIndex);
        View toReturn = null;
        switch (columnIndex){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;

            case 3:

                break;
        }
        return toReturn;
    }
}
