package com.example.memorytiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import maes.tech.intentanim.CustomIntent;

public class settings extends AppCompatActivity {

    CardView btn_about;
    CardView btn_back;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //FindViewByIDs
        btn_about = (CardView) findViewById(R.id.about_card);
        btn_back = (CardView) findViewById(R.id.back_card);
        logo = (ImageView) findViewById(R.id.logo);

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cardAnimationAbout(btn_about);
                Utils.delay(1, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        Utils.cardAnimationAbout(btn_back);
                    }
                });
                Utils.delay(1, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        removeIconAnimation(logo);
                    }
                });
                Utils.delay(4, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        openAbout();
                    }
                });
            }});
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.ReversedCardAnimation(btn_back);
                Utils.delay(1, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        finish();
                    }
                });
            }});

    }

    private void openAbout() {
        Intent intent = new Intent(this, about.class);
        startActivity(intent);
        CustomIntent.customType(this, "fadein-to-fadeout");
    }

    private void removeIconAnimation(final ImageView iv) {
        YoYo.with(Techniques.FadeOut)
                .duration(100)
                .playOn(iv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeIn)
                        .duration(500)
                        .playOn(iv);
            }
        }, 750);
    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this, "fadein-to-fadeout");
    }
}
