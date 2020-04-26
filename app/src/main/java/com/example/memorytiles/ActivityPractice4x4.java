package com.example.memorytiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import java.util.*;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import maes.tech.intentanim.CustomIntent;

public class ActivityPractice4x4 extends AppCompatActivity {

    ///Variables
    //Sounds
    private SoundPool soundPool;
    private int startSound, flipSound, matchSound;

    //Scores
    String player_str_score = "0";
    String player_str_flips = "0";

    //Buttons
    ImageButton end_game;


    //Tiles
    ImageView id_11, id_12, id_13, id_14;
    ImageView id_21, id_22, id_23, id_24;
    ImageView id_31, id_32, id_33, id_34;
    ImageView id_41, id_42, id_43, id_44;

    //Front Tiles Array
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 107, 108,
            201, 202, 203, 204, 205, 206, 207, 208};
    List<ImageView> frontsShowing = new ArrayList<>();
    int matches = 0;

    //Images
    int image101, image102, image103, image104, image105, image106, image107, image108;
    int image201, image202, image203, image204, image205, image206, image207, image208;
    int backtile;

    //Turn Based Variables
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    //Scores
    TextView score;
    TextView flips;
    int flips_counter = 0;
    int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice4x4);

        //Sounds
        SoundCheck();
        startSound = soundPool.load(this, R.raw.start, 1);
        flipSound = soundPool.load(this, R.raw.flip, 1);
        matchSound = soundPool.load(this, R.raw.match, 1);
        soundPool.play(startSound, 1, 1, 0, 0, 1);

        ////Find View By IDs
        //Scores
        score = (TextView) findViewById(R.id.winner_score);
        flips = (TextView) findViewById(R.id.flips);

        //Buttons
        end_game = (ImageButton) findViewById(R.id.btn_endgame);

        ///Tiles
        //First row
        id_11 = (ImageView) findViewById(R.id.id_11);
        id_12 = (ImageView) findViewById(R.id.id_12);
        id_13 = (ImageView) findViewById(R.id.id_13);
        id_14 = (ImageView) findViewById(R.id.id_14);

        //Second row
        id_21 = (ImageView) findViewById(R.id.id_21);
        id_22 = (ImageView) findViewById(R.id.id_22);
        id_23 = (ImageView) findViewById(R.id.id_23);
        id_24 = (ImageView) findViewById(R.id.id_24);

        //Third row
        id_31 = (ImageView) findViewById(R.id.id_31);
        id_32 = (ImageView) findViewById(R.id.id_32);
        id_33 = (ImageView) findViewById(R.id.id_33);
        id_34 = (ImageView) findViewById(R.id.id_34);

        //Fourth row
        id_41 = (ImageView) findViewById(R.id.id_41);
        id_42 = (ImageView) findViewById(R.id.id_42);
        id_43 = (ImageView) findViewById(R.id.id_43);
        id_44 = (ImageView) findViewById(R.id.id_44);

        //Tags
        id_11.setTag("0");
        id_12.setTag("1");
        id_13.setTag("2");
        id_14.setTag("3");
        id_21.setTag("4");
        id_22.setTag("5");
        id_23.setTag("6");
        id_24.setTag("7");
        id_31.setTag("8");
        id_32.setTag("9");
        id_33.setTag("10");
        id_34.setTag("11");
        id_41.setTag("12");
        id_42.setTag("13");
        id_43.setTag("14");
        id_44.setTag("15");

        //Function to load front of cards
        cardsResources();

        //Shuffle the Images
        Collections.shuffle(Arrays.asList(cardsArray));

        //OnClick Card Methods
        id_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_11,  theCard);
            }
        });

        id_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_12, theCard);
            }
        });

        id_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_13, theCard);
            }
        });

        id_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_14, theCard);
            }
        });

        id_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_21, theCard);
            }
        });

        id_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_22, theCard);
            }
        });

        id_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_23, theCard);
            }
        });

        id_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_24, theCard);
            }
        });

        id_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_31, theCard);
            }
        });

        id_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_32, theCard);
            }
        });

        id_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_33, theCard);
            }
        });

        id_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_34, theCard);
            }
        });

        id_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_41, theCard);
            }
        });

        id_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_42, theCard);
            }
        });

        id_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_43, theCard);
            }
        });

        id_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_44, theCard);
            }
        });

        end_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();
            }
        });
    }

    private void doStuff(ImageView iv, int card) {
        //Set the correct images to the ImageView
        if(cardsArray[card] == 101) {
            flipCard(iv, image101);
        } else if (cardsArray[card] == 102) {
            flipCard(iv, image102);
        } else if (cardsArray[card] == 103) {
            flipCard(iv, image103);
        } else if (cardsArray[card] == 104) {
            flipCard(iv, image104);
        } else if (cardsArray[card] == 105) {
            flipCard(iv, image105);
        } else if (cardsArray[card] == 106) {
            flipCard(iv, image106);
        } else if (cardsArray[card] == 107) {
            flipCard(iv, image107);
        } else if (cardsArray[card] == 108) {
            flipCard(iv, image108);
        } else if (cardsArray[card] == 201) {
            flipCard(iv, image201);
        } else if (cardsArray[card] == 202) {
            flipCard(iv, image202);
        } else if (cardsArray[card] == 203) {
            flipCard(iv, image203);
        } else if (cardsArray[card] == 204) {
            flipCard(iv, image204);
        } else if (cardsArray[card] == 205) {
            flipCard(iv, image205);
        } else if (cardsArray[card] == 206) {
            flipCard(iv, image206);
        } else if (cardsArray[card] == 207) {
            flipCard(iv, image207);
        } else if (cardsArray[card] == 208) {
            flipCard(iv, image208);
        }

        //Check which image is selected and save it to temporary variable
        if(cardNumber == 1) {
            firstCard = cardsArray[card];
            if(firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;
            frontsShowing.add(iv);

            iv.setEnabled(false);
        } else if(cardNumber == 2) {
            secondCard = cardsArray[card];
            if(secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;
            frontsShowing.add(iv);

            id_11.setEnabled(false);
            id_12.setEnabled(false);
            id_13.setEnabled(false);
            id_14.setEnabled(false);
            id_21.setEnabled(false);
            id_22.setEnabled(false);
            id_23.setEnabled(false);
            id_24.setEnabled(false);
            id_31.setEnabled(false);
            id_32.setEnabled(false);
            id_33.setEnabled(false);
            id_34.setEnabled(false);
            id_41.setEnabled(false);
            id_42.setEnabled(false);
            id_43.setEnabled(false);
            id_44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Check if the selected images are equal
                    calculate();
                }
            }, 1000);

        }
    }

    //If Images are equal, remove them
    private void calculate() {
        if(firstCard == secondCard) {
            match(frontsShowing.get(0), frontsShowing.get(1));

            playerPoints++;
            player_str_score = new Integer(playerPoints).toString();

            score.setText("Score: " + player_str_score);
            matches++;
        } else {
            //Flip cards back over
            flipCard(frontsShowing.get(0), backtile);
            flipCard(frontsShowing.get(1), backtile);
            frontsShowing.remove(1);
            frontsShowing.remove(0);
        }

        flips_counter++;
        player_str_flips = new Integer(flips_counter).toString();
        flips.setText("Flips: " + player_str_flips);

        id_11.setEnabled(true);
        id_12.setEnabled(true);
        id_13.setEnabled(true);
        id_14.setEnabled(true);

        id_21.setEnabled(true);
        id_22.setEnabled(true);
        id_23.setEnabled(true);
        id_24.setEnabled(true);

        id_31.setEnabled(true);
        id_32.setEnabled(true);
        id_33.setEnabled(true);
        id_34.setEnabled(true);

        id_41.setEnabled(true);
        id_42.setEnabled(true);
        id_43.setEnabled(true);
        id_44.setEnabled(true);

        //Checks if end of game
        checkEnd();
    }

    private void checkEnd() {
        if(matches == 8) {
            endGame();
        }

    }

    //End Game
    private void endGame() {
        Scores.setPracticeScore(playerPoints);
        Scores.setPracticeFlips(flips_counter);
        Scores.setPractice6x6(false);
        Intent intent = new Intent(this, practice_end_activity.class);
        startActivity(intent);
        finish();
        CustomIntent.customType(this, "bottom-to-up");
    }

    ///Cards
    //Load Card Resources
    private void cardsResources() {
        image101 = R.drawable.an_101;
        image102 = R.drawable.an_102;
        image103 = R.drawable.an_103;
        image104 = R.drawable.an_104;
        image105 = R.drawable.an_105;
        image106 = R.drawable.an_106;
        image107 = R.drawable.an_107;
        image108 = R.drawable.an_108;
        image201 = R.drawable.an_201;
        image202 = R.drawable.an_202;
        image203 = R.drawable.an_203;
        image204 = R.drawable.an_204;
        image205 = R.drawable.an_205;
        image206 = R.drawable.an_206;
        image207 = R.drawable.an_207;
        image208 = R.drawable.an_208;
        backtile = R.drawable.bt_101;
    }

    ///Animations
    //Flipping Cards
    public void flipCard(final ImageView iv, final int image) {
        soundPool.play(flipSound, 10, 10, 0, 0, 1);
        YoYo.with(Techniques.FlipOutY)
                .duration(100)
                .playOn(iv);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Check if the selected images are equal
                iv.setImageResource(image);
                YoYo.with(Techniques.FlipInY)
                        .duration(100)
                        .playOn(iv);
            }
        }, 100);
    }

    //Matching cards
    public void match(final ImageView iv1, final ImageView iv2) {
        soundPool.play(matchSound, 1, 1, 0, 0, 1);
        YoYo.with(Techniques.FadeOut)
                .duration(300)
                .playOn(iv1);
        YoYo.with(Techniques.FadeOut)
                .duration(300)
                .playOn(iv2);
        frontsShowing.remove(1);
        frontsShowing.remove(0);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Make IVs invisible
                iv1.setVisibility(View.INVISIBLE);
                iv2.setVisibility(View.INVISIBLE);
            }
        }, 350);
    }

    private void SoundCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }
    }


}
