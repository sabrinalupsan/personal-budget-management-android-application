package com.example.seminar_4.Wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seminar_4.R;

public class IsCorrect extends AppCompatActivity {
    TextView questionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_correct);

        Intent intent=getIntent();
        Bundle extras= intent.getExtras();
        String name=extras.getString("name");
        float rating= extras.getFloat ("rating");
        String cost= extras.getString("cost");

        questionTv=findViewById(R.id.questionTv);
        questionTv.append("Add wish "+"'"+name+"'"+" with the importance "
                +rating+"/5 "+"and cost "+cost+"$?");
    }

    public void cancelWish(View view){
        Intent intent= new Intent();
        intent.putExtra("add","Canceled");

        setResult(RESULT_OK,intent);
        finish();
    }

    public void addWish(View view){
        Intent intent= new Intent();
        intent.putExtra("add","Added");

        setResult(RESULT_OK,intent);
        finish();
    }

}

