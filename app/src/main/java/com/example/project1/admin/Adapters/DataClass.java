package com.example.project1.admin.Adapters;

public class DataClass {
    private String imageURL;
    private String caption;
    private String dateTime; // Added date and time field

    public DataClass() {
        // Default constructor required for Firebase
    }

    public DataClass(String imageURL, String caption, String dateTime) {
        this.imageURL = imageURL;
        this.caption = caption;
        this.dateTime = dateTime;

    }

    public String getImageUrl() {
        return imageURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
