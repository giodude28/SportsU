package io.giodude.sportsu.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.giodude.sportsu.Model.LeagueModel;
import io.giodude.sportsu.Model.PastEventModel;
import io.giodude.sportsu.Model.StandingModel;
import io.giodude.sportsu.Model.TeamModel;
import io.giodude.sportsu.Network.NetworkModule;
import io.giodude.sportsu.Network.Repository;
import io.giodude.sportsu.Network.team;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FootballViewModel extends ViewModel {

    private MutableLiveData<List<TeamModel.Team>> teams;
    private MutableLiveData<List<LeagueModel.League>> league;
    private MutableLiveData<List<PastEventModel.Event>> past;
    private MutableLiveData<List<StandingModel.Table>> table;
    public Repository repositories;

    public void init(){
        if (teams != null){
            return;
        }
        repositories = Repository.getInstance();
        teams = repositories.getTeam(team.teamid);

    }

    public void initLeague(){
        if (league != null){
            return;
        }
        repositories = Repository.getInstance();
        league = repositories.getLeague();
    }

    public void initPast(){
        if (past != null){
            return;
        }
        repositories = Repository.getInstance();
        past = repositories.getPast(team.pastid);
    }

    public void initTable(){
        if (table != null){
            return;
        }
        repositories = Repository.getInstance();
        table = repositories.getTable(team.pastid, team.seasond);
    }


    public LiveData<List<TeamModel.Team>> getTeams(){
        return teams;
    }

    public LiveData<List<LeagueModel.League>> getLeague(){
        return league;
    }

    public LiveData<List<PastEventModel.Event>> getPast(){
        return past;
    }

    public LiveData<List<StandingModel.Table>> getTable(){
        return table;
    }
}
