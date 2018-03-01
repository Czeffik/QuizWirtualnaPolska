package com.trzewik.quizwirtualnapolska.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.adapters.QuestionAnswerListAdapter;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;

import java.util.List;

public class QuizQuestionActivity extends AppCompatActivity {
    private ListView listView;
    private TextView questionView;
    private TextView titleView;
    private DatabaseController databaseController = new DatabaseController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        listView = findViewById(R.id.list);
        questionView = findViewById(R.id.text);
        titleView = findViewById(R.id.title);

        populateView();
    }

    private void populateView(){
        long id = getId();
        Quiz quiz = databaseController.getQuizById(id);
        populateTitle(quiz);
        List<QuizQuestion> quizQuestions = databaseController.getQuizQuestionsByQuizId(id);
        QuizQuestion quizQuestion = quizQuestions.get(0);
        populateQuestion(quizQuestion);
        id = quizQuestion.getId();
        List<QuizAnswer> quizAnswers = databaseController.getQuizAnswerById(id);
        populateAnswers(quizAnswers);

    }

    private void populateTitle(final Quiz quiz){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleView.setText(quiz.getTitle());
            }
        });
    }

    private void populateQuestion(final QuizQuestion quizQuestion){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                questionView.setText(quizQuestion.getText());
            }
        });
    }

    private void populateAnswers(final List<QuizAnswer> quizAnswers) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter( new QuestionAnswerListAdapter(quizAnswers, getApplicationContext()));
            }
        });
    }

    private long getId() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        return bundle.getLong("id");
    }

}
