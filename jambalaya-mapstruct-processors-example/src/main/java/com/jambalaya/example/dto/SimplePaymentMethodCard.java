/*
 * Copyright 2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
