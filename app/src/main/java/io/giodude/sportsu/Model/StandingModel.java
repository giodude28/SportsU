package io.giodude.sportsu.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingModel {
    @SerializedName("table")
    @Expose
    private List<Table> table = null;

    public class Table{
        @SerializedName("idStanding")
        @Expose
        private String idStanding;
        @SerializedName("intRank")
        @Expose
        private String intRank;
        @SerializedName("idTeam")
        @Expose
        private String idTeam;
        @SerializedName("strTeam")
        @Expose
        private String strTeam;
        @SerializedName("strTeamBadge")
        @Expose
        private String strTeamBadge;
        @SerializedName("idLeague")
        @Expose
        private String idLeague;
        @SerializedName("strLeague")
        @Expose
        private String strLeague;
        @SerializedName("strSeason")
        @Expose
        private String strSeason;
        @SerializedName("strForm")
        @Expose
        private String strForm;
        @SerializedName("strDescription")
        @Expose
        private String strDescription;
        @SerializedName("intPlayed")
        @Expose
        private String intPlayed;
        @SerializedName("intWin")
        @Expose
        private String intWin;
        @SerializedName("intLoss")
        @Expose
        private String intLoss;
        @SerializedName("intDraw")
        @Expose
        private String intDraw;
        @SerializedName("intGoalsFor")
        @Expose
        private String intGoalsFor;
        @SerializedName("intGoalsAgainst")
        @Expose
        private String intGoalsAgainst;
        @SerializedName("intGoalDifference")
        @Expose
        private String intGoalDifference;
        @SerializedName("intPoints")
        @Expose
        private String intPoints;
        @SerializedName("dateUpdated")
        @Expose
        private String dateUpdated;

        public Table(String idStanding, String intRank, String idTeam, String strTeam, String strTeamBadge, String idLeague, String strLeague, String strSeason, String strForm, String strDescription, String intPlayed, String intWin, String intLoss, String intDraw, String intGoalsFor, String intGoalsAgainst, String intGoalDifference, String intPoints, String dateUpdated) {
            this.idStanding = idStanding;
            this.intRank = intRank;
            this.idTeam = idTeam;
            this.strTeam = strTeam;
            this.strTeamBadge = strTeamBadge;
            this.idLeague = idLeague;
            this.strLeague = strLeague;
            this.strSeason = strSeason;
            this.strForm = strForm;
            this.strDescription = strDescription;
            this.intPlayed = intPlayed;
            this.intWin = intWin;
            this.intLoss = intLoss;
            this.intDraw = intDraw;
            this.intGoalsFor = intGoalsFor;
            this.intGoalsAgainst = intGoalsAgainst;
            this.intGoalDifference = intGoalDifference;
            this.intPoints = intPoints;
            this.dateUpdated = dateUpdated;
        }

        public String getIdStanding() {
            return idStanding;
        }

        public String getIntRank() {
            return intRank;
        }

        public String getIdTeam() {
            return idTeam;
        }

        public String getStrTeam() {
            return strTeam;
        }

        public String getStrTeamBadge() {
            return strTeamBadge;
        }

        public String getIdLeague() {
            return idLeague;
        }

        public String getStrLeague() {
            return strLeague;
        }

        public String getStrSeason() {
            return strSeason;
        }

        public String getStrForm() {
            return strForm;
        }

        public String getStrDescription() {
            return strDescription;
        }

        public String getIntPlayed() {
            return intPlayed;
        }

        public String getIntWin() {
            return intWin;
        }

        public String getIntLoss() {
            return intLoss;
        }

        public String getIntDraw() {
            return intDraw;
        }

        public String getIntGoalsFor() {
            return intGoalsFor;
        }

        public String getIntGoalsAgainst() {
            return intGoalsAgainst;
        }

        public String getIntGoalDifference() {
            return intGoalDifference;
        }

        public String getIntPoints() {
            return intPoints;
        }

        public String getDateUpdated() {
            return dateUpdated;
        }
    }
    public List<Table> getTable() {
        return table;
    }
}
