package com.example.michael.fortytwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Michael on 16.08.2015.
 */
public class GameActivity extends Activity {
    Button topLeftButton,bottomLeftButton,topRightButton,bottomRightButton;
    TextView startNumber;
    boolean gameOver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //Sets random numbers to all the Buttons
        topLeftButton = (Button)findViewById(R.id.top_left_button);
        topLeftButton.setText(String.valueOf(createRandomNumberInRange(1, 5)));

        bottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        bottomLeftButton.setText(String.valueOf(createRandomNumberInRange(20, 50)));

        topRightButton = (Button)findViewById(R.id.top_right_button);
        topRightButton.setText(String.valueOf(createRandomNumberInRange(5, 20)));

        bottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        bottomRightButton.setText(String.valueOf(createRandomNumberInRange(50, 100)));

        //set the random number to subtract from

        startNumber = (TextView) findViewById(R.id.initial_number);
        startNumber.setText(String.valueOf(createRandomNumberInRange(100, 300)));








    }

    private static int createRandomNumberInRange (int top, int bottom ){


        Random random = new Random();

        int range =  top -bottom +1;
        int number =(int) (range*random.nextDouble())+ bottom;

        return number;




    }


    public void onLeftButtonClick(View view) {
        subtractButtonValue(startNumber,topLeftButton);

    }

    public void onRightButton1Click(View view) {
        subtractButtonValue(startNumber,topRightButton);
    }


    public void onBottomLeftButtonClick(View view) {
        subtractButtonValue(startNumber,bottomLeftButton);
    }

    public void onBottomRightButtonClick(View view) {
        subtractButtonValue(startNumber,bottomRightButton);
    }

    public void subtractButtonValue(TextView number, Button button){
        int buttonValule = Integer.parseInt((String) button.getText());
        int textValue = Integer.parseInt((String) number.getText());
        int newValue =  textValue-buttonValule;

        number.setText(String.valueOf(newValue));



    }
}
