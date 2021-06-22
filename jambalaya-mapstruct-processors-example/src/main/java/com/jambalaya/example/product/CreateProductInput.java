package com.jambalaya.example.product;

public class CreateProductInput implements java.io.Serializable {

    private ProductDetailsInput details;

    public CreateProductInput() {
    }

    public CreateProductInput(ProductDetailsInput details) {
        this.details = details;
    }

    public ProductDetailsInput getDetails() {
        return details;
    }

    public void setDetails(ProductDetailsInput details) {
        this.details = details;
    }

}
