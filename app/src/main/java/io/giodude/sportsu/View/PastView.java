package io.giodude.sportsu.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

import io.giodude.sportsu.Adapter.PastEventAdapter;
import io.giodude.sportsu.Model.PastEventModel;
import io.giodude.sportsu.R;
import io.giodude.sportsu.ViewModel.FootballViewModel;

public class PastView extends Fragment {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private PastEventAdapter pastEventAdapter;
    private List<PastEventModel.Event> pastModel = new ArrayList<>();
    private FootballViewModel footballViewModel;
    public ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    TextView vis;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_past_view, container, false);
        progressBar = view.findViewById(R.id.progress);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        vis = view.findViewById(R.id.visi);
        showPast();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            showPast();
            swipeRefreshLayout.setRefreshing(false);
        });
        return view;
    }

    private void getPast(List<PastEventModel.Event> past) {
        recyclerView = view.findViewById(R.id.pastRecyclerview);
        rvLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayout);
        pastEventAdapter = new PastEventAdapter(getActivity(), past);
        recyclerView.setAdapter(pastEventAdapter);
    }

    private void showPast() {
        footballViewModel = ViewModelProviders.of(PastView.this).get(FootballViewModel.class);
        footballViewModel.initPast();
        footballViewModel.getPast().observe(this, events -> {
            if(events == null)
            {
                Toast.makeText(getContext(),"Can't Connect to the Server! Please Check you Internet Connection!",Toast.LENGTH_LONG).show();
                vis.setVisibility(View.VISIBLE);
            }else{
                if (events.size() == 0){
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No Available Data!",Toast.LENGTH_LONG).show();
                    vis.setVisibility(View.VISIBLE);
                }else {
                    vis.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                    getPast(events);
                    pastModel.addAll(events);
                    pastEventAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}