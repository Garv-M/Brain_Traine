package com.example.brain_trainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class game extends AppCompatActivity {

    Button reset,opt0, opt1, opt2, opt3;
    TextView timer, ques, score,right_wrong;
    int locationOfRightAnswer,score_total=0,no_of_ques=0;
    ArrayList<Integer> answers;

    public void Reset(View v){
        score_total = 0;
        no_of_ques = 0;
        score.setText(Integer.toString(score_total) + "/" + Integer.toString(no_of_ques));
        timer.setText("00:30");
        new_ques();
        countdown();
        reset.setVisibility(View.INVISIBLE);
        right_wrong.setText("");
    }

    public void countdown() {
        CountDownTimer countDownTimer = new CountDownTimer(30000 +100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int sec = (int) millisUntilFinished / 1000;

                if (sec <= 9) {
                    timer.setText("00:0" + sec);
                } else {
                    timer.setText("00:" + sec);
                }
            }

            @Override
            public void onFinish() {
                right_wrong.setText("Done");
                reset.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public int randomise() {
        Random rand;
        rand = new Random();
        int n1 = rand.nextInt(20) + 1;
        int n2 = rand.nextInt(20) + 1;

        ques.setText(Integer.toString(n1) + "+" + Integer.toString(n2));
        return n1 + n2;
    }

    public void new_ques(){
        int n=randomise();

        Random rand = new Random();
        locationOfRightAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0;i<4;i++){
            if(i==locationOfRightAnswer){
                answers.add(n);
            }else{
                int wrongAns = rand.nextInt(41);

                while(wrongAns==n){
                    wrongAns = rand.nextInt(41);
                }
                answers.add(wrongAns);
            }
        }
        opt0.setText(Integer.toString(answers.get(0)));
        opt1.setText(Integer.toString(answers.get(1)));
        opt2.setText(Integer.toString(answers.get(2)));
        opt3.setText(Integer.toString(answers.get(3)));
    }

    public void choseAnswers(View v){
        if(Integer.toString(locationOfRightAnswer).equals(v.getTag().toString())){
            right_wrong.setText("Right!!");
            score_total++;
        }else{
            right_wrong.setText("Wrong :(");
        }
        no_of_ques++;
        score.setText(Integer.toString(score_total) + "/" + Integer.toString(no_of_ques));
        new_ques();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timer = findViewById(R.id.textView_timer);
        reset = findViewById(R.id.button);
        ques = findViewById(R.id.Ques);
        opt0 = findViewById(R.id.opt0);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        score = findViewById(R.id.Score);
        right_wrong = findViewById(R.id.right_wrong);
        answers = new ArrayList<Integer>();

        reset.setVisibility(View.INVISIBLE);
        countdown();
        new_ques();
    }
}