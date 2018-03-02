package com.trzewik.quizwirtualnapolska.logic;

import com.trzewik.quizwirtualnapolska.api.ApiClient;
import com.trzewik.quizwirtualnapolska.model.quizDetails.QuizDetails;
import com.trzewik.quizwirtualnapolska.model.quizList.Item;
import com.trzewik.quizwirtualnapolska.model.quizList.Quizzes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


class DataFetcher {
    final static private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ApiClient apiClient = new ApiClient();

    Map<Long, QuizDetails> getQuizDetails(List<Item> items) {
        final Map<Long, QuizDetails> quizDetails = new HashMap<>();
        for (final Item item : items) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long itemId = item.getId();
                    quizDetails.put(itemId, apiClient.getQuizDetails(itemId, 0));
                }
            }).start();
        }
        waitFor(quizDetails, items.size());
        return quizDetails;
    }

    List<Item> getItems(int startIndex, int maxResult) {
        Quizzes quizzes = apiClient.getQuizzes(startIndex, maxResult);
        return quizzes.getItems();
    }

    private void waitFor(Map<Long, QuizDetails> map, int maxResult) {
        if (map.keySet().size() != maxResult) {
            LOGGER.info("Waiting for downloading! Fetched: " + map.keySet().size());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitFor(map, maxResult);
        }
    }
}
