package com.example.s4.BudgetApp;

import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CreditEntry implements Serializable {
   private String limit, amount, type, category, date;

    public CreditEntry(String limit, String amount, String type, String category, String date)
    {
        this.limit = limit;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public String getLimit(){ return limit;}
    public void setLimit(String limit){this.limit = limit;}

    public String getAmount(){return amount;}
    public void setAmount(String amount){this.amount = amount;}

    public String getType(){return type;}
    public void setTpe(String amount){this.amount = amount;}

    public String getCategory(){return category;}
    public void setCategory(String amount){this.amount = amount;}

    public String getDate(){return date;}
    public void setDate(String amount){this.amount = amount;}


    @Override
    public String toString() {
        return "Limit: " + this.limit + ", Amount: " + this.amount + ", Type: " + this.type +
                ", Category: " + this.category + ", Date: " + this.date;
    }
}
