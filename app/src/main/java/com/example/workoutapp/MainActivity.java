package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_history)
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
        String filedir = getFilesDir().getPath();
        AppFileManager appFile = new AppFileManager(filedir);

    }
    public void add_page(View view) {
        Intent intent = new Intent(this, AddClass.class);
        startActivity(intent);
    }
    public void exercise_page(View view) {
        Intent intent = new Intent(this, ExerciseClass.class);
        startActivity(intent);
    }

    public static class HomeFragment extends Fragment {

        private HomeViewModel homeViewModel;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            homeViewModel =
                    new ViewModelProvider(this).get(HomeViewModel.class);
            View root = inflater.inflate(R.layout.fragment_home, container, false);
            final TextView textView = root.findViewById(R.id.text_home);
            homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
        }
    }

    public static class HomeViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public HomeViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is home fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
    }

    public static class NotificationsFragment extends Fragment {

        private NotificationsViewModel notificationsViewModel;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            notificationsViewModel =
                    new ViewModelProvider(this).get(NotificationsViewModel.class);
            View root = inflater.inflate(R.layout.fragment_history, container, false);
            final TextView textView = root.findViewById(R.id.text_notifications);
            notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
        }
    }

    public static class NotificationsViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public NotificationsViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is notifications fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
    }
}