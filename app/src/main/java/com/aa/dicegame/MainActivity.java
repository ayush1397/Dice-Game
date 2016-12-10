package com.aa.dicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private int userTotal,computerTotal,userCurrent,computerCurrent;

    ImageView imageView;
    TextView textView;
    Button rollButton, holdButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView ) findViewById(R.id.textview);
        rollButton = (Button) findViewById(R.id.button);
        holdButton = (Button) findViewById(R.id.button2);
        resetButton = (Button) findViewById(R.id.button3);
    }

    android.os.Handler handler = new android.os.Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            computerTurn();
        }
    };

    public void changeImageView(int i) {
        switch (i){
            case 1: imageView.setImageResource(R.drawable.dice1);
                break;
            case 2: imageView.setImageResource(R.drawable.dice2);
                break;
            case 3: imageView.setImageResource(R.drawable.dice3);
                break;
            case 4: imageView.setImageResource(R.drawable.dice4);
                break;
            case 5: imageView.setImageResource(R.drawable.dice5);
                break;
            case 6: imageView.setImageResource(R.drawable.dice6);
                break;
            default: imageView.setImageResource(R.drawable.dice6);
        }
    }

    long time = 1000;

    public void userRoll(View view){
        Random random = new Random();
        int i = random.nextInt(6);
        i++;
        changeImageView(i);

        if(i>=2&&i<=6){
            userCurrent = userCurrent + i;
            textView.setText("User total = " + userTotal + ", Computer Total = " + computerTotal +
                    ", User Current = "+ userCurrent);
        }
        else{
            userCurrent = 0;
            textView.setText("User total = " + userTotal + ", Computer Total = " + computerTotal +
                    ", User chance ended");
            handler.postDelayed(runnable,time);
        }
    }


    public void userHold(View view){

        userTotal = userTotal + userCurrent;
        userCurrent = 0;
        computerTurn();
        textView.setText("User total = " + userTotal + ", Computer Total = " + computerTotal +
                ", User chance ended");

        handler.postDelayed(runnable,time);

    }

    public void userReset(View view){

        userTotal = 0;
        computerTotal = 0;

        textView.setText("User total = " + userTotal + ", Computer Total = " + computerTotal);
    }



    //a function is grey when it isn't used
    public void computerTurn() {
       // while (computerTotal < 28) {
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);

            Random random = new Random();
            int i = random.nextInt(6);
            i++;
            changeImageView(i);

            if (i >= 2 && i <= 6) {
                computerCurrent = computerCurrent + i;
                textView.setText("User total = " + userTotal + ", Computer Total = " + computerTotal +
                        ", Computer Current = " + userCurrent);

                if(computerCurrent<21){
                    handler.postDelayed(runnable,time);
                }

            } else {
                userCurrent = 0;
                textView.setText("User total = " + userTotal + ", Computer Total = " + computerTotal +
                        ", Computer's chance ended");
               // break;
            }
            computerCurrent = 0;
      //  }

        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
    }
}
