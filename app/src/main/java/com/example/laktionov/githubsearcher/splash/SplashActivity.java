package com.example.laktionov.githubsearcher.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.laktionov.githubsearcher.R;
import com.example.laktionov.githubsearcher.search.SearchActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
}
