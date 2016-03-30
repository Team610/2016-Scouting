package com.team610.scouting.masterapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.team610.scouting.masterapp.team.TeamData;

import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MetricsFragment extends ScoutingFragment {


    public MetricsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_metrics, container, false);
        return v;
    }

    @Override
    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        SortableTableView<TeamData> table = (SortableTableView) getActivity().findViewById(R.id.metric_display_table);

        TeamData[] teams = new TeamData[MainActivity.teams.size()];
        int i = 0;
        for (TeamData t : MainActivity.teams.values()) {
            teams[i++] = t;
        }
        table.setDataAdapter(new MetricsTableAdapter(this, getActivity(), teams));
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getActivity(), "Team", "Auton", "Defence", "High %", "High Shots", "Low %", "Low Shots", "Hang %", "Challenge %", "Total Points");
        table.setHeaderAdapter(headerAdapter);
        table.setColumnComparator(0, new idComparator());
        table.setColumnComparator(1,new sumComparator(this));
    }
    private static class idComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            return (one.id) - (two.id);
        }
    }
    private static class sumComparator implements Comparator<TeamData> {
        MetricsFragment i;
        sumComparator(MetricsFragment i){
            this.i = i;
        }
        public int compare(TeamData one, TeamData two) {
            return (int) (i.getSum(one) - i.getSum(two));
        }
    }
    public boolean[] checkedBoxes(){
        TableLayout table = (TableLayout)  getActivity().findViewById(R.id.metric_table);
        boolean[] toReturn = new boolean[table.getChildCount()];
        for(int i = 0; i < table.getChildCount(); i++){
            toReturn[i] = ((CheckBox) ((TableRow) table.getChildAt(i)).getChildAt(0)).isChecked();
        }
        return toReturn;
    }

    public double getSum(TeamData team){
        double sum = 0D;
        boolean[] checkboxes = checkedBoxes();
        for(int i = 0; i < checkboxes.length;i++){
            if(checkboxes[i]){
                switch(i){
                    case 0:
                        sum+= team.avgDefenceScore();
                        break;
                    case 1:
                        sum+= team.avgAutonScore();
                        break;
                    case 2:
                        sum+= team.avgHighGoalScore();
                    case 3:
                        sum+= team.avgLowGoalScore();
                    case 4:
                        sum+= team.avgTotalPoints();
                }
            }
        }
        return sum;
    }

    private class MetricsTableAdapter extends TableDataAdapter<TeamData> {
        MetricsFragment instance;
        public MetricsTableAdapter(MetricsFragment instance, Context context, TeamData[] data) {
            super(context,data);
            this.instance = instance;
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            TeamData team = getRowData(rowIndex);

            if(columnIndex == 0){
                TextView tv = new TextView(getContext());
                tv.setText(team.id()+"");
                return tv;
            }else{
               TextView tv = new TextView(getContext());
                tv.setText(instance.getSum(team) + "");
                return tv;
            }
        }
    }
}
