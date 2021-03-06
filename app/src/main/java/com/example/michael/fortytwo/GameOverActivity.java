package com.example.michael.fortytwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Michael Graf
 * @author Marius Herr on 16.08.2015.
 */
public class GameOverActivity extends Activity {

    public int totalScoreNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.over_layout);

        TextView curScore = (TextView) findViewById(R.id.current_score);
        Intent scorePassed =  getIntent();
        int currentScore = (int) scorePassed.getSerializableExtra("Score");
        curScore.append(String.valueOf(currentScore));

        TextView totalScore = (TextView) findViewById(R.id.total_score);
        totalScoreNumber =  (int) scorePassed.getSerializableExtra("highscore");
        totalScore.append(String.valueOf(totalScoreNumber));
        }

    public void onPlayAgainButtonClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
}

