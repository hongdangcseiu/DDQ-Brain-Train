package com.ddq.braintrain;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.ddq.braintrain.gameactivity.CompareGameActivity;
import com.ddq.braintrain.gameactivity.FindOperatorGameRoundActivity;
import com.ddq.braintrain.levelmenu.CompareLevelMenuActivity;
import com.ddq.braintrain.levelmenu.FindOperatorLevelMenuActivity;
import com.ddq.braintrain.levelmenu.FindOperatorLevelSelectActivity;
import com.ddq.braintrain.models.ProgressModel;

public class MathActivity extends AppCompatActivity {

    TextView findOperatorScore, compareScore, findOperatorProgress, compareProgress;
    CardView findOperatorCardView, compareCardView;
    ImageView findOperatorCompleted, compareCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel findOperatorModel, compareModel;

    AppCompatButton findOperatorGuideButton, compareGuideButton;

    SharedPreferences sharedPreferences;
    String gameOneGuide, gameTwoGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        findOperatorCardView = findViewById(R.id.findOperatorCardView);
        findOperatorScore = findViewById(R.id.findOperatorScore);
        compareScore = findViewById(R.id.compareScore);
        findOperatorProgress = findViewById(R.id.findOperatorProgress);
        compareProgress = findViewById(R.id.compareProgress);
        compareCardView = findViewById(R.id.compareCardView);
        findOperatorCompleted = findViewById(R.id.findOperatorCompleted);
        compareCompleted = findViewById(R.id.compareComplete);

        findOperatorGuideButton = findViewById(R.id.findOperatorGuideButton);
        compareGuideButton = findViewById(R.id.compareGuideButton);


        sharedPreferences = getSharedPreferences("guideButton", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gameOneGuide = sharedPreferences.getString("gameOneGuideMath", "");
        gameTwoGuide = sharedPreferences.getString("gameTwoGuideMath", "");

        findOperatorGuideButton.setVisibility( gameOneGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        compareGuideButton.setVisibility( gameTwoGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);


        brainTrainDatabase = new BrainTrainDatabase(MathActivity.this);
        findOperatorModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 41);
        findOperatorScore.setText("??i???m c???a b???n: " + findOperatorModel.getUserScore());
        String result1 = String.format("%.2f", 100*((float) findOperatorModel.getUserScore() / (float) findOperatorModel.getMaxScore()));
        findOperatorProgress.setText("???? ho??n th??nh: " + result1 + "%");
        if (findOperatorModel.isCompletedStatus()) {
            findOperatorCompleted.setVisibility(View.VISIBLE);
        }

        compareModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 42);
        compareScore.setText("??i???m c???a b???n: " + compareModel.getUserScore());
        String result2 = String.format("%.2f", 100*((float) compareModel.getUserScore() / (float) compareModel.getMaxScore()));
        compareProgress.setText("???? ho??n th??nh: " + result2 + "%");
        if (compareModel.isCompletedStatus()) {
            compareCompleted.setVisibility(View.VISIBLE);
        }

        compareCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, CompareGameActivity.class);
                startActivity(intent);
            }
        });
        findOperatorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, FindOperatorLevelSelectActivity.class);
                startActivity(intent);
            }
        });


        compareCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                compareGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameOneGuideMath", "");
                editor.apply();
                return false;
            }
        });

        findOperatorCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                findOperatorGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameTwoGuideMath", "");
                editor.apply();
                return false;
            }
        });

        compareGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MathActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Tr?? ch??i m?? ph???ng c???nh mua s???m. Nhi???m v??? l?? ch???n s???n ph???m c?? chi ph?? th???p h??n ????? mua\n" +
                        "\n" +
                        "Gi?? c???a s???n ph???m ???????c th??? hi???n b???ng c??c bi???u th???c to??n h???c bao g???m c??c ph??p t??nh ????n gi???n nh?? c???ng, tr???, nh??n v?? chia\n" +
                        "\n" +
                        "Ng?????i ch??i ph???i t??nh gi?? c???a c??c s???n ph???m r???i ch???m v??o s???n ph???m c?? gi?? tr??? th???p nh???t");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        compareGuideButton.setVisibility(View.GONE);
                        editor.putString("gameOneGuideMath", "notAppear");
                        editor.apply();
                    }
                });
                alert.setPositiveButton("???? Hi???u", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        findOperatorGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MathActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Nhi???m v??? c???a ng?????i ch??i l?? t??m hai s??? c?? t???ng l?? b???i s??? c???a ch???c, b???i s??? c???a tr??m ho???c b???i s??? c???a ngh??n");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        findOperatorGuideButton.setVisibility(View.GONE);
                        editor.putString("gameThreeGuideMath", "notAppear");
                        editor.apply();
                    }
                });
                alert.setPositiveButton("???? Hi???u", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }
}