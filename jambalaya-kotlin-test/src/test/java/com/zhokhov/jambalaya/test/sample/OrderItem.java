package com.zhokhov.jambalaya.test.sample;

public class OrderItem {

    private final Long id;
    private final String sku;
    private final String productName;

    public OrderItem(Long id, String sku, String productName) {
        this.id = id;
        this.sku = sku;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

}
