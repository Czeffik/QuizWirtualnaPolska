package com.trzewik.quizwirtualnapolska.logic;


import com.trzewik.quizwirtualnapolska.db.entity.Rate;

import java.util.List;

public class RateMessageProvider {
    private DatabaseController databaseController = new DatabaseController();

    public String getRateMessage(long quizId, int result) {
        List<Rate> rates = databaseController.getAllRates(quizId);
        String message = null;
        for (Rate rate : rates) {
            if (result >= rate.getValueFrom() && result <= rate.getValueTo()) {
                message = rate.getMessage();
            }
        }
        return message;
    }
}
