package com.zhokhov.jambalaya.test.sample;

import java.util.List;

public class Order {

    private final Long id;
    private final String orderNumber;
    private final List<OrderItem> items;

    public Order(Long id, String orderNumber, List<OrderItem> orderitems) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.items = orderitems;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public List<OrderItem> getItems() {
        return items;
    }

}
