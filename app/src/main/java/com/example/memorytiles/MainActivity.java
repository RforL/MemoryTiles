package com.example.memorytiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    ///Define Variables
    //Context
    Activity context = MainActivity.this;

    // Buttons
    CardView play_button;
    CardView practice_button;
    CardView settings_button;

    int secs = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///Find View by IDs
        //Buttons
        play_button = (CardView) findViewById(R.id.play_card);
        practice_button = (CardView) findViewById(R.id.practice_card);
        settings_button = (CardView) findViewById(R.id.settings_card);

        //OnClickListener Methods
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cardAnimation(play_button);
                Utils.delay(secs, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        openActivities(play_menu.class);
                    }
                });
            }});
        practice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cardAnimation(practice_button);
                Utils.delay(secs, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        openActivities(practice_menu.class);
                    }
                });
            }});
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cardAnimation(settings_button);
                Utils.delay(secs, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        openActivities(settings.class);
                    }
                });
            }});

    }

    //Open Activities
    private void openActivities(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        CustomIntent.customType(this, "fadein-to-fadeout");
    }
}
