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

import com.ddq.braintrain.levelmenu.DifferentLevelMenuActivity;
import com.ddq.braintrain.levelmenu.FlashCardLevelMenuActivity;
import com.ddq.braintrain.levelmenu.SharkBoatLevelMenuActivity;
import com.ddq.braintrain.models.ProgressModel;

public class AttentionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView sharkBoatScore, sharkBoatProgress, flashCardScore, flashCardProgress, differentScore, differentProgress;
    CardView differentCardView, flashCardCardView, sharkBoatCardView;
    ImageView differentCompleted, flashCardCompleted, sharkBoatCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel differentModel, flashCardModel, sharkBoatModel;

    SharedPreferences sharedPreferences;
    String gameOneGuide, gameTwoGuide, gameThreeGuide;

    AppCompatButton differentGuideButton, flashCardGuideButton, sharkBoatGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        sharkBoatScore = findViewById(R.id.sharkBoatScore);
        sharkBoatProgress = findViewById(R.id.sharkBoatProgress);
        flashCardScore = findViewById(R.id.flashCardScore);
        flashCardProgress = findViewById(R.id.flashCardProgress);
        differentScore = findViewById(R.id.differentScore);
        differentProgress = findViewById(R.id.differentProgress);
        differentCardView = findViewById(R.id.differentCardView);
        flashCardCardView = findViewById(R.id.flashCardCardView);
        sharkBoatCardView = findViewById(R.id.sharkBoatCardView);
        differentCompleted = findViewById(R.id.differentComplete);
        flashCardCompleted = findViewById(R.id.flashCardComplete);
        sharkBoatCompleted = findViewById(R.id.sharkBoatComplete);

        differentGuideButton = findViewById(R.id.differentGuideButton);
        flashCardGuideButton = findViewById(R.id.flashCardGuideButton);
        sharkBoatGuideButton = findViewById(R.id.sharkBoatGuideButton);


        sharedPreferences = getSharedPreferences("guideButton", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gameOneGuide = sharedPreferences.getString("gameOneGuideAttention", "");
        gameTwoGuide = sharedPreferences.getString("gameTwoGuideAttention", "");
        gameThreeGuide = sharedPreferences.getString("gameThreeGuideAttention", "");


        differentGuideButton.setVisibility( gameOneGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        flashCardGuideButton.setVisibility( gameTwoGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        sharkBoatGuideButton.setVisibility( gameThreeGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);


        brainTrainDatabase = new BrainTrainDatabase(AttentionActivity.this);
        differentModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 21);
        differentScore.setText("??i???m c???a b???n: " + differentModel.getUserScore());
        String different = String.format("%.2f", 100*((float) differentModel.getUserScore() / (float) differentModel.getMaxScore()));
        differentProgress.setText("???? ho??n th??nh: " + different + "%");
        if (differentModel.isCompletedStatus()) {
            differentCompleted.setVisibility(View.VISIBLE);
        }

        flashCardModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 22);
        flashCardScore.setText("??i???m c???a b???n: " + flashCardModel.getUserScore());
        String flashCardPercent = String.format("%.2f", 100*((float) flashCardModel.getUserScore() / (float) flashCardModel.getMaxScore()));
        flashCardProgress.setText("???? ho??n th??nh: " + flashCardPercent + "%");
        if (flashCardModel.isCompletedStatus()) {
            flashCardCompleted.setVisibility(View.VISIBLE);
        }

        sharkBoatModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 23);
        sharkBoatScore.setText("??i???m c???a b???n: " + sharkBoatModel.getUserScore());
        String sharkBoatPercent = String.format("%.2f", 100*((float) sharkBoatModel.getUserScore() / (float) sharkBoatModel.getMaxScore()));
        sharkBoatProgress.setText("???? ho??n th??nh: " + sharkBoatPercent + "%");
        if (sharkBoatModel.isCompletedStatus()) {
            sharkBoatCompleted.setVisibility(View.VISIBLE);
        }

        differentCardView.setOnClickListener(AttentionActivity.this);
        flashCardCardView.setOnClickListener(AttentionActivity.this);
        sharkBoatCardView.setOnClickListener(AttentionActivity.this);

        differentCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                differentGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameOneGuideAttention", "");
                editor.apply();
                return false;
            }
        });

        flashCardCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                flashCardGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameTwoGuideAttention", "");
                editor.apply();
                return false;
            }
        });

        sharkBoatCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sharkBoatGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameThreeGuideAttention", "");editor.apply();
                return false;
            }
        });


        differentGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AttentionActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Nhi???m v??? l?? t??m m???t ??i???m kh??c bi???t duy nh???t trong b???c tranh");
                alert.setCancelable(false);
                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        differentGuideButton.setVisibility(View.GONE);
                        editor.putString("gameOneGuideAttention", "notAppear");
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

        flashCardGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AttentionActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Tr?? ch??i s??? cung c???p c??c c???p h??nh ???nh kh??c nhau\n" +
                        "\n" +
                        "Nhi???m v??? c???a ng?????i ch??i l?? ch???n ch??nh x??c c??c h??nh gh??p th??nh m???t c???p");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        flashCardGuideButton.setVisibility(View.GONE);
                        editor.putString("gameTwoGuideAttention", "notAppear");
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

        sharkBoatGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AttentionActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Ng?????i ch??i ph???i ?????m b???o an to??n cho t??u thuy???n tr??n bi???n b???ng c??ch ng??n ch???n c?? m???p ?????n c???n thuy???n\n" +
                        "\n" +
                        "Khi ng?????i ch??i d??? ??o??n c?? m???p s??? va v??o thuy???n, h??y ch???m v??o m??n h??nh ????? t???o s??ng bi???n\n" +
                        "\n" +
                        "S??ng s??? khi???n c?? m???p d???t ra xa thuy???n v?? b??i theo h?????ng kh??c\n" +
                        "\n" +
                        "Trong 2 ph??t, c??? g???ng kh??ng ????? con c?? m???p n??o ph?? thuy???n");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sharkBoatGuideButton.setVisibility(View.GONE);
                        editor.putString("gameThreeGuideAttention", "notAppear");
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.differentCardView:
                intent = new Intent(AttentionActivity.this, DifferentLevelMenuActivity.class);
                break;
            case R.id.flashCardCardView:
                intent = new Intent(AttentionActivity.this, FlashCardLevelMenuActivity.class);
                break;
            default:
                intent = new Intent(AttentionActivity.this, SharkBoatLevelMenuActivity.class);
                break;
        }
        startActivity(intent);
    }
}