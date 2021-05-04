package io.giodude.sportsu.Network;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.giodude.sportsu.Model.LeagueModel;
import io.giodude.sportsu.Model.PastEventModel;
import io.giodude.sportsu.Model.StandingModel;
import io.giodude.sportsu.Model.TeamModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository instance;
    static NetworkModule rfit = new NetworkModule();

    public static Repository getInstance(){
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public MutableLiveData<List<TeamModel.Team>> getTeam(String teamleague){
        final MutableLiveData<List<TeamModel.Team>> tData = new MutableLiveData<>();
        Call<TeamModel> call = rfit.retrofitBuilder().getTeam(teamleague);
        call.enqueue(new Callback<TeamModel>() {
            @Override
            public void onResponse(Call<TeamModel> call, Response<TeamModel> response) {
                TeamModel teamList = response.body();
                List<TeamModel.Team> teams = teamList.getTeams();
                tData.setValue(teams);
            }
            @Override
            public void onFailure(Call<TeamModel> call, Throwable t) {
                tData.setValue(null);
            }
        });
        return tData;
    }


    public MutableLiveData<List<LeagueModel.League>> getLeague(){
        final MutableLiveData<List<LeagueModel.League>> lData = new MutableLiveData<>();
        Call<LeagueModel> call = rfit.retrofitBuilderleague().getLeague();
        call.enqueue(new Callback<LeagueModel>() {
            @Override
            public void onResponse(Call<LeagueModel> call, Response<LeagueModel> response) {
                LeagueModel leagueList = response.body();
                List<LeagueModel.League> league = leagueList.getLeagues();
                ArrayList<LeagueModel.League> newData = new ArrayList<>();
                for (int i = 0; i < league.size(); i++){
                    if (league.get(i).getStrSport().equals("Soccer")){
                        newData.add(league.get(i));
                    }
                }

                lData.setValue(newData);
            }

            @Override
            public void onFailure(Call<LeagueModel> call, Throwable t) {
                lData.setValue(null);
            }
        });
        return lData;
    }

    public MutableLiveData<List<PastEventModel.Event>> getPast(String past){
        final MutableLiveData<List<PastEventModel.Event>> pData = new MutableLiveData<>();
        Call<PastEventModel> call = rfit.retrofitBuilderPast().getPast(past);
        call.enqueue(new Callback<PastEventModel>() {
            @Override
            public void onResponse(Call<PastEventModel> call, Response<PastEventModel> response) {
                PastEventModel pastList = response.body();
                List<PastEventModel.Event> pasts = pastList.getEvents();
                pData.setValue(pasts);
            }

            @Override
            public void onFailure(Call<PastEventModel> call, Throwable t) {
                pData.setValue(null);
            }
        });
        return pData;
    }


    public MutableLiveData<List<StandingModel.Table>> getTable(String id,String sea){
        final MutableLiveData<List<StandingModel.Table>> sData = new MutableLiveData<>();
        Call<StandingModel> call = rfit.retrofitBuilderStanding().getStanding(id,sea);
        call.enqueue(new Callback<StandingModel>() {
            @Override
            public void onResponse(Call<StandingModel> call, Response<StandingModel> response) {
                StandingModel standingList = response.body();
                List<StandingModel.Table> table = standingList.getTable();
                sData.setValue(table);
            }

            @Override
            public void onFailure(Call<StandingModel> call, Throwable t) {
                sData.setValue(null);
            }
        });
        return sData;
    }
}

