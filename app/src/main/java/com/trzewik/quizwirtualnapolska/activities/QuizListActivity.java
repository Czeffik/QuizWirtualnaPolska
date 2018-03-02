package com.trzewik.quizwirtualnapolska.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.adapters.QuizAdapter;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.logic.DataLoader;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;

import java.util.List;

public class QuizListActivity extends AppCompatActivity {
    private static int START_INDEX = 0;
    private static int MAX_RESULT = 10;

    private ListView listView;
    private ProgressBar progressBar;
    private DatabaseController databaseController = new DatabaseController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        listView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progressBar);
        if (isOnline() || databaseController.getNumberOfQuizzes() > 0) {
            insertAndDisplayData(START_INDEX, MAX_RESULT);
        } else {
            populateAlertDialog();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateQuizList();
    }

    private void insertAndDisplayData(final int startIndex, final int maxResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Quiz> quizzes = databaseController.getQuizListFromDb();
                if (App.get().isForceUpdate() || quizzes.isEmpty()) {
                    populateProgressBar();
                    new DataLoader().retrieveData(getApplicationInfo().dataDir, startIndex, maxResult);
                    populateQuizList();
                } else {
                    populateQuizList();
                }
            }
        }).start();
    }

    private void populateQuizList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                populateProgressBar();
                QuizAdapter quizAdapter = new QuizAdapter(databaseController.getQuizListFromDb(), getApplicationContext());
                listView.setAdapter(quizAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        List<Quiz> quizzes = databaseController.getQuizListFromDb();
                        Quiz quiz = quizzes.get(position);
                        Intent intent = new Intent(QuizListActivity.this, QuizQuestionActivity.class);
                        intent.putExtras(setBundle(quiz.getId()));
                        startActivity(intent);

                    }
                });
            }
        });
    }

    private void populateProgressBar(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int numberOfQuizzes = databaseController.getNumberOfQuizzes();
                while (numberOfQuizzes<MAX_RESULT){
                    progressBar.setMax(MAX_RESULT);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(numberOfQuizzes);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    numberOfQuizzes = databaseController.getNumberOfQuizzes();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void populateAlertDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Informacja")
                .setMessage("Przy pierwszym uruchomieniu wymagany jest dostęp do internetu! Jeśli chcesz korzystać z aplikacji włącz Internet i spróbuj ponownie.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private Bundle setBundle(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        return bundle;
    }


}
