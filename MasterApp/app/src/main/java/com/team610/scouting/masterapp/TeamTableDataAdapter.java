package com.team610.scouting.masterapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                toReturn = renderAuton(team);
                break;
            case 1:
                toReturn = renderDefense(team);
                break;
            case 2:
                toReturn = renderHighPercent(team);
                break;
            case 3:
                toReturn  = renderHighShots(team);
                break;
            case 4:
                toReturn = renderLowPercent(team);
                break;
            case 5:
                toReturn = renderLowShots(team);
                break;
            case 6:
                toReturn = renderHangingPercent(team);
                break;
            case 7:
                toReturn = renderChallengePercent(team);
                break;
            case 8:
                toReturn = renderHangingPercent(team);
                break;
        }
        return toReturn;
    }
    //TODO
    private View renderAuton(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.avgAutonScore);
        return tv;
    }
}
