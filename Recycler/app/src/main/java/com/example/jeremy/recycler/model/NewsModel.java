package com.example.jeremy.recycler.model;

/**
 * Created by Jeremy on 03/02/16.
 */
public class NewsModel {

    private String text;
    private String imageUrl;
    private String date;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
