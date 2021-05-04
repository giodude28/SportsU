package io.giodude.sportsu.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.giodude.sportsu.Adapter.TeamAdapter;
import io.giodude.sportsu.Model.TeamModel;
import io.giodude.sportsu.R;
import io.giodude.sportsu.ViewModel.FootballViewModel;

public class TeamView extends Fragment {
    private FootballViewModel footballViewModel;
    private TeamAdapter teamAdapter;
    private List<TeamModel.Team> teamModel = new ArrayList<>();
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager rLayout;
    public ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    TextView vis;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_team_view, container, false);
        vis = view.findViewById(R.id.visi);
        progressBar = view.findViewById(R.id.progress);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        showTeam();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            showTeam();
            swipeRefreshLayout.setRefreshing(false);
        });
        return view;
    }

    private void initRecyclerview(List<TeamModel.Team> teamModel) {
        recyclerView = view.findViewById(R.id.teamRV);
        rLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rLayout);
        teamAdapter = new TeamAdapter(getActivity(), teamModel);
        recyclerView.setAdapter(teamAdapter);
    }

    private void showTeam() {
        footballViewModel = ViewModelProviders.of(TeamView.this).get(FootballViewModel.class);
        footballViewModel.init();

        footballViewModel.getTeams().observe(this, teams -> {
            if (teams == null) {
                Toast.makeText(getContext(),"Can't Connect to the Server! Please Check you Internet Connection!",Toast.LENGTH_LONG).show();
                vis.setVisibility(View.VISIBLE);
            } else {
                if (teams.size() == 0){
                    progressBar.setVisibility(View.VISIBLE);
                    vis.setVisibility(View.VISIBLE);
                }else {
                    vis.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                    initRecyclerview(teams);
                    teamModel.addAll(teams);
                    teamAdapter.notifyDataSetChanged();
                }
            }

        });

    }
}