package com.trzewik.quizwirtualnapolska;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.logic.DataLoader;

import java.io.IOException;
import java.util.List;

public class QuizList extends AppCompatActivity {
    private static int START_INDEX = 0;
    private static int MAX_RESULT = 10;

    private ListView listView;
    private DataLoader dataLoader = new DataLoader();


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
                List<Quiz> quizzes = dataLoader.getQuizListFromDb(App.get());
                if (App.get().isForceUpdate() || quizzes.isEmpty()) {
                    try {
                        new DataLoader().retrieveQuizList(App.get(), startIndex, maxResult);
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
                QuizAdapter quizAdapter = new QuizAdapter(new DataLoader().getQuizListFromDb(App.get()), getApplicationContext());
                listView.setAdapter(quizAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        List<Quiz> quizzes = dataLoader.getQuizListFromDb(App.get());
                        Quiz quiz= quizzes.get(position);

                        Snackbar.make(view, quiz.getId()+"\n"+quiz.getTitle(), Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();
                    }
                });
            }
        });
    }

}
