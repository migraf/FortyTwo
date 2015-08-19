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
    Intent gameOverIntent;
    int [] randomNumbers =  new int[4];

    final int FORTYTWO = 42;

    //Todo: Count clicks for score and calculate score -> show highscore on top
    int clicks, initNum, score;



    //Todo: implement game over for game loop -> switch screen with score (if == 42 -> screen with score, clicks and time left)





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        initNum = createRandomStartNumber(100,300);

        gameOverIntent =  new Intent(this, GameOverActivity.class);
        gameOverIntent.putExtra("Score", 0);
        fillArrayWithRandomNumbers(randomNumbers);
        calculateFittingNumbers(randomNumbers, initNum);



        //Sets random numbers to all the Buttons
        topLeftButton = (Button)findViewById(R.id.top_left_button);
        topLeftButton.setText(String.valueOf(randomNumbers[3]));

        bottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        bottomLeftButton.setText(String.valueOf(randomNumbers[1]));

        topRightButton = (Button)findViewById(R.id.top_right_button);
        topRightButton.setText(String.valueOf(randomNumbers[2]));

        bottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        bottomRightButton.setText(String.valueOf(randomNumbers[0]));

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
                startActivity(gameOverIntent);



            }
        }.start();



    }

    private static int createRandomStartNumber (int top, int bottom){
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
        subtractButtonValue(startNumber, bottomRightButton);
        clicks++;
    }


    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void subtractButtonValue(TextView number, Button button){
        gameOverIntent.putExtra("Score", 0);

        Intent scoreIntent = new Intent(this, ScoreActivity.class);
        scoreIntent.putExtra("Score", calculateScore(countdownTimer,clicks));

        int buttonValue = Integer.parseInt((String) button.getText());
        int textValue = Integer.parseInt((String) number.getText());
        int newValue =  textValue-buttonValue;

        if(newValue < FORTYTWO){
            startActivity(gameOverIntent);
            timer.cancel();
        }
        else if(newValue == FORTYTWO){
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

    private void fillArrayWithRandomNumbers(int [] arr){
        arr[3] = createRandomStartNumber(1,1);
        arr[2] = createRandomStartNumber(5,10);
        arr[1] = createRandomStartNumber(10,50);
        arr[0] = createRandomStartNumber(50,100);

    }

    private void calculateFittingNumbers(int [] arr, int startNumber){
        int restNumber = startNumber -FORTYTWO;
        int num;

        for (int i = 0; i<arr.length; i++){
            if(arr[i] < restNumber){
                num = restNumber/arr[i];
                restNumber -= num*arr[i];
            }
        }

        if(restNumber != 0){
            fillArrayWithRandomNumbers(randomNumbers);
            calculateFittingNumbers(arr, startNumber);
        }
    }



}
