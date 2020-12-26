package com.example.seminar_4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Wish implements Serializable { //Serializable
    private  String name;
    private  float importance;
    private Date deadline;
    private float cost;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    private String alert;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;
    private long id;
    private static long noWishes=0;
    private String url;


    public Wish(){}

    public Wish(String name, float importance, Date date, float cost, String alert, String category){
        this.name=name;
        this.importance=importance;
        this.deadline=date;
        this.cost=cost;
        this.alert = alert;
        this.category = category;
//        this.id=noWishes;
//        noWishes++;
    }


    public Wish(int id,String url,String name, float importance, Date date, float cost, String alert, String category){
        this.name=name;
        this.importance=importance;
        this.deadline=date;
        this.cost=cost;
        this.alert = alert;
        this.category = category;
        this.id=id;
        this.url=url;
//        noWishes++;

    }



    public long getId() {
        return id;
    }


//    protected Wish(Parcel in) {
//        name = in.readString();
//        importance = in.readFloat();
//        cost = in.readFloat();
//        SimpleDateFormat formater= new SimpleDateFormat("dd/mm/yyyy", Locale.US);
//
//        String date=in.readString();
//        try {
//            deadline=formater.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }        alert=in.readString();
//        category=in.readString();
//    }
//
//    public static final Creator<Wish> CREATOR = new Creator<Wish>() {
//        @Override
//        public Wish createFromParcel(Parcel in) {
//            return new Wish(in);
//        }
//
//        @Override
//        public Wish[] newArray(int size) {
//            return new Wish[size];
//        }
//    };
//

        public float getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return
                 name + '\n' +
                "importance: " + importance +"/5"+ '\n'+
                "deadline: " + deadline +'\n'+
                "cost:" + cost +"$"+'\n'+
                        "category: "+category+
                        "\nAlerts: "+alert;

    }


    public String getAtributes() {
        return
                "importance: " + importance +"/5"+ '\n'+
                "deadline: " + deadline +'\n'+
                "cost:" + cost +"$"+'\n'+
                "category: "+category+
                "\nAlerts: "+alert;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        String date=deadline.toString();
////        SimpleDateFormat formater= new SimpleDateFormat("dd/mm/yyyy", Locale.US);
////        try {
////            date=formater.parse(deadline.getText().toString());
//////            wish.setDeadline(date);
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
//        dest.writeString(name);
//        dest.writeFloat(importance);
//        dest.writeFloat(cost);
//        dest.writeSerializable(date);
//        dest.writeString(alert);
//        dest.writeString(category);
//    }
//
//    public String getAlert() {
//        return alert;
//    }
//
//    public void setAlert(String alert) {
//        this.alert = alert;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
}
