package com.jambalaya.example.product;

import java.util.List;

public class ProductDetailsInput implements java.io.Serializable {

    private List<ProductMediaInput> desktopMedia;
    private List<ProductMediaInput> mobileMedia;
    private String listSource;

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

    public String getListSource() {
        return listSource;
    }

    public void setListSource(String listSource) {
        this.listSource = listSource;
    }
}
