package com.example.myapp.network.image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeImage {
    @SerializedName("image")
    @Expose
    private String image;


    public ChangeImage() {
    }

    public ChangeImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
