package com.trzewik.quizwirtualnapolska.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;
import com.trzewik.quizwirtualnapolska.logic.PercentageCalculator;
import com.trzewik.quizwirtualnapolska.logic.RateMessageProvider;

public class QuizResultActivity extends AppCompatActivity {
    private static String UNDER_TITLE_TEXT = "Odpowiedziałeś na:";
    private static String ANSWERS_TEX = "pytań";
    private static String QUIZ_LIST_BUTTON_TEXT = "Przejdź do listy quizów";
    private static String TRY_AGAIN_BUTTON_TEXT = "Rozwiąż jeszcze raz";
    private TextView rateMessage;
    private TextView underTitle;
    private TextView percentage;
    private TextView answersText;
    private Button quizList;
    private Button tryAgain;
    private DatabaseController databaseController = new DatabaseController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        rateMessage = findViewById(R.id.quizTitle);
        underTitle = findViewById(R.id.underTitle);
        percentage = findViewById(R.id.correctAnswersPercent);
        answersText = findViewById(R.id.answersText);
        quizList = findViewById(R.id.quizList);
        tryAgain = findViewById(R.id.tryAgain);

        populateView();

    }

    private void populateView() {
        populateRateMessage();
        populateUnderTitle();
        populatePercentage();
        populateAnswers();
        populateTryAgainButton();
        populateQuizListButton();
    }

    private void populateRateMessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                long quizId = getId();
                int percentageOfCorrectAnswers = new PercentageCalculator().getPercentageOfCorrectAnswers(getId());
                String message = new RateMessageProvider().getRateMessage(quizId, percentageOfCorrectAnswers);
                rateMessage.setText(message);
            }
        });
    }

    private void populateUnderTitle() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                underTitle.setText(UNDER_TITLE_TEXT);
            }
        });
    }

    private void populatePercentage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                long quizId = getId();
                String percent = new PercentageCalculator().getPercentageOfCorrectAnswers(quizId) + "%";
                percentage.setText(percent);
            }
        });
    }

    private void populateAnswers() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                answersText.setText(ANSWERS_TEX);
            }
        });
    }

    private void populateTryAgainButton() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tryAgain.setText(TRY_AGAIN_BUTTON_TEXT);
                tryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(QuizResultActivity.this, QuizQuestionActivity.class);
                        intent.putExtras(getIntent().getExtras());
                        databaseController.clearQuizAnswers(getId());
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    private void populateQuizListButton() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                quizList.setText(QUIZ_LIST_BUTTON_TEXT);
                quizList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(QuizResultActivity.this, QuizListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    private long getId() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        return bundle.getLong("id");
    }
}
