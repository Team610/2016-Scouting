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
                toReturn = renderID(team);
                break;
            case 1:
                toReturn = renderAuton(team);
                break;
            case 2:
                toReturn = renderDefense(team);
                break;
            case 3:
                toReturn = renderHighPercent(team);
                break;
            case 4:
                toReturn  = renderHighShots(team);
                break;
            case 5:
                toReturn = renderLowPercent(team);
                break;
            case 6:
                toReturn = renderLowShots(team);
                break;
            case 7:
                toReturn = renderHangingPercent(team);
                break;
            case 8:
                toReturn = renderChallengePercent(team);
                break;
            case 9:
                toReturn = renderCourtyardDrops(team);
                break;
        }
        return toReturn;
    }

    private View renderCourtyardDrops(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.avgCourtyardDrops()+"");
        return tv;
    }
    private View renderID(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.id()+"");
        return tv;
    }

    //TODO
    private View renderAuton(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.avgAutonScore()+"");
        return tv;
    }
    private View renderDefense(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.avgDefenceScore()+"");
        return tv;
    }
    private View renderHighPercent(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.highGoalPercentage()+"%");
        return tv;
    }
    private View renderHighShots(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.avgHighGoalScore()+"");
        return tv;
    }
    private View renderLowPercent(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.lowGoalPercentage()+"%");
        return tv;
    }
    private View renderLowShots(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.avgLowGoalScore()+"");
        return tv;
    }
    private View renderHangingPercent(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.hangingPercentage()+"%");
        return tv;
    }
    private View renderChallengePercent(TeamData team) {
        TextView tv = new TextView(getContext());
        tv.setText(team.challengePercentage()+"%");
        return tv;
    }
}
