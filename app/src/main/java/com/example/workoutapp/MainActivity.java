package com.example.workoutapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_history)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        if (navView.getSelectedItemId() != R.id.navigation_home)
//        {
//            System.out.println(navView.findViewWithTag(R.id.nav_view));
//            System.out.println(navView.getMenu().findItem(navView.getSelectedItemId()));
//            System.out.println(R.id.navigation_history);
//            System.out.println(R.id.navigation_home);
//            System.out.println(R.id.navigation_add);
//            System.out.println("\n\nIts working\n\n");
//            Log.d("MyApp","I am here");
//        }

    }

}