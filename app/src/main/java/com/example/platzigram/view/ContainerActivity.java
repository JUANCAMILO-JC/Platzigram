package com.example.platzigram.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.platzigram.R;
import com.example.platzigram.view.fragment.HomeFragment;
import com.example.platzigram.view.fragment.ProfileFragment;
import com.example.platzigram.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity {
    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottombar);

        addFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.home:
                        addFragment(new HomeFragment());
                        viewIsAtHome = true;
                        break;
                    case R.id.profile:
                        addFragment(new ProfileFragment());
                        viewIsAtHome = false;
                        break;
                    case R.id.search:
                        addFragment(new SearchFragment());
                        viewIsAtHome = false;
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!viewIsAtHome) { //if the current view is not the News fragment
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottombar);
            bottomNavigationView.setSelectedItemId(R.id.home); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }


    }

}

