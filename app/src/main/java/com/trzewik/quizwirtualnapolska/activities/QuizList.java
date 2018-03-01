package com.trzewik.quizwirtualnapolska.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.trzewik.quizwirtualnapolska.App;
import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.adapters.QuizAdapter;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.logic.DataLoader;
import com.trzewik.quizwirtualnapolska.logic.DatabaseController;

import java.io.IOException;
import java.util.List;

public class QuizList extends AppCompatActivity {
    private static int START_INDEX = 0;
    private static int MAX_RESULT = 4;

    private ListView listView;
    private DataLoader dataLoader = new DataLoader();
    private DatabaseController databaseController = new DatabaseController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        listView = (ListView) findViewById(R.id.list);
        insertAndDisplayData(START_INDEX, MAX_RESULT);
    }

    private void insertAndDisplayData(final int startIndex, final int maxResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Quiz> quizzes = databaseController.getQuizListFromDb();
                if (App.get().isForceUpdate() || quizzes.isEmpty()) {
                    try {
                        new DataLoader().retrieveQuizList(getApplicationInfo().dataDir, startIndex, maxResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                QuizAdapter quizAdapter = new QuizAdapter(databaseController.getQuizListFromDb(), getApplicationContext());
                listView.setAdapter(quizAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        List<Quiz> quizzes = databaseController.getQuizListFromDb();
                        Quiz quiz = quizzes.get(position);
                        Intent intent = new Intent(QuizList.this, QuizQuestion.class);
                        intent.putExtras(setBundle(quiz.getId()));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private Bundle setBundle(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        return bundle;
    }

}
