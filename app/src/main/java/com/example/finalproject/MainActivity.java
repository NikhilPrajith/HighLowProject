package com.example.finalproject;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // Moving onto the gamemode activity
    public void switchActivity(View view) {
        Intent intent = new Intent(this, ModeActivity.class);
        startActivity(intent);
    }
}