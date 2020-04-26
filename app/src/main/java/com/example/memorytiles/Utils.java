package com.example.memorytiles;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;

import androidx.cardview.widget.CardView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Utils {

    static SoundPool soundPool;
    static int buttonPressed, flipSound, matchSound;

    // Delay mechanism
    public interface DelayCallback{
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 10); // afterDelay will be executed after (secs*1000) milliseconds.
    }

    public static void cardAnimation(final CardView cv) {
        YoYo.with(Techniques.SlideOutRight)
                .duration(100)
                .playOn(cv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.SlideInRight)
                        .duration(500)
                        .playOn(cv);
            }
        }, 550);
    }

    public static void cardAnimationAbout(final CardView cv) {
        YoYo.with(Techniques.SlideOutRight)
                .duration(100)
                .playOn(cv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.SlideInRight)
                        .duration(500)
                        .playOn(cv);
            }
        }, 550);
    }

    public static void ReversedCardAnimation(final CardView cv) {
        YoYo.with(Techniques.SlideOutLeft)
                .duration(100)
                .playOn(cv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(cv);
            }
        }, 550);
    }
}

//https://stackoverflow.com/questions/15874117/how-to-set-delay-in-android
