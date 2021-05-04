package io.giodude.sportsu.Network;

import java.util.List;

import io.giodude.sportsu.Model.LeagueModel;
import io.giodude.sportsu.Model.PastEventModel;
import io.giodude.sportsu.Model.StandingModel;
import io.giodude.sportsu.Model.TeamModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TeamApiService {

    String TEAM_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("search_all_teams.php")
    Call<TeamModel> getTeam(@Query("l")String team);


    String LEAGUE_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("all_leagues.php")
    Call<LeagueModel> getLeague();


    String PAST_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("eventspastleague.php")
    Call<PastEventModel> getPast(@Query("id")String past);


    String TABLE_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("lookuptable.php")
    Call<StandingModel> getStanding(@Query("l")String team,
                                    @Query("s")String season);
}
