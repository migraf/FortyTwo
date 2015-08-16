package com.example.michael.fortytwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    TextView startNumber;
    //Todo: Count clicks for score and calculate score -> show highscore on top
    int clicks, score;

    //Todo: implement game over for game loop -> switch screen with score (if == 42 -> screen with score, clicks and time left)
    boolean gameOver = false;

    //Todo: implement the countdown with integers from 10 to 0 (game over)


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);


        //Sets random numbers to all the Buttons
        topLeftButton = (Button)findViewById(R.id.top_left_button);
        topLeftButton.setText(String.valueOf(createRandomNumberInRangeButtons(1, 5, )));

        bottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        bottomLeftButton.setText(String.valueOf(createRandomNumberInRangeButtons(20, 50,)));

        topRightButton = (Button)findViewById(R.id.top_right_button);
        topRightButton.setText(String.valueOf(createRandomNumberInRangeButtons(5, 20, )));

        bottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        bottomRightButton.setText(String.valueOf(createRandomNumberInRangeButtons(50, 100, ));

        //set the random number to subtract from
        startNumber = (TextView) findViewById(R.id.initial_number);
        startNumber.setText(String.valueOf(createRandomStartNumber(100, 300)));

    }

    private static int createRandomStartNumber (int top, int bottom ){
        Random random = new Random();
        int range =  top - bottom +1;
        int randomNumber =(int) (range*random.nextDouble())+ bottom;
        return randomNumber;
    }

    //Todo: Try to create only "good numbers" for the button (depending on startNumber)
    private static int createRandomNumberInRangeButtons (int top, int bottom, int initialNumber){
        Random random = new Random();

        int range =  top - bottom +1;
        int randomNumber =(int) (range*random.nextDouble())+ bottom;

        return randomNumber;
    }


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

    public void subtractButtonValue(TextView number, Button button){
        int buttonValue = Integer.parseInt((String) button.getText());
        int textValue = Integer.parseInt((String) number.getText());
        int newValue =  textValue-buttonValue;

        number.setText(String.valueOf(newValue));

    }
}
