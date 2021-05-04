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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.giodude.sportsu.Adapter.StandingAdapter;
import io.giodude.sportsu.Adapter.TeamAdapter;
import io.giodude.sportsu.Adapter.TeamIconAdapter;
import io.giodude.sportsu.Model.StandingModel;
import io.giodude.sportsu.Model.TeamModel;
import io.giodude.sportsu.R;
import io.giodude.sportsu.ViewModel.FootballViewModel;

public class HomeView extends Fragment {
    private static RecyclerView recyclerView,recyclerView1;
    private RecyclerView.LayoutManager rvLayout,rvLayout1;
    private List<StandingModel.Table> tableModel = new ArrayList<>();
    private List<TeamModel.Team> teamModel = new ArrayList<>();
    private StandingAdapter standingAdapter;
    private TeamIconAdapter teamIconAdapter;
    private FootballViewModel footballViewModel;
    public ProgressBar progressBar;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private CarouselView carouselView;
    public ArrayList<Integer> carouselpics = new ArrayList<>();


    TextView vis;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_view, container, false);
        progressBar = view.findViewById(R.id.progress);
        vis = view.findViewById(R.id.visi);
        carouselView = view.findViewById(R.id.carouselView);
//        swipeRefreshLayout = view.findViewById(R.id.swipe);
        showTeam();
        showTable();
        showPics();
//        swipeRefreshLayout.setOnRefreshListener(() -> {
//            showTeam();
////            showPics();
//            showTable();
//            swipeRefreshLayout.setRefreshing(false);
//        });
        return  view;
    }

    public void showPics()
    {
        carouselpics.addAll(Arrays.asList(R.drawable.banner1,R.drawable.banner2,R.drawable.banner3));
        carouselView.setImageListener((position, imageView) -> {

            ArrayList<Integer> imageListTitle = new ArrayList<Integer>();
            for(int i = 0; i < carouselpics.size(); i++ ){
                imageListTitle.add(carouselpics.get(i));
            }
            //imageView.setImageResource(imagelist[position]);
            Picasso.get().load(imageListTitle.get(position)).into(imageView);
        });
        carouselView.setPageCount(carouselpics.size());
    }
    private void getTable(List<StandingModel.Table> tables) {
        recyclerView = view.findViewById(R.id.tableRecyclerview);
        rvLayout = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(rvLayout);
        standingAdapter = new StandingAdapter(getActivity(),tables);
        recyclerView.setAdapter(standingAdapter);
    }
    private void getIcons(List<TeamModel.Team> teamModel) {
        recyclerView1 = view.findViewById(R.id.teamsRecyclerview);
        rvLayout1 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView1.setLayoutManager(rvLayout1);
        teamIconAdapter = new TeamIconAdapter(getActivity(), teamModel);
        recyclerView1.setAdapter(teamIconAdapter);
    }

    private void showTeam() {
        footballViewModel = ViewModelProviders.of(HomeView.this).get(FootballViewModel.class);
        footballViewModel.init();

        footballViewModel.getTeams().observe(this, teams -> {
            if (teams == null) {
                System.out.println("No connection");
//                Toast.makeText(getContext(),"Can't Connect to the Server! Please Check you Internet Connection!",Toast.LENGTH_LONG).show();
//                vis.setVisibility(View.VISIBLE);
            } else {
                if (teams.size() == 0){
                    System.out.println("Null Data");
//                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    getIcons(teams);
                    teamModel.addAll(teams);
                    teamIconAdapter.notifyDataSetChanged();
                }
            }

        });

    }
    private void showTable(){
        footballViewModel = ViewModelProviders.of(HomeView.this).get(FootballViewModel.class);
        footballViewModel.initTable();
        footballViewModel.getTable().observe(this, tables -> {
            if (tables == null){
                Toast.makeText(getContext(),"Can't Connect to the Server! Please Check you Internet Connection!",Toast.LENGTH_LONG).show();
                vis.setVisibility(View.VISIBLE);
            }else {
                if (tables.size() == 0){
                    vis.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No Available Data!",Toast.LENGTH_LONG).show();
                }else {
                    vis.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                    getTable(tables);
                    tableModel.addAll(tables);
                    standingAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}