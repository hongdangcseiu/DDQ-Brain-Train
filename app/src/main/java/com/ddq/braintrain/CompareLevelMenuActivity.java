package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class CompareLevelMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private BrainTrainDatabase brainTrainDatabase;
    private List<CompareModel> compareModels;
    GridLayout compareLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_level_menu);

        compareLevelLayout = findViewById(R.id.compareLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(CompareLevelMenuActivity.this);

        compareModels = new BrainTrainDAO().compareModels(brainTrainDatabase);

        for (int i = 0; i < compareModels.size(); i++) {
            btn = new AppCompatButton(CompareLevelMenuActivity.this);
            btn.setText("" + compareModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    150,
                    150
            );
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(CompareLevelMenuActivity.this, R.drawable.button_shape));
            if (compareModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(CompareLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            compareLevelLayout.addView(btn);
            btn.setOnClickListener(CompareLevelMenuActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CompareLevelMenuActivity.this, CompareGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}