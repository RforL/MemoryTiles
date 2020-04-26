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
import maes.tech.intentanim.CustomIntent;

public class play_end_activity extends AppCompatActivity {

    Context c = play_end_activity.this;

    private SoundPool soundPool;
    int buttonClick;

    int winner1 = 0;
    int win_score1 = 0;
    int loser_score1 = 0;

    int winner2 = 0;
    int win_score2 = 0;
    int loser_score2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_end);

        SoundVersionCheck();

        winner1 = Scores.getPlaywinner();
        win_score1 = Scores.getPlaywinnerscore();
        loser_score1 = Scores.getPlayloserscore();

        ///Find view by IDs
        //Winner
        TextView winnerText = (TextView) findViewById(R.id.winner_text);
        TextView winnerScore = (TextView) findViewById(R.id.winner_score);
        ImageView winnerAvatar = (ImageView) findViewById(R.id.winner);

        //Loser
        TextView loserText = (TextView) findViewById(R.id.loser_text);
        TextView loserScore = (TextView) findViewById(R.id.loser_score);
        ImageView loserAvatar = (ImageView) findViewById(R.id.loser);

        //Buttons
        ImageButton playRematch = (ImageButton) findViewById(R.id.btn_practice_rematch);
        ImageButton playSize = (ImageButton) findViewById(R.id.btn_practice_sizes);
        ImageButton playMenu = (ImageButton) findViewById(R.id.btn_practice_menu);

        ////Functionality
        ///Winners
        //Player 1 wins
        if (winner1 == 1) {
            winnerText.setText("Player 1 wins!");
            winnerScore.setText(win_score1 + " points");
            winnerAvatar.setBackgroundResource(R.drawable.av_male_enlarged);

            loserText.setText("Player 2");
            loserScore.setText(loser_score1 + " points");
            loserAvatar.setBackgroundResource(R.drawable.av_female_enlarged);
        }

        //Draw
        else if (winner1 == 2) {
            winnerText.setText("Draw \n\nPlayer 1");
            winnerScore.setText(win_score1 + " points");
            winnerAvatar.setBackgroundResource(R.drawable.av_male_enlarged);

            loserText.setText("Player 2");
            loserScore.setText(loser_score1 + " points");
            loserAvatar.setBackgroundResource(R.drawable.av_female_enlarged);
        }
        //Player 2 wins
        else {
            winnerText.setText("Player 2 wins!");
            winnerScore.setText(win_score1 + " points");
            winnerAvatar.setBackgroundResource(R.drawable.av_female_enlarged);

            loserText.setText("Player 1");
            loserScore.setText(loser_score1 + " points");
            loserAvatar.setBackgroundResource(R.drawable.av_male_enlarged);
        }

         //OnClick methods
        playRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Scores.isPractice6x6() == true) {
                    openActivity6x6();
                } else {
                    openActivity4x4();
                }
            }
        });

        playSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(buttonClick, 1, 1, 1, 0, 1);
                Intent intent = new Intent(c, play_menu.class);
                startActivity(intent);
                finish();
                CustomIntent.customType(c, "fadein-to-fadeout");
            }
        });

        playMenu.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(this, ActivityPlay4x4.class);
        startActivity(intent);
        soundPool.play(buttonClick, 1, 1, 1, 0, 1);
        finish();
        CustomIntent.customType(this, "up-to-bottom");
    }

    private void openActivity6x6() {
        Intent intent = new Intent(this, ActivityPlay6x6.class);
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
