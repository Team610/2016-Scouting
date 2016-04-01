package com.team610.scouting.masterapp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team610.scouting.masterapp.team.TeamData;

import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class StandingsFragment extends ScoutingFragment {


    public StandingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false);
    }

    @Override
    public void updateViewsFromThe6ix() throws NoSuchFieldException, IllegalAccessException {
        for (SimulatedMatch m : MainActivity.simMatches) {
            double blueRP = m.blueCapture / 100D + (m.blueBreach ? 1 : 0) + (m.redWin ? 0 : 2);
            System.out.println("BLUE RP " + blueRP);
            for (int i = 0; i < 3; i++) {
                TeamData t = m.blue[i];
                if (!MainActivity.simulatedRP.containsKey(t.id)) {
                    MainActivity.simulatedRP.put(t.id, t.RP + blueRP);
                } else {
                    MainActivity.simulatedRP.put(t.id, MainActivity.simulatedRP.get(t.id) + blueRP);
                }
            }
            double redRP =  m.redCapture / 100D + (m.redBreach ? 1 : 0) + (m.redWin ? 2 : 0);
            System.out.println("RED RP " + redRP);
            for (int i = 0; i < 3; i++) {
                TeamData t = m.blue[i];
                if (!MainActivity.simulatedRP.containsKey(t.id)) {
                    MainActivity.simulatedRP.put(t.id, t.RP + redRP);
                } else {
                    MainActivity.simulatedRP.put(t.id, MainActivity.simulatedRP.get(t.id) + redRP);
                }
            }
        }

        SortableTableView<TeamData> table = (SortableTableView) getActivity().findViewById(R.id.standings_table);
        TeamData[] teams = new TeamData[MainActivity.teams.size()];
        int i = 0;
        for (TeamData t : MainActivity.teams.values()) {
            teams[i++] = t;
        }
        table.setDataAdapter(new StandingsTableAdapter(getActivity(), teams));
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getActivity(), "Team", "RP");
        table.setHeaderAdapter(headerAdapter);
        table.setColumnComparator(0, new idComparator());
        table.setColumnComparator(1, new RPComparator());


    }
    private static class idComparator implements Comparator<TeamData> {

        public int compare(TeamData one, TeamData two) {
            return (one.id) - (two.id);
        }
    }
    private static class RPComparator implements Comparator<TeamData> {
        public int compare(TeamData one, TeamData two) {
            return Double.compare(MainActivity.simulatedRP.get(one.id), MainActivity.simulatedRP.get(two.id));
        }
    }
    private class StandingsTableAdapter extends TableDataAdapter<TeamData> {

        public StandingsTableAdapter(Context activity, TeamData[] data) {
            super(activity, data);

        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            TeamData team = getRowData(rowIndex);
            if (columnIndex == 0) {
                TextView tv = new TextView(getContext());
                tv.setText(team.id() + "");
                return tv;
            } else {
                TextView tv = new TextView(getContext());
                tv.setText(MainActivity.simulatedRP.get(team.id) + "");
                return tv;
            }
        }
    }
}
