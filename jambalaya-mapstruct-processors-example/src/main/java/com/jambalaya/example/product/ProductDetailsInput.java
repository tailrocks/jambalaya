package com.jambalaya.example.product;

import java.util.List;

public class ProductDetailsInput implements java.io.Serializable {

    private List<ProductMediaInput> desktopMedia;
    private List<ProductMediaInput> mobileMedia;
    private List<ProductMediaInput> tests;

    public List<ProductMediaInput> getDesktopMedia() {
        return desktopMedia;
    }

    public void setDesktopMedia(List<ProductMediaInput> desktopMedia) {
        this.desktopMedia = desktopMedia;
    }

    public List<ProductMediaInput> getMobileMedia() {
        return mobileMedia;
    }

    public void setMobileMedia(List<ProductMediaInput> mobileMedia) {
        this.mobileMedia = mobileMedia;
    }

    public List<ProductMediaInput> getTests() {
        return tests;
    }

    public void setTests(List<ProductMediaInput> tests) {
        this.tests = tests;
    }

}
