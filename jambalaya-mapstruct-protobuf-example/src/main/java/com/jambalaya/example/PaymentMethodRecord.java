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
package com.jambalaya.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentMethodRecord {

    private Long id;
    private LocalDateTime createdDate;
    private Long version;
    private PaymentMethodCardBrand cardBrand;
    private LocalDate cardExpirationDate;
    private String cardNumber;
    private String cardHolderName;

    public Long getId() {
        return id;
    }

    public PaymentMethodRecord setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public PaymentMethodRecord setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public PaymentMethodRecord setVersion(Long version) {
        this.version = version;
        return this;
    }

    public PaymentMethodCardBrand getCardBrand() {
        return cardBrand;
    }

    public PaymentMethodRecord setCardBrand(PaymentMethodCardBrand cardBrand) {
        this.cardBrand = cardBrand;
        return this;
    }

    public LocalDate getCardExpirationDate() {
        return cardExpirationDate;
    }

    public PaymentMethodRecord setCardExpirationDate(LocalDate cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public PaymentMethodRecord setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public PaymentMethodRecord setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

}
