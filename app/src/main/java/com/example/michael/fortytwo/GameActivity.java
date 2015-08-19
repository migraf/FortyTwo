package com.example.michael.fortytwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

/**
 * @author Michael Graf
 * @author Marius Herr on 16.08.2015.
 */

public class GameActivity extends Activity {
    Button topLeftButton,bottomLeftButton,topRightButton,bottomRightButton;
    TextView startNumber, countdownTimer;
    CountDownTimer timer;

    final int FORTYTWO = 42;

    //Todo: Count clicks for score and calculate score -> show highscore on top
    int clicks, initNum, score;



    //Todo: implement game over for game loop -> switch screen with score (if == 42 -> screen with score, clicks and time left)


    //Todo: implement the countdown with integers from 10 to 0 (game over)


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        initNum = createRandomStartNumber(100,300);

        final Intent scoreIntent =  new Intent(this, ScoreActivity.class);
        scoreIntent.putExtra("Score", 0);



        //Sets random numbers to all the Buttons
        topLeftButton = (Button)findViewById(R.id.top_left_button);
        topLeftButton.setText(String.valueOf(createRandomNumberInRangeButtons(1, 5, initNum)));

        bottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        bottomLeftButton.setText(String.valueOf(createRandomNumberInRangeButtons(20, 50, initNum)));

        topRightButton = (Button)findViewById(R.id.top_right_button);
        topRightButton.setText(String.valueOf(createRandomNumberInRangeButtons(5, 20, initNum)));

        bottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        bottomRightButton.setText(String.valueOf(createRandomNumberInRangeButtons(50, 100, initNum)));

        //set the random number to subtract from
        startNumber = (TextView) findViewById(R.id.initial_number);
        startNumber.setText(String.valueOf(initNum));


        countdownTimer = (TextView) findViewById(R.id.countdown_timer);


         timer = new CountDownTimer(10000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownTimer.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                startActivity(scoreIntent);



            }
        }.start();



    }

    private static int createRandomStartNumber (int top, int bottom){
        Random random = new Random();
        int range =  top - bottom +1;
        int randomNumber =(int) (range*random.nextDouble())+ bottom;
        return randomNumber;
    }

    //Todo: Try to create only "good numbers" for the button (depending on startNumber)
    private static int createRandomNumberInRangeButtons (int top, int bottom, int initNum){
        Random random = new Random();

        int range =  top - bottom +1;
        int randomNumber =(int) (range*random.nextDouble())+ bottom;

        return randomNumber;
    }

    //On Click methods for all the Buttons


    public void onLeftButtonClick(View view) {
        subtractButtonValue(startNumber,topLeftButton);
        clicks++;
    }

    public void onRightButton1Click(View view) {
        subtractButtonValue(startNumber,topRightButton);
        clicks++;
    }


    public void onBottomLeftButtonClick(View view) {
        subtractButtonValue(startNumber,bottomLeftButton);
        clicks++;
    }

    public void onBottomRightButtonClick(View view) {
        subtractButtonValue(startNumber,bottomRightButton);
        clicks++;
    }


    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void subtractButtonValue(TextView number, Button button){
        Intent scoreIntent =  new Intent(this, ScoreActivity.class);
        scoreIntent.putExtra("Score", calculateScore(countdownTimer,clicks));
        int buttonValue = Integer.parseInt((String) button.getText());
        int textValue = Integer.parseInt((String) number.getText());
        int newValue =  textValue-buttonValue;

        if(newValue < FORTYTWO){
           startActivity(scoreIntent);
            timer.cancel();



        }

        number.setText(String.valueOf(newValue));

    }
    private int calculateScore(TextView time, int clicks){

        int timeValue = Integer.parseInt((String) time.getText());

        int score  = (42*timeValue) - clicks;

       return score;

    }



}
