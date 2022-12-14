package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.ddq.braintrain.gameactivity.GridsHighlightGameActivity;
import com.ddq.braintrain.levelmenu.GridsHighlightLevelMenuActivity;
import com.ddq.braintrain.levelmenu.MissingObjectLevelMenuActivity;
import com.ddq.braintrain.levelmenu.NotInPreviousLevelMenuActivity;
import com.ddq.braintrain.models.HighlightGridsModel;
import com.ddq.braintrain.models.ProgressModel;

import java.util.List;

public class MemoryActivity extends AppCompatActivity {

    CardView notInPreviousCardView, missingObjectCardView, gridsHighlightCardView;
    TextView notInPreviousScore, missingObjectScore, gridsHighlightScore, notInPreviousProgress, missingObjectProgress, gridsHighlightProgress;
    ImageView gridsHighlightComplete, missingObjectComplete, notInPreviousComplete;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel notInPreviousModel;
    private ProgressModel gridsHighlightModel;

    private static int missingObjectCurrentScore;

    public static int getMissingObjectCurrentScore() {
        return missingObjectCurrentScore;
    }

    private ProgressModel missingObjectModel;
    private static List<HighlightGridsModel> highlightGridsModels;

    public static List<HighlightGridsModel> getHighlightGridsModels() {
        return highlightGridsModels;
    }

    SharedPreferences sharedPreferences;
    String gameOneGuide, gameTwoGuide, gameThreeGuide;

    int gridHighlightLevelToPlay = 2;

    static int gridHighlightTotalScore;

    public static int getGridHighlightTotalScore() {
        return gridHighlightTotalScore;
    }

    AppCompatButton gridsHighlightGuideButton, missingObjectGuideButton, notInPreviousGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        notInPreviousScore = findViewById(R.id.notInPreviousScore);
        notInPreviousCardView = findViewById(R.id.notInPreviousCardView);
        missingObjectCardView = findViewById(R.id.missingObjectCardView);
        gridsHighlightCardView = findViewById(R.id.gridsHighlightCardView);
        gridsHighlightScore = findViewById(R.id.gridsHighlightScore);
        notInPreviousProgress = findViewById(R.id.notInPreviousProgress);
        missingObjectProgress = findViewById(R.id.missingObjectProgress);
        gridsHighlightProgress = findViewById(R.id.gridsHighlightProgress);
        gridsHighlightComplete = findViewById(R.id.gridsHighlightComplete);
        missingObjectScore = findViewById(R.id.missingObjectScore);
        missingObjectComplete = findViewById(R.id.missingObjectComplete);
        notInPreviousComplete = findViewById(R.id.notInPreviousComplete);

        gridsHighlightGuideButton = findViewById(R.id.gridsHighlightGuideButton);
        missingObjectGuideButton = findViewById(R.id.missingObjectGuideButton);
        notInPreviousGuideButton = findViewById(R.id.notInPreviousGuideButton);

        brainTrainDatabase = new BrainTrainDatabase(MemoryActivity.this);
        gridsHighlightModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 11);

        gridsHighlightScore.setText("??i???m c???a b???n: " + gridsHighlightModel.getUserScore());
        String result = String.format("%.2f", 100*((float) gridsHighlightModel.getUserScore() / (float) gridsHighlightModel.getMaxScore()));
        gridHighlightTotalScore =gridsHighlightModel.getUserScore();
                Log.d(TAG, "onCreate: "+gridsHighlightModel.getMaxScore());
        gridsHighlightProgress.setText("???? ho??n th??nh: " + result + "%");
        if (gridsHighlightModel.isCompletedStatus()) {
            gridsHighlightProgress.setVisibility(View.GONE);
            gridsHighlightComplete.setVisibility(View.VISIBLE);
        }

        sharedPreferences = getSharedPreferences("guideButton", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gameOneGuide = sharedPreferences.getString("gameOneGuide", "");
        gameTwoGuide = sharedPreferences.getString("gameTwoGuide", "");
        gameThreeGuide = sharedPreferences.getString("gameThreeGuide", "");


        gridsHighlightGuideButton.setVisibility( gameOneGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        notInPreviousGuideButton.setVisibility( gameTwoGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        missingObjectGuideButton.setVisibility( gameThreeGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);

        highlightGridsModels = new BrainTrainDAO().highlightGridsModels(brainTrainDatabase);
        for(HighlightGridsModel model : highlightGridsModels){
            if(model.getCompleteStatus() == 1){
                gridHighlightLevelToPlay++;
            } else {
                if(gridHighlightLevelToPlay>2) gridHighlightLevelToPlay--;
                gridHighlightLevelToPlay/=2;
                break;
            }
        }

        notInPreviousModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 12);
        notInPreviousScore.setText("??i???m c???a b???n: " + notInPreviousModel.getUserScore());
        String notInPreviousPercent = String.format("%.2f", 100*((float) notInPreviousModel.getUserScore() / (float) notInPreviousModel.getMaxScore()));

        Log.d(TAG, "onCreate: "+notInPreviousModel.getMaxScore());
        notInPreviousProgress.setText("???? ho??n th??nh: " + notInPreviousPercent + "%");
        if (notInPreviousModel.isCompletedStatus()) {
            notInPreviousComplete.setVisibility(View.VISIBLE);
        }

        missingObjectModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 13);
        missingObjectCurrentScore = missingObjectModel.getUserScore();
        missingObjectScore.setText("??i???m c???a b???n: " + missingObjectCurrentScore);
        String missingObjectPercent = String.format("%.2f", 100*((float) missingObjectModel.getUserScore() / (float) missingObjectModel.getMaxScore()));
        missingObjectProgress.setText("???? ho??n th??nh: " + missingObjectPercent + "%");
        if (missingObjectModel.isCompletedStatus()) {
            missingObjectComplete.setVisibility(View.VISIBLE);
        }

        gridsHighlightCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemoryActivity.this, GridsHighlightGameActivity.class);
                intent.putExtra("level", gridHighlightLevelToPlay);
                intent.putExtra("trial", 12);
                startActivity(intent);
                finish();
            }
        });

        notInPreviousCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, NotInPreviousLevelMenuActivity.class));
                finish();
            }
        });

        missingObjectCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, MissingObjectLevelMenuActivity.class));
                finish();
            }
        });

        gridsHighlightCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                gridsHighlightGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameOneGuide", "");
                editor.apply();
                return false;
            }
        });

        notInPreviousCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                notInPreviousGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameTwoGuide", "");
                editor.apply();
                return false;
            }
        });

        missingObjectCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                missingObjectGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameThreeGuide", "");editor.apply();
                return false;
            }
        });

        gridsHighlightGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MemoryActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("M???t ma tr???n bi???n ?????i s??? xu???t hi???n ng???u nhi??n v???i m???t m???u c??c kh???i ???????c hi???n th??? t???m th???i (3 gi??y)\n\n" +
                        "Nhi???m v??? c???a ng?????i ch??i l?? b??o c??o v??? tr?? c???a c??c kh???i b???ng c??ch ch???m v??o v??? tr?? c???a ma tr???n n??i c??c kh???i ???????c hi???n th???.");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        gridsHighlightGuideButton.setVisibility(View.GONE);
                        editor.putString("gameOneGuide", "notAppear");
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

        missingObjectGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MemoryActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Nhi???m v??? c???a ng?????i ch??i l?? t??m ????? v???t tr??n t???m th??? c?? d???u ch???m h???i");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        missingObjectGuideButton.setVisibility(View.GONE);
                        editor.putString("gameThreeGuide", "notAppear");
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

        notInPreviousGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MemoryActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Ng?????i ch??i ???????c ch???n ng???u nhi??n 1 trong 3 th??? n??y v?? ph???i ghi nh??? ????? v???t trong th??? ????\n" +
                        "\n" +
                        "Trong m???i v??ng, s??? l?????ng th??? t??ng l??n 1 v?? ng?????i ch??i ph???i ch???n 1 th??? c?? h??nh d???ng kh??c kh??ng ???????c ch???n tr?????c ????");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        notInPreviousGuideButton.setVisibility(View.GONE);
                        editor.putString("gameTwoGuide", "notAppear");
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