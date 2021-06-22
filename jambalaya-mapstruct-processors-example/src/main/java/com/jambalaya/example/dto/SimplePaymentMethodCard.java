package com.jambalaya.example.dto;

import java.util.List;

/**
 * @author Alexey Zhokhov
 */
public class SimplePaymentMethodCard {

    private final String number;
    private final int expirationYear;
    private final int expirationMonth;
    private final String cardHolderName;
    private final List<SimpleAccountUpdaterLog> updaterLog;

    public SimplePaymentMethodCard(String number, int expirationYear, int expirationMonth, String cardHolderName,
                                   List<SimpleAccountUpdaterLog> updaterLog) {
        this.number = number;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.cardHolderName = cardHolderName;
        this.updaterLog = updaterLog;
    }

    public String getNumber() {
        return number;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public List<SimpleAccountUpdaterLog> getUpdaterLog() {
        return updaterLog;
    }

}
