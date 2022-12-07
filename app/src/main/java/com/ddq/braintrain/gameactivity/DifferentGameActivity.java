package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.DifferentLevelMenuActivity;
import com.ddq.braintrain.models.DifferentModel;

import java.util.List;

public class DifferentGameActivity extends AppCompatActivity {

    ImageView imageViewSample;
    TextView questionTextView, resultTextView, timeTextView, scoreTextView;
    Button resultButton, nextImageButton;
    int level;

    float x, y;
    long timeLeft;

    private List<DifferentModel> models;
    CountDownTimer timer, innerTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_game);

        imageViewSample = findViewById(R.id.imageViewSample);
        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        resultButton = findViewById(R.id.resultButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.timeTextView);
        nextImageButton = findViewById(R.id.nextImageButton);

        resultButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        nextImageButton.setVisibility(View.INVISIBLE);

        models = DifferentLevelMenuActivity.getDifferentModels();

        Intent intent = getIntent();
         level = intent.getIntExtra("level", 0);

        gameStart();

        //generateImage(level);

        imageViewSample.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    x = event.getX();
                    y = event.getY();

                    if((x > models.get(level-1).getxCoordinate() - 75 && x < models.get(level-1).getxCoordinate() + 75) && (y > models.get(level-1).getyCoordinate() - 75 && y < models.get(level-1).getyCoordinate() + 75)){
                        Log.d(TAG, "cau tra loi dung " + x + " "+ y);
                        timer.cancel();
                        resultTextView.setVisibility(View.VISIBLE);
                        resultTextView.setText("Câu trả lời đúng!");
                        nextImageButton.setVisibility(View.VISIBLE);

                    } else {

                        resultTextView.setText("Câu trả lời sai!");
                        Log.d(TAG, "cau tra loi sai "+ x + " "+ y);
                    }
                }
                return true;
            }
        });

        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level++;
                gameContinue();
                nextImageButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void generateImage(){
        imageViewSample.setImageResource(getResources().getIdentifier(models.get(level-1).getImage(), "drawable", getPackageName()));
        imageViewSample.setTag(""+(level - 1));
    }

    public void gameStart(){
        generateImage();
        startTimer(120000);
        questionTextView.setText(models.get(level-1).getImageName());
    }

    public void gameContinue(){
        generateImage();
        startTimer(timeLeft*1000);
        questionTextView.setText(models.get(level-1).getImageName());
    }

    public void startTimer(long timeLeftSecond){
        timer = new CountDownTimer(timeLeftSecond,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                 timeLeft = millisUntilFinished / 1000;
                updateTimeLeft(timeLeft);
            }

            @Override
            public void onFinish() {
                gameFinish();
            }
        }.start();
    }

    public void updateTimeLeft(long timeLeft){
        timeTextView.setText("Còn lại: " + timeLeft + " giây!");
    }

    public void gameFinish(){
        imageViewSample.setClickable(false);
    }


}