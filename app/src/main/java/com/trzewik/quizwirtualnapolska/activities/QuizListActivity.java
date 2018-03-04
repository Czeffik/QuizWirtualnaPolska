package com.trzewik.quizwirtualnapolska.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.adapters.QuizAdapter;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.logic.DataGainer;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;
import com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties;

import java.util.List;

import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.BUTTON_ACTION_NUMBER_OF_QUIZZES;
import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.INITIAL_NUMBER_OF_QUIZZES;
import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.START_INDEX;
import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.buttonText;
import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.firstRun;
import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.information;
import static com.trzewik.quizwirtualnapolska.properties.QuizListActivityProperties.internetConnectionRequired;

public class QuizListActivity extends AppCompatActivity {
    private long lastClickTime = 0;

    private ListView listView;
    private Button button;
    private ProgressBar progressBar;
    private DatabaseController databaseController = new DatabaseController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        listView = findViewById(R.id.list);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressbar);

        if (isOnline() || databaseController.getNumberOfQuizzes() > 0) {
            new FirstRunLoader().execute();
        } else {
            populateAlertDialog(information, firstRun);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateQuizList();
    }

    private void populateQuizList() {
        listView.setVisibility(View.VISIBLE);
        QuizAdapter quizAdapter = new QuizAdapter(databaseController.getAllQuizzes(), getApplicationContext());
        listView.setAdapter(quizAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                preventDoubleClick();
                List<Quiz> quizzes = databaseController.getAllQuizzes();
                Quiz quiz = quizzes.get(position);
                Intent intent = new Intent(QuizListActivity.this, QuizQuestionActivity.class);
                intent.putExtras(setBundle(quiz.getId()));
                startActivity(intent);

            }
        });
    }

    private void populateButton(){
        button.setText(buttonText);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preventDoubleClick();
                if (isOnline()) {
                    new ExtraQuizLoader().execute();
                }
                else{
                    populateAlertDialog(information, internetConnectionRequired);
                }
            }
        });
    }

    private void populateAlertDialog(String title, String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(title)
                .setMessage(message)
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

    private void preventDoubleClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
    }

    private class FirstRunLoader extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... param) {
            if (databaseController.getNumberOfQuizzes() == 0) {
                new DataGainer().retrieveData(getApplicationInfo().dataDir, START_INDEX, INITIAL_NUMBER_OF_QUIZZES);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            listView.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            populateQuizList();
            populateButton();
        }
    }

    private class ExtraQuizLoader extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... param) {
            new DataGainer().fetchNewQuizzes(getApplicationInfo().dataDir, BUTTON_ACTION_NUMBER_OF_QUIZZES);
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            populateQuizList();
            populateButton();
        }
    }
}
