package com.example.michael.fortytwo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Michael on 16.08.2015.
 */
public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //Sets random numbers to all the Buttons
        Button topLeftButton = (Button)findViewById(R.id.top_left_button);
        topLeftButton.setText(String.valueOf(createRandomNumber(100)));

        Button bottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        bottomLeftButton.setText(String.valueOf(createRandomNumber(100)));

        Button topRightButton = (Button)findViewById(R.id.top_right_button);
        topRightButton.setText(String.valueOf(createRandomNumber(100)));

        Button bottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        bottomRightButton.setText(String.valueOf(createRandomNumber(100)));

        //set the random number to subtract from

        TextView startNumber = (TextView) findViewById(R.id.initial_number);
        startNumber.setText(String.valueOf(createRandomNumber(300)));




    }

    private static int createRandomNumberSmall (int range ){

        Random random = new Random();

        return random.nextInt(range);

    }
    private static int createRandomNumber (int range ){

        Random random = new Random();

        return random.nextInt(range);

    }


}
