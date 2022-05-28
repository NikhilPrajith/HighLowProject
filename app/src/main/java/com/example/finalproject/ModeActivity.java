package com.example.finalproject;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // Moving onto the game activity
    public void modeSelect(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        switch (view.getId()) {
            case R.id.netWorth:
                System.out.println("Networth");
                intent.putExtra("buttonId", "Networth");
                break;
            case R.id.youtubers:
                System.out.println("Youtuber");
                intent.putExtra("buttonId", "Youtube");
                break;
            case R.id.groceries:
                System.out.println("Groc");
                intent.putExtra("buttonId", "Grocery");
                break;

            case R.id.height:
                System.out.println("h");
                intent.putExtra("buttonId", "Heights");
                break;
        }

        startActivity(intent);
    }
}