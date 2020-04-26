package com.example.memorytiles;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class ActivityPlay6x6 extends AppCompatActivity {

    ///Variables
    public static final String EXTRA_INT0 = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_INT1 = "com.example.application.example.EXTRA_INT1";
    public static final String EXTRA_INT2 = "com.example.application.example.EXTRA_INT2";

    //Sounds
    private SoundPool soundPool;
    private int startSound, flipSound, matchSound;

    //Scores
    String p1_str_score = "0", p2_str_score = "0";

    //Buttons
    ImageButton end_game;

    //Tiles
    ImageView id_11, id_12, id_13, id_14, id_15, id_16;
    ImageView id_21, id_22, id_23, id_24, id_25, id_26;
    ImageView id_31, id_32, id_33, id_34, id_35, id_36;
    ImageView id_41, id_42, id_43, id_44, id_45, id_46;
    ImageView id_51, id_52, id_53, id_54, id_55, id_56;
    ImageView id_61, id_62, id_63, id_64, id_65, id_66;

    //Front Tiles Array
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118,
            201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218};
    List<ImageView> frontsShowing = new ArrayList<>();
    int matches = 0;

    //Images
    int image101, image102, image103, image104, image105, image106, image107, image108, image109, image110, image111, image112, image113, image114, image115, image116, image117, image118;
    int image201, image202, image203, image204, image205, image206, image207, image208, image209, image210, image211, image212, image213, image214, image215, image216, image217, image218;
    int backtile;

    //Turn Based Variables
    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int turn = 1;
    ImageView player1Active, player2Active;

    //Scores
    TextView score;
    int player1Points = 0, player2Points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play6x6);

        //Sounds
        SoundCheck();
        startSound = soundPool.load(this, R.raw.start, 1);
        flipSound = soundPool.load(this, R.raw.flip, 1);
        matchSound = soundPool.load(this, R.raw.match, 1);
        soundPool.play(startSound, 1, 1, 0, 0, 1);

        ////Find View By IDs
        //Scores
        score = (TextView) findViewById(R.id.winner_score);

        //Buttons
        end_game = (ImageButton) findViewById(R.id.btn_endgame);

        ///Tiles
        //First row
        id_11 = (ImageView) findViewById(R.id.id_11);
        id_12 = (ImageView) findViewById(R.id.id_12);
        id_13 = (ImageView) findViewById(R.id.id_13);
        id_14 = (ImageView) findViewById(R.id.id_14);
        id_15 = (ImageView) findViewById(R.id.id_15);
        id_16 = (ImageView) findViewById(R.id.id_16);

        //Second row
        id_21 = (ImageView) findViewById(R.id.id_21);
        id_22 = (ImageView) findViewById(R.id.id_22);
        id_23 = (ImageView) findViewById(R.id.id_23);
        id_24 = (ImageView) findViewById(R.id.id_24);
        id_25 = (ImageView) findViewById(R.id.id_25);
        id_26 = (ImageView) findViewById(R.id.id_26);

        //Third row
        id_31 = (ImageView) findViewById(R.id.id_31);
        id_32 = (ImageView) findViewById(R.id.id_32);
        id_33 = (ImageView) findViewById(R.id.id_33);
        id_34 = (ImageView) findViewById(R.id.id_34);
        id_35 = (ImageView) findViewById(R.id.id_35);
        id_36 = (ImageView) findViewById(R.id.id_36);

        //Fourth row
        id_41 = (ImageView) findViewById(R.id.id_41);
        id_42 = (ImageView) findViewById(R.id.id_42);
        id_43 = (ImageView) findViewById(R.id.id_43);
        id_44 = (ImageView) findViewById(R.id.id_44);
        id_45 = (ImageView) findViewById(R.id.id_45);
        id_46 = (ImageView) findViewById(R.id.id_46);

        //Fifth row
        id_51 = (ImageView) findViewById(R.id.id_51);
        id_52 = (ImageView) findViewById(R.id.id_52);
        id_53 = (ImageView) findViewById(R.id.id_53);
        id_54 = (ImageView) findViewById(R.id.id_54);
        id_55 = (ImageView) findViewById(R.id.id_55);
        id_56 = (ImageView) findViewById(R.id.id_56);

        //Sixth row
        id_61 = (ImageView) findViewById(R.id.id_61);
        id_62 = (ImageView) findViewById(R.id.id_62);
        id_63 = (ImageView) findViewById(R.id.id_63);
        id_64 = (ImageView) findViewById(R.id.id_64);
        id_65 = (ImageView) findViewById(R.id.id_65);
        id_66 = (ImageView) findViewById(R.id.id_66);

        //Tags
        id_11.setTag("0");
        id_12.setTag("1");
        id_13.setTag("2");
        id_14.setTag("3");
        id_15.setTag("4");
        id_16.setTag("5");
        id_21.setTag("6");
        id_22.setTag("7");
        id_23.setTag("8");
        id_24.setTag("9");
        id_25.setTag("10");
        id_26.setTag("11");
        id_31.setTag("12");
        id_32.setTag("13");
        id_33.setTag("14");
        id_34.setTag("15");
        id_35.setTag("16");
        id_36.setTag("17");
        id_41.setTag("18");
        id_42.setTag("19");
        id_43.setTag("20");
        id_44.setTag("21");
        id_45.setTag("22");
        id_46.setTag("23");
        id_51.setTag("24");
        id_52.setTag("25");
        id_53.setTag("26");
        id_54.setTag("27");
        id_55.setTag("28");
        id_56.setTag("29");
        id_61.setTag("30");
        id_62.setTag("31");
        id_63.setTag("32");
        id_64.setTag("33");
        id_65.setTag("34");
        id_66.setTag("35");

        //Active spots
        player1Active = (ImageView) findViewById(R.id.p1_active);
        player2Active = (ImageView) findViewById(R.id.p2_active);

        player2Active.setVisibility(View.INVISIBLE);

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

        id_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_15, theCard);
            }
        });

        id_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_16, theCard);
            }
        });

        id_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_21,  theCard);
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

        id_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_25, theCard);
            }
        });

        id_26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_26, theCard);
            }
        });

        id_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_31,  theCard);
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

        id_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_35, theCard);
            }
        });

        id_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_36, theCard);
            }
        });

        id_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_41,  theCard);
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

        id_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_45, theCard);
            }
        });

        id_46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_46, theCard);
            }
        });

        id_51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_51,  theCard);
            }
        });

        id_52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_52, theCard);
            }
        });

        id_53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_53, theCard);
            }
        });

        id_54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_54, theCard);
            }
        });

        id_55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_55, theCard);
            }
        });

        id_56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_56, theCard);
            }
        });

        id_61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_61,  theCard);
            }
        });

        id_62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_62, theCard);
            }
        });

        id_63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_63, theCard);
            }
        });

        id_64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_64, theCard);
            }
        });

        id_65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_65, theCard);
            }
        });

        id_66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff(id_66, theCard);
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
        } else if (cardsArray[card] == 109) {
            flipCard(iv, image109);
        } else if (cardsArray[card] == 110) {
            flipCard(iv, image110);
        } else if (cardsArray[card] == 111) {
            flipCard(iv, image111);
        } else if (cardsArray[card] == 112) {
            flipCard(iv, image112);
        } else if (cardsArray[card] == 113) {
            flipCard(iv, image113);
        } else if (cardsArray[card] == 114) {
            flipCard(iv, image114);
        } else if (cardsArray[card] == 115) {
            flipCard(iv, image115);
        } else if (cardsArray[card] == 116) {
            flipCard(iv, image116);
        } else if (cardsArray[card] == 117) {
            flipCard(iv, image117);
        } else if (cardsArray[card] == 118) {
            flipCard(iv, image118);
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
        } else if (cardsArray[card] == 209) {
            flipCard(iv, image209);
        } else if (cardsArray[card] == 210) {
            flipCard(iv, image210);
        } else if (cardsArray[card] == 211) {
            flipCard(iv, image211);
        } else if (cardsArray[card] == 212) {
            flipCard(iv, image212);
        } else if (cardsArray[card] == 213) {
            flipCard(iv, image213);
        } else if (cardsArray[card] == 214) {
            flipCard(iv, image214);
        } else if (cardsArray[card] == 215) {
            flipCard(iv, image215);
        } else if (cardsArray[card] == 216) {
            flipCard(iv, image216);
        } else if (cardsArray[card] == 217) {
            flipCard(iv, image217);
        } else if (cardsArray[card] == 218) {
            flipCard(iv, image218);
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
            id_15.setEnabled(false);
            id_16.setEnabled(false);

            id_21.setEnabled(false);
            id_22.setEnabled(false);
            id_23.setEnabled(false);
            id_24.setEnabled(false);
            id_25.setEnabled(false);
            id_26.setEnabled(false);

            id_31.setEnabled(false);
            id_32.setEnabled(false);
            id_33.setEnabled(false);
            id_34.setEnabled(false);
            id_35.setEnabled(false);
            id_36.setEnabled(false);

            id_41.setEnabled(false);
            id_42.setEnabled(false);
            id_43.setEnabled(false);
            id_44.setEnabled(false);
            id_45.setEnabled(false);
            id_46.setEnabled(false);

            id_51.setEnabled(false);
            id_52.setEnabled(false);
            id_53.setEnabled(false);
            id_54.setEnabled(false);
            id_55.setEnabled(false);
            id_56.setEnabled(false);

            id_61.setEnabled(false);
            id_62.setEnabled(false);
            id_63.setEnabled(false);
            id_64.setEnabled(false);
            id_65.setEnabled(false);
            id_66.setEnabled(false);

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

            if(turn == 1) {
                player1Points++;
                p1_str_score = new Integer(player1Points).toString();
            } else if (turn == 2) {
                player2Points++;
                p2_str_score = new Integer(player2Points).toString();
            }

            score.setText(p1_str_score + " - " + p2_str_score);
            matches++;
        } else {
            //Change Player turn
            if(turn == 1) {
                turn = 2;
                player1Active.setVisibility(View.INVISIBLE);
                player2Active.setVisibility(View.VISIBLE);
            } else if(turn == 2) {
                turn = 1;
                player2Active.setVisibility(View.INVISIBLE);
                player1Active.setVisibility(View.VISIBLE);
            }

            //Flip cards back over
            flipCard(frontsShowing.get(0), backtile);
            flipCard(frontsShowing.get(1), backtile);
            frontsShowing.remove(1);
            frontsShowing.remove(0);
        }

        id_11.setEnabled(true);
        id_12.setEnabled(true);
        id_13.setEnabled(true);
        id_14.setEnabled(true);
        id_15.setEnabled(true);
        id_16.setEnabled(true);

        id_21.setEnabled(true);
        id_22.setEnabled(true);
        id_23.setEnabled(true);
        id_24.setEnabled(true);
        id_25.setEnabled(true);
        id_26.setEnabled(true);

        id_31.setEnabled(true);
        id_32.setEnabled(true);
        id_33.setEnabled(true);
        id_34.setEnabled(true);
        id_35.setEnabled(true);
        id_36.setEnabled(true);

        id_41.setEnabled(true);
        id_42.setEnabled(true);
        id_43.setEnabled(true);
        id_44.setEnabled(true);
        id_45.setEnabled(true);
        id_46.setEnabled(true);

        id_51.setEnabled(true);
        id_52.setEnabled(true);
        id_53.setEnabled(true);
        id_54.setEnabled(true);
        id_55.setEnabled(true);
        id_56.setEnabled(true);

        id_61.setEnabled(true);
        id_62.setEnabled(true);
        id_63.setEnabled(true);
        id_64.setEnabled(true);
        id_65.setEnabled(true);
        id_66.setEnabled(true);

        //Checks if end of game
        checkEnd();
    }

    private void checkEnd() {
        if(matches == 18) {
            endGame();
        }

    }

    private void endGame() {
        int winner;

        if (player1Points > player2Points) {
            winner = 1;
        } else if (player1Points == player2Points) {
            winner = 2;
        } else {
            winner = 3;
        }

        if (winner == 1) {
            Scores.setPlaywinnerscore(player1Points);
            Scores.setPlayloserscore(player2Points);
        } else if (winner == 3) {
            Scores.setPlaywinnerscore(player2Points);
            Scores.setPlayloserscore(player1Points);
        } else {
            Scores.setPlaywinnerscore(player1Points);
            Scores.setPlayloserscore(player2Points);
        }

        Scores.setPlaywinner(winner);
        Scores.setPlay6x6(true);

        Intent intent = new Intent(this, play_end_activity.class);
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
        image109 = R.drawable.an_109;
        image110 = R.drawable.an_110;
        image111 = R.drawable.an_111;
        image112 = R.drawable.an_112;
        image113 = R.drawable.an_113;
        image114 = R.drawable.an_114;
        image115 = R.drawable.an_115;
        image116 = R.drawable.an_116;
        image117 = R.drawable.an_117;
        image118 = R.drawable.an_118;
        image201 = R.drawable.an_201;
        image202 = R.drawable.an_202;
        image203 = R.drawable.an_203;
        image204 = R.drawable.an_204;
        image205 = R.drawable.an_205;
        image206 = R.drawable.an_206;
        image207 = R.drawable.an_207;
        image208 = R.drawable.an_208;
        image209 = R.drawable.an_209;
        image210 = R.drawable.an_210;
        image211 = R.drawable.an_211;
        image212 = R.drawable.an_212;
        image213 = R.drawable.an_213;
        image214 = R.drawable.an_214;
        image215 = R.drawable.an_215;
        image216 = R.drawable.an_216;
        image217 = R.drawable.an_217;
        image218 = R.drawable.an_218;
        backtile = R.drawable.bt_101;
    }

    ///Animations
    //Flipping Cards
    public void flipCard(final ImageView iv, final int image) {
        soundPool.play(flipSound, 1, 1, 0, 0, 1);
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
