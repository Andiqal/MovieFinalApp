package com.example.moviefinalapp.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moviefinalapp.R;
import com.example.moviefinalapp.fragments.FavoriteFragment;
import com.example.moviefinalapp.fragments.MovieFragment;
import com.example.moviefinalapp.network.Const;
import com.example.moviefinalapp.utils.ActionBarTitle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ActionBarTitle {
    private String layoutName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_item_now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.bottom_nav_item_now_playing:
                layoutName = Const.NOW_PLAYING;
                setActionBarTitle(layoutName);
                fragment = new MovieFragment(layoutName);
                break;
            case R.id.bottom_nav_item_upcoming:
                layoutName = Const.UPCOMING;
                setActionBarTitle(layoutName);
                fragment = new MovieFragment(layoutName);
                break;
            case R.id.bottom_nav_item_popular:
                layoutName = Const.POPULAR;
                setActionBarTitle(layoutName);
                fragment = new MovieFragment(layoutName);
                break;
            case R.id.bottom_nav_item_favorite:
                layoutName = Const.FAVORITES;
                setActionBarTitle(layoutName);
                fragment = new FavoriteFragment();
                break;
        }

        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void setActionBarTitle(String title) {
        View view = getLayoutInflater().inflate(R.layout.action_bar,null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
        );

        TextView titleBar = view.findViewById(R.id.tv_ab_title);
        titleBar.setText(title);

        getSupportActionBar().setCustomView(view, params);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}