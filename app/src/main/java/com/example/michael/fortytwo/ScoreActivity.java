package com.example.michael.fortytwo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author Michael Graf
 * @author Marius Herr on 16.08.2015.
 */

public class ScoreActivity extends Activity {

    public int totalScoreNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        TextView curScore = (TextView) findViewById(R.id.current_score);
        Intent scorePassed =  getIntent();
        int currentScore = (int) scorePassed.getSerializableExtra("Score");
        curScore.append(String.valueOf(currentScore));

        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("highestScore", 0);

        int highestScore = getPreferences(MODE_PRIVATE).getInt("highestScore", 0);



        TextView totalScore = (TextView) findViewById(R.id.total_score);
        totalScoreNumber =  currentScore + totalScoreNumber;
        totalScore.append(String.valueOf(currentScore));

    }

    public void onPlayAgainButtonClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
