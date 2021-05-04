package io.giodude.sportsu.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.giodude.sportsu.Adapter.LeagueAdapter;
import io.giodude.sportsu.Model.LeagueModel;
import io.giodude.sportsu.R;
import io.giodude.sportsu.ViewModel.FootballViewModel;

public class LeagueView extends Fragment {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private LeagueAdapter leagueAdapter;
    private List<LeagueModel.League> leagueModel = new ArrayList<>();
    private FootballViewModel footballViewModel;
    private ProgressBar progressBar;
    TextView vis;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_league_view, container, false);
        progressBar = view.findViewById(R.id.progress);
        vis = view.findViewById(R.id.visi);
        showLeague();
        return view;
    }

    private void getLeaguess(List<LeagueModel.League> league) {
        recyclerView = view.findViewById(R.id.leagueRecyclerview);
        rvLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayout);
        leagueAdapter = new LeagueAdapter(getActivity(), league);
        recyclerView.setAdapter(leagueAdapter);
    }

    private void showLeague() {
        footballViewModel = ViewModelProviders.of(LeagueView.this).get(FootballViewModel.class);
        footballViewModel.initLeague();

        footballViewModel.getLeague().observe(this, new Observer<List<LeagueModel.League>>() {
            @Override
            public void onChanged(List<LeagueModel.League> leagues) {
                if (leagues.size() == 0 || leagues == null) {
                    Toast.makeText(getContext(),"Can't Connect to the Server! Please Check you Internet Connection!",Toast.LENGTH_LONG).show();
                    vis.setVisibility(View.VISIBLE);
                } else {
                    if (leagues.size() == 0){
                        progressBar.setVisibility(View.VISIBLE);
                        vis.setVisibility(View.VISIBLE);
                    }else {
                        vis.setVisibility(View.GONE);
                        progressBar.setVisibility(View.INVISIBLE);
                        getLeaguess(leagues);
                        leagueModel.addAll(leagues);
                        leagueAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}