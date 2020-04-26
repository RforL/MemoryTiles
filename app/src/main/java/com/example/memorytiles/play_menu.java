package com.example.memorytiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

import maes.tech.intentanim.CustomIntent;

public class play_menu extends AppCompatActivity {

    ///Define Variables
    //Context
    Activity context = play_menu.this;

    //Buttons
    CardView easy_button;
    CardView hard_button;
    CardView back_button;

    int secs = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);

        ///Find View by IDs
        //Buttons
        easy_button = (CardView) findViewById(R.id.easy_card);
        hard_button = (CardView) findViewById(R.id.hard_card);
        back_button = (CardView) findViewById(R.id.back_card);


        //OnClickListener Methods
        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cardAnimation(easy_button);
                Utils.delay(secs, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        openActivities(ActivityPlay4x4.class);
                    }
                });
            }});

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cardAnimation(hard_button);
                Utils.delay(secs, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        openActivities(ActivityPlay6x6.class);
                    }
                });
            }});

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.ReversedCardAnimation(back_button);
                Utils.delay(secs, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        finish();
                    }
                });
            }});

    }


    //Open Activities
    private void openActivities(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        CustomIntent.customType(this, "fadein-to-fadeout");
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this, "fadein-to-fadeout");
    }
}
