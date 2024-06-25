package com.example.project1.admin.Adapters;

public class Images {

    private String caption,imageUrl,dateTime;
    public Images() {

    }

    public Images(String caption, String imageUrl, String dateTime) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.dateTime = dateTime;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
