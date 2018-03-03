package com.trzewik.quizwirtualnapolska.logic;


public class DataRetriever {
    private DataLoader dataLoader = new DataLoader();
    private String appDirectory;
    private int startIndex;
    private int maxResult;
    private int availableCores;


    public DataRetriever(String appDirectory, int startIndex, int maxResult) {
        this.startIndex = startIndex;
        this.maxResult = maxResult;
        this.appDirectory = appDirectory;
        availableCores = Runtime.getRuntime().availableProcessors();
    }

    public void retrieveData() {
        while (maxResult > 0) {
            dataLoader.retrieveData(appDirectory, startIndex, availableCores);
            startIndex = startIndex + availableCores;
            maxResult = maxResult - availableCores;
        }
    }
}
