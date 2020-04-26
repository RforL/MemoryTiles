package com.example.memorytiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import maes.tech.intentanim.CustomIntent;

public class about extends AppCompatActivity {

    CardView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btn_back = (CardView) findViewById(R.id.back_card);

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

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this, "fadein-to-fadeout");
    }
}
