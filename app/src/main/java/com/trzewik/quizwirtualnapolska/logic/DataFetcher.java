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

    Map<Long, QuizDetails> getQuizDetails(List<Item> items) {
        Map<Long, QuizDetails> mapWithQuizDetails = new HashMap<>();
        for (Item item : items) {
            long itemId = item.getId();
            QuizDetails quizDetails = new ApiClient().getQuizDetails(itemId, 0);
            mapWithQuizDetails.put(itemId, quizDetails);
        }
        return mapWithQuizDetails;
    }

    List<Item> getItems(int startIndex, int maxResult) {
        Quizzes quizzes = new ApiClient().getQuizzes(startIndex, maxResult);
        return quizzes.getItems();
    }

    private void waitFor(Map<Long, QuizDetails> map, int maxResult) {
        if (map.keySet().size() != maxResult) {
            LOGGER.info("Waiting for downloading! Fetched: " + map.keySet().size() + " should be: " + maxResult);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitFor(map, maxResult);
        }
    }
}
