package com.example.memorytiles;

public class Scores {

    //Scores for play
    static int playwinner = 1;
    static int playwinnerscore = 0;
    static int playloserscore = 0;

    static boolean play6x6 = false;


    //Scores for practice
    static int practiceFlips = 0;
    static int practiceScore = 0;

    static boolean practice6x6 = false;

    public static int getPlaywinner() {
        return playwinner;
    }

    public static void setPlaywinner(int playwinner) {
        Scores.playwinner = playwinner;
    }

    public static int getPlaywinnerscore() {
        return playwinnerscore;
    }

    public static void setPlaywinnerscore(int playwinnerscore) {
        Scores.playwinnerscore = playwinnerscore;
    }

    public static int getPlayloserscore() {
        return playloserscore;
    }

    public static void setPlayloserscore(int playloserscore) {
        Scores.playloserscore = playloserscore;
    }

    public static boolean isPlay6x6() {
        return play6x6;
    }

    public static void setPlay6x6(boolean play6x6) {
        Scores.play6x6 = play6x6;
    }

    public static int getPracticeFlips() {
        return practiceFlips;
    }

    public static void setPracticeFlips(int practiceFlips) {
        Scores.practiceFlips = practiceFlips;
    }

    public static int getPracticeScore() {
        return practiceScore;
    }

    public static void setPracticeScore(int practiceScore) {
        Scores.practiceScore = practiceScore;
    }

    public static boolean isPractice6x6() {
        return practice6x6;
    }

    public static void setPractice6x6(boolean practice6x6) {
        Scores.practice6x6 = practice6x6;
    }
}
