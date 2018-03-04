package com.trzewik.quizwirtualnapolska.logic;


public class DataGainer {
    private DatabaseController databaseController = new DatabaseController();
    private DataLoader dataLoader = new DataLoader();
    private int availableCores;


    public DataGainer() {
        availableCores = Runtime.getRuntime().availableProcessors();
    }

    public void retrieveData(String appDirectory, int startIndex, int maxResult) {
        while (maxResult > 0) {
            if (maxResult < availableCores) {
                dataLoader.fetchData(appDirectory, startIndex, maxResult);
                maxResult = 0;
            } else {
                dataLoader.fetchData(appDirectory, startIndex, availableCores);
                startIndex = startIndex + availableCores;
                maxResult = maxResult - availableCores;
            }
        }
    }

    public void fetchNewQuizzes(String appDirectory, int maxResult) {
        int startIndex = databaseController.getNumberOfQuizzes();
        while (maxResult > 0) {
            if (maxResult < availableCores) {
                dataLoader.fetchAdditionalQuizzes(appDirectory, startIndex, maxResult);
                maxResult = 0;
            } else {
                dataLoader.fetchAdditionalQuizzes(appDirectory, startIndex, availableCores);
                startIndex = startIndex + availableCores;
                maxResult = maxResult - availableCores;
            }
        }
    }
}
