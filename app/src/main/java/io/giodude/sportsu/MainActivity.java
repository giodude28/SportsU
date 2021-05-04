package io.giodude.sportsu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.giodude.sportsu.View.HomeView;
import io.giodude.sportsu.View.LeagueView;
import io.giodude.sportsu.View.PastView;
import io.giodude.sportsu.View.TeamView;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fLayout, new HomeView()).commit();

        declare();
    }

    private void declare()
    {
        navigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {


                case R.id.league:
                    selectedFragment = new LeagueView();
                    break;

                case R.id.homes:
                    selectedFragment = new HomeView();
                    break;

                case R.id.teams:
                    selectedFragment = new TeamView();
                    break;

                case R.id.match:
                    selectedFragment = new PastView();
                    break;
//
//                case R.id.more:
//                    selectedFragment = new MoreView();
//                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fLayout,selectedFragment)
                    .commit();

            return true;
        });
    }

}