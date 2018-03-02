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
//TODO new Thread()
//    Map<Long, QuizDetails> getQuizDetails(List<Item> items) {
//        final Map<Long, QuizDetails> mapWithQuizDetails = new HashMap<>();
//        for (final Item item : items) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    long itemId = item.getId();
//                    QuizDetails quizDetails = new ApiClient().getQuizDetails(itemId, 0);
//                    mapWithQuizDetails.put(itemId, quizDetails);
//                }
//            }).start();
//        }
//        int numberOfItems = items.size();
//        waitFor(mapWithQuizDetails, numberOfItems);
//        return mapWithQuizDetails;
//    }

//TODO ExecuteService
//    Map<Long, QuizDetails> getQuizDetails(final List<Item> items) {
//        final Map<Long, QuizDetails> mapWithQuizDetails = new HashMap<>();
//
//        ExecutorService es = Executors.newCachedThreadPool();
//        int availableProcessors = Runtime.getRuntime().availableProcessors();
//        for(int i=0;i<availableProcessors;i++) {
//            es.execute(new Runnable() {
//                @Override
//                public void run() {
//                    for (Item item : items) {
//                        long itemId = item.getId();
//                        QuizDetails quizDetails = new ApiClient().getQuizDetails(itemId, 0);
//                        mapWithQuizDetails.put(itemId, quizDetails);
//                    }
//                }
//            });
//        }
//        es.shutdown();
//        int numberOfItems = items.size();
//        waitFor(mapWithQuizDetails, numberOfItems);
//        return mapWithQuizDetails;
//    }

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
