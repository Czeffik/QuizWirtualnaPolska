package com.trzewik.quizwirtualnapolska.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.adapters.AnswerListAdapter;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;
import com.trzewik.quizwirtualnapolska.logic.QuestionAnswerChecker;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType;

import java.util.List;

public class QuizQuestionActivity extends AppCompatActivity {
    private long lastClickTime = 0;
    private ListView listView;
    private TextView questionView;
    private TextView titleView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private DatabaseController databaseController = new DatabaseController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        listView = findViewById(R.id.list);
        questionView = findViewById(R.id.text);
        titleView = findViewById(R.id.quizTitle);
        imageView = findViewById(R.id.image);
        progressBar = findViewById(R.id.progressBar);

        populateView();
    }

    private void populateView() {
        long quizId = getId();
        Quiz quiz = databaseController.getQuizById(quizId);

        List<QuizQuestion> quizQuestions = databaseController.getNotAnsweredQuizQuestionsByQuizId(quizId);
        if (quizQuestions.size() > 0) {
            QuizQuestion quizQuestion = quizQuestions.get(0);
            long questionId = quizQuestion.getId();
            List<QuestionAnswer> questionAnswers = databaseController.getQuizAnswerByQuestionId(questionId);
            populateTitle(quiz);
            populateQuestion(quizQuestion);
            populateAnswers(questionAnswers);
            populateProgressBar(quizId);
        } else {
            Intent intent = new Intent(QuizQuestionActivity.this, QuizResultActivity.class);
            intent.putExtras(getIntent().getExtras());
            startActivity(intent);
            finish();
        }
    }

    private void populateTitle(final Quiz quiz) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleView.setText(quiz.getTitle());
            }
        });
    }

    private void populateQuestion(final QuizQuestion quizQuestion) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                questionView.setText(quizQuestion.getText());
                if (quizQuestion.getQuestionType().equals(QuestionType.QUESTION_TEXT_IMAGE.toString())) {
                    Bitmap bMap = BitmapFactory.decodeFile(quizQuestion.getPathToImage());
                    imageView.setImageBitmap(bMap);
                }
            }
        });
    }

    private void populateAnswers(final List<QuestionAnswer> questionAnswers) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(new AnswerListAdapter(questionAnswers, getApplicationContext()));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        preventDoubleClick();
                        new QuestionAnswerChecker().setQuestionAnswer(questionAnswers.get(i));
                        populateView();
                    }
                });
            }
        });
    }

    private void populateProgressBar(final long quizId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int allQuestions = databaseController.getNumberOfQuestions(quizId);
                int notAnsweredQuestions = databaseController.getNumberOfQuestionsWithoutAnswer(quizId);
                progressBar.setMax(allQuestions);
                progressBar.setProgress(allQuestions - notAnsweredQuestions);
            }
        });
    }

    private long getId() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        return bundle.getLong("id");
    }

    private void preventDoubleClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
    }

}
