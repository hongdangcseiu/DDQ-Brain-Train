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

import com.ddq.braintrain.gameactivity.CompleteWordGameActivity;
import com.ddq.braintrain.gameactivity.ConjunctionGameActivity;
import com.ddq.braintrain.gameactivity.FindWordGameActivity;
import com.ddq.braintrain.gameactivity.SortingCharGameActivity;
import com.ddq.braintrain.models.ProgressModel;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView completeWordScore, findWordScore, conjunctionScore, sortingCharScore;
    CardView completeWordCardView, findWordCardView, conjunctionCardView, sortingCharCardView;
    ImageView completeWordCompleted, findWordCompleted, conjunctionCompleted, sortingCharCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel completeWordModel, findWordModel, conjunctionModel, sortingCharModel;


    SharedPreferences sharedPreferences;
    String gameOneGuide, gameTwoGuide, gameThreeGuide, gameFourGuide;

    AppCompatButton completeWordGuideButton, findWordGuideButton, conjunctionGuideButton, sortingCharGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        completeWordScore = findViewById(R.id.completeWordScore);
        findWordScore = findViewById(R.id.findWordScore);
        conjunctionScore = findViewById(R.id.conjunctionScore);
        sortingCharScore = findViewById(R.id.sortingCharScore);
        completeWordCardView = findViewById(R.id.completeWordCardView);
        findWordCardView = findViewById(R.id.findWordCardView);
        conjunctionCardView = findViewById(R.id.conjunctionCardView);
        sortingCharCardView = findViewById(R.id.sortingCharCardView);
        completeWordCompleted = findViewById(R.id.completeWordComplete);
        findWordCompleted = findViewById(R.id.findWordCompleted);
        conjunctionCompleted = findViewById(R.id.conjunctionComplete);
        sortingCharCompleted = findViewById(R.id.sortingCharComplete);

        completeWordGuideButton = findViewById(R.id.completeWordGuideButton);
        findWordGuideButton = findViewById(R.id.findWordGuideButton);
        conjunctionGuideButton = findViewById(R.id.conjunctionGuideButton);
        sortingCharGuideButton = findViewById(R.id.sortingCharGuideButton);


        sharedPreferences = getSharedPreferences("guideButton", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gameOneGuide = sharedPreferences.getString("gameOneGuideLanguage", "");
        gameTwoGuide = sharedPreferences.getString("gameTwoGuideLanguage", "");
        gameThreeGuide = sharedPreferences.getString("gameThreeGuideLanguage", "");
        gameFourGuide = sharedPreferences.getString("gameFourGuideLanguage", "");


        completeWordGuideButton.setVisibility( gameOneGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        findWordGuideButton.setVisibility( gameTwoGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        conjunctionGuideButton.setVisibility( gameThreeGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        sortingCharGuideButton.setVisibility( gameFourGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);



        brainTrainDatabase = new BrainTrainDatabase(LanguageActivity.this);
        completeWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 31);
        completeWordScore.setText("??i???m c???a b???n: " + completeWordModel.getUserScore());
        if (completeWordModel.isCompletedStatus()) {
            completeWordCompleted.setVisibility(View.VISIBLE);
        }

        findWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 32);
        findWordScore.setText("??i???m c???a b???n: " + findWordModel.getUserScore());
        if (findWordModel.isCompletedStatus()) {
            findWordCompleted.setVisibility(View.VISIBLE);
        }

        conjunctionModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 33);
        conjunctionScore.setText("??i???m c???a b???n: " + conjunctionModel.getUserScore());
        if (conjunctionModel.isCompletedStatus()) {
            conjunctionCompleted.setVisibility(View.VISIBLE);
        }

        sortingCharModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 34);
        sortingCharScore.setText("??i???m c???a b???n: " + sortingCharModel.getUserScore());
        if (sortingCharModel.isCompletedStatus()) {
            sortingCharCompleted.setVisibility(View.VISIBLE);
        }
        completeWordCardView.setOnClickListener(LanguageActivity.this);
        findWordCardView.setOnClickListener(LanguageActivity.this);
        conjunctionCardView.setOnClickListener(LanguageActivity.this);
        sortingCharCardView.setOnClickListener(LanguageActivity.this);

        completeWordCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                completeWordGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameOneGuideLanguage", "");
                editor.apply();
                return false;
            }
        });

        findWordCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                findWordGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameTwoGuideLanguage", "");
                editor.apply();
                return false;
            }
        });

        conjunctionCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                conjunctionGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameThreeGuideLanguage", "");editor.apply();
                return false;
            }
        });

        sortingCharCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sortingCharGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameFourGuideLanguage", "");editor.apply();
                return false;
            }
        });

        completeWordGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Tr?? ch??i s??? cung c???p cho ng?????i d??ng 1 ch??? c??i\n" +
                        "\n" +
                        "Trong v??ng 2 ph??t, h??y t??m nh???ng t??? c?? ngh??a b???t ?????u b???ng ch??? c??i n??y\n" +
                        "\n" +
                        "T??? b???n t??m th???y c??ng d??i, b???n c??ng nh???n ???????c s??? ??i???m cao");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        completeWordGuideButton.setVisibility(View.GONE);
                        editor.putString("gameOneGuideLanguage", "notAppear");
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

        findWordGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Trong v??ng 2 ph??t, nhi???m v??? l?? t??m nh???ng t??? c?? th??? gh??p v???i t??? cho s???n ban ?????u th??nh t??? gh??p c?? ngh??a");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        findWordGuideButton.setVisibility(View.GONE);
                        editor.putString("gameTwoGuideLanguage", "notAppear");
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

        conjunctionGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Ng?????i d??ng ti???p t???c t??m m???t t??? kh??c ????? n???i v???i t??? cu???i c??ng trong t??? gh??p ???? t??m ???????c tr?????c ???? ????? t???o t??? gh??p c?? ngh??a m???i\n" +
                        "\n" +
                        "Ti???p t???c l??m ??i???u n??y cho ?????n khi kh??ng th??? t??m th???y nhi???u t??? h??n ????? ph?? h???p");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        conjunctionGuideButton.setVisibility(View.GONE);
                        editor.putString("gameThreeGuideLanguage", "notAppear");
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

        sortingCharGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("H?????ng D???n");
                alert.setMessage("Tr?? ch??i n??y s??? cung c???p m???t c???m t??? c?? c??c ch??? c??i ???????c x??o tr???n\n" +
                        "\n" +
                        "Nhi???m v??? c???a ng?????i ch??i l?? s???p x???p l???i th??? t??? c??c ch??? c??i ????? t??m ra t??? ch??nh x??c");
                alert.setCancelable(false);

                alert.setNegativeButton("Kh??ng hi???n l???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sortingCharGuideButton.setVisibility(View.GONE);
                        editor.putString("gameFourGuideLanguage", "notAppear");
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
            case R.id.completeWordCardView:
                intent = new Intent(LanguageActivity.this, CompleteWordGameActivity.class);
                break;

            case R.id.findWordCardView:
                intent = new Intent(LanguageActivity.this, FindWordGameActivity.class);
                break;
            case R.id.conjunctionCardView:
                intent = new Intent(LanguageActivity.this, ConjunctionGameActivity.class);
                break;
            default:
                intent = new Intent(LanguageActivity.this, SortingCharGameActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

}