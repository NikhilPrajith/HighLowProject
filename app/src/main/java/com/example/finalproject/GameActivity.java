package com.example.finalproject;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    public Data A;
    public Data B;
    public PlayGame game;
    public Boolean gameEnd;
    public String type;
    public String beforeData = "";
    public String afterData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent i = getIntent();
        String passed = i.getExtras().getString("buttonId");
        TextView name = (TextView) findViewById(R.id.mode);
        type = passed;
        name.setText(passed);
        startGame(passed);

    }
    public void startGame(String mode){
        FileProcess a = new FileProcess(mode,getApplicationContext());
        Map<Integer, Data> temp = a.getFiltered();
        if(mode.equals("Networth") || mode.equals("Grocery")){
            beforeData = "$";
        }
        if(mode.equals("Youtube")){
            afterData = " subs";
        }
        if(mode.equals("Heights")){
            afterData = "in";
        }
        if(temp.size()!=0){
            game = new PlayGame(temp);
            A = game.getASingleStartingData();
            B = game.getComparingData(A);
            gameEnd = false;
            setData();
        }

    }
    public void processUserChoice(Boolean result){
        if(result == false){
            System.out.println("Failed");
            A = new Data("",0.0,"");
            B = new Data("",0.0,"");
        }else{
            A = B;
            B = game.getComparingData(A);

        }
        System.out.println("new Process");
        setData();
    }
    public void answerSelection(View view) {
        switch (view.getId()) {
            case R.id.moreButton:
                System.out.println("More");
                processUserChoice(game.compare("G",A,B));
                break;
            case R.id.lessButton:
                System.out.println("Less");
                processUserChoice(game.compare("L",A,B));
                break;
        }
    }

    public void setData(){
        if(A.getName()!="" && B.getName() !="") {
            TextView scoreView = (TextView) findViewById(R.id.score);
            scoreView.setText("Score: " + game.getScore().toString());

            TextView name1 = (TextView) findViewById(R.id.name1);
            name1.setText(A.getName());
            TextView val1 = (TextView) findViewById(R.id.value1);


            System.out.println("|||||||||||");
            System.out.println(A.getData());
            System.out.println(game.format(A.getData()));
            System.out.println(B.getData());
            System.out.println("|||}||||||");
            val1.setText(beforeData+ game.format(A.getData())+afterData);

            String imageUri = A.getUrl();
            ImageView imgageOfA = (ImageView) findViewById(R.id.img1);
            Picasso.get().load(imageUri).resize(400, 400).centerCrop().into(imgageOfA);

            String imageUriB = B.getUrl();
            ImageView imgageOfB = (ImageView) findViewById(R.id.img2);
            Picasso.get().load(imageUriB).resize(400, 400).centerCrop().into(imgageOfB);
            TextView name2 = (TextView) findViewById(R.id.name2);
            name2.setText(B.getName());
        }

    }


}