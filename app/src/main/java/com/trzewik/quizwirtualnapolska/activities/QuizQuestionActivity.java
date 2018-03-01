package com.trzewik.quizwirtualnapolska.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.adapters.AnswerListAdapter;
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.db.entity.QuizQuestion;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.QuestionType;

import java.util.List;

public class QuizQuestionActivity extends AppCompatActivity {
    private ListView listView;
    private TextView questionView;
    private TextView titleView;
    private ImageView imageView;
    private DatabaseController databaseController = new DatabaseController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        listView = findViewById(R.id.list);
        questionView = findViewById(R.id.text);
        titleView = findViewById(R.id.title);
        imageView = findViewById(R.id.image);

        populateView();
    }

    private void populateView(){
        long id = getId();
        Quiz quiz = databaseController.getQuizById(id);

        List<QuizQuestion> quizQuestions = databaseController.getQuizQuestionsByQuizId(id);
        if (quizQuestions.size()>0) {
            QuizQuestion quizQuestion = quizQuestions.get(0);
            id = quizQuestion.getId();
            List<QuestionAnswer> questionAnswers = databaseController.getQuizAnswerByQuestionId(id);

            populateTitle(quiz);
            populateQuestion(quizQuestion);
            populateAnswers(questionAnswers);
        }

        else{
            Intent intent = new Intent(QuizQuestionActivity.this, QuizResultActivity.class);
            intent.putExtras(getIntent().getExtras());
            startActivity(intent);
            finish();
        }
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
                if (quizQuestion.getQuestionType().equals(QuestionType.QUESTION_TEXT_IMAGE.toString())){
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
                listView.setAdapter( new AnswerListAdapter(questionAnswers, getApplicationContext()));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        QuestionAnswer answer = questionAnswers.get(i);
                        databaseController.setQuestionAnswer(answer.getQuestionId(), answer.getId());
                        populateView();

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
