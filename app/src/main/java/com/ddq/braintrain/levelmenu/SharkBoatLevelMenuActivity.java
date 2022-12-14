package com.ddq.braintrain.levelmenu;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.R;
import com.ddq.braintrain.gameactivity.SharkBoatGameActivity;
import com.ddq.braintrain.models.SharkBoatModel;

import java.util.List;

public class SharkBoatLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private BrainTrainDatabase brainTrainDatabase;
    private static List<SharkBoatModel> sharkBoatModels;
    GridLayout sharkBoatLevelLayout;
    AppCompatButton btn;

    boolean canOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_boat_level_menu);

//        sharkBoatLevelLayout = findViewById(R.id.sharkBoatLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(SharkBoatLevelMenuActivity.this);
        sharkBoatModels = new BrainTrainDAO().sharkBoatModels(brainTrainDatabase);
/*
        for (int i = 0; i < sharkBoatModels.size(); i++) {
            btn = new AppCompatButton(SharkBoatLevelMenuActivity.this);
            btn.setText("" + sharkBoatModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(SharkBoatLevelMenuActivity.this, R.drawable.button_shape));
            if (sharkBoatModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(SharkBoatLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            sharkBoatLevelLayout.addView(btn);
            btn.setOnClickListener(SharkBoatLevelMenuActivity.this);
        }*/

        TableLayout tableLayout = findViewById(R.id.table_layout);


        for (SharkBoatModel model : sharkBoatModels) {

            View rowView = getLayoutInflater().inflate(R.layout.table_row_layout, tableLayout, false);
            TextView levelTextView = rowView.findViewById(R.id.level_text_view);
            levelTextView.setText(String.valueOf(model.getLevel()));
            TextView scoreTextView = rowView.findViewById(R.id.score_text_view);
            scoreTextView.setText(String.valueOf(model.getScore()));
            if (model.getCompleteStatus() == 1) {
                //Change row color
                rowView.setBackgroundColor(Color.GREEN);

            }

            if (!canOpen) {
                rowView.setEnabled(false);
                rowView.setAlpha(0.75f);
                Log.d(TAG, "onCreate: " + model.getLevel() + " can't click");
            }
            canOpen = model.getCompleteStatus() == 1;


            tableLayout.addView(rowView);

            TableRow row = rowView.findViewById(R.id.table_row);
            row.setBackgroundColor(Color.YELLOW);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the click event here
                    handleLevelClick(model);
                }
            });
        }

    }

    public void handleLevelClick(SharkBoatModel model) {
        Intent intent = new Intent(SharkBoatLevelMenuActivity.this, SharkBoatGameActivity.class);
        intent.putExtra("level", model.getLevel());
        intent.putExtra("score", model.getScore());
        intent.putExtra("shark", model.getNumberOfShark());
        intent.putExtra("boat", model.getNumberOfBoat());
        intent.putExtra("boatpoint", model.getPointPerBoat());
        intent.putExtra("bitecount", model.getAllowableNumberOfBite());
        intent.putExtra("passpoint", model.getLevelPassScore());
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SharkBoatLevelMenuActivity.this, SharkBoatGameActivity.class);
        intent.putExtra("level", v.getId());

        startActivity(intent);
    }
/*
    private void populateTable() {
        TableLayout tableLayout = findViewById(R.id.table_layout);
        // Retrieve the data from the SQLite database
        List<LevelScore> levelScores = getLevelScoresFromDatabase();
        // Iterate through the data and add a new row for each level/score pair
        for (LevelScore levelScore : levelScores) {
            TableRow row = new TableRow(this);
            TextView levelTextView = new TextView(this);
            levelTextView.setText(String.valueOf(levelScore.level));
            TextView scoreTextView = new TextView(this);
            scoreTextView.setText(String.valueOf(levelScore.score));
            row.addView(levelTextView);
            row.addView(scoreTextView);
            tableLayout.addView(row);
        }
    }*/
}