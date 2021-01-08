package com.example.seminar_4.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private Bitmap image;
    private String category;

    public Image(Bitmap image, String category) {
        this.image = image;
        this.category = category;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
