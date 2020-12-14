package com.example.seminar_4.Wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.sem4.model.Wish;
import com.example.seminar_4.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddWishActivity extends AppCompatActivity {

    private EditText wishName;
    private RatingBar importance;
    private EditText deadline;
    private EditText cost;
    private Spinner spnAlerts;
    private Button btnAdd;
    private Intent intent;
    private RadioGroup rgCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);
        init();
        populateSpnAlerts();
        btnAdd.setOnClickListener(addWishClickEvent());
        intent=getIntent();

    }

    private View.OnClickListener addWishClickEvent(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wish wish= buildWishFromWidgets();
                intent.putExtra("wish",wish);
                setResult(RESULT_OK,intent);
                finish();
            }
        };
    }

    private Wish buildWishFromWidgets() {
        Date date=null;
        SimpleDateFormat formater= new SimpleDateFormat("dd/mm/yyyy", Locale.US);
        try {
             date=formater.parse(deadline.getText().toString());
//            wish.setDeadline(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String name=wishName.getText().toString();
        float wCost= Float.parseFloat(cost.getText().toString());
        float imp=importance.getRating();
        String category;
        if(rgCategory.getCheckedRadioButtonId()==R.id.rbExperience){
            category="Experience";
        }
        else category="Material Good";

        String alert=spnAlerts.getSelectedItem().toString();

        Wish wish= new Wish(name,imp,date,wCost,alert,category);

//        wish.setCost();
        return wish;
    }

    private void init(){
        wishName=findViewById(R.id.editWishName);
        importance=findViewById(R.id.ratingBar);
        deadline=findViewById(R.id.editDeadline);
        cost=findViewById(R.id.editCost);
        spnAlerts=findViewById(R.id.spnAlerts);
        btnAdd=findViewById(R.id.addBtn);
        rgCategory=findViewById(R.id.rgCategory);
    }

    private void populateSpnAlerts(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.alerts, android.R.layout.simple_spinner_dropdown_item );
        spnAlerts.setAdapter(adapter);
    }

}

