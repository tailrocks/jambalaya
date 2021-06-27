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
