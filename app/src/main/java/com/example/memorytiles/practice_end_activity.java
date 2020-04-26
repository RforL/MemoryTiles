package com.example.memorytiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

import maes.tech.intentanim.CustomIntent;

public class practice_end_activity extends AppCompatActivity {

    Context c = practice_end_activity.this;

    private SoundPool soundPool;
    int buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_end);

        SoundVersionCheck();

        ///Find view by IDs
        //Winner
        TextView playerText = (TextView) findViewById(R.id.player_text);
        TextView score = (TextView) findViewById(R.id.score);
        TextView flips = (TextView) findViewById(R.id.flips);
        ImageView avatar = (ImageView) findViewById(R.id.avatar);

        //Buttons
        ImageButton practiceRematch = (ImageButton) findViewById(R.id.btn_practice_rematch);
        ImageButton practiceSize = (ImageButton) findViewById(R.id.btn_practice_sizes);
        ImageButton practiceMenu = (ImageButton) findViewById(R.id.btn_practice_menu);

        ////Functionality
        ///Score Feedback

        score.setText(Scores.getPracticeScore() + " points");
        flips.setText(Scores.getPracticeFlips() + " flips");

        //OnClick methods
        practiceRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Scores.isPractice6x6() == true) {
                    openActivity6x6();
                } else {
                    openActivity4x4();
                }
            }
        });

        practiceSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(buttonClick, 1, 1, 1, 0, 1);
                Intent intent = new Intent(c, practice_menu.class);
                startActivity(intent);
                finish();
                CustomIntent.customType(c, "fadein-to-fadeout");
            }
        });

        practiceMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(buttonClick, 1, 1, 1, 0, 1);
                finish();
                CustomIntent.customType(c, "fadein-to-fadeout");
            }
        });
    }

    //Activity Open methods
    private void openActivity4x4() {
        Intent intent = new Intent(this, ActivityPractice4x4.class);
        startActivity(intent);
        soundPool.play(buttonClick, 1, 1, 1, 0, 1);
        finish();
        CustomIntent.customType(this, "up-to-bottom");
    }

    private void openActivity6x6() {
        Intent intent = new Intent(this, ActivityPractice6x6.class);
        startActivity(intent);
        soundPool.play(buttonClick, 1, 1, 1, 0, 1);
        finish();
        CustomIntent.customType(this, "up-to-bottom");
    }

    private void SoundVersionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
    }

}
