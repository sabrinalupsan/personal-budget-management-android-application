package com.example.seminar_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.seminar_4.CashActivity.TRANSACTIONS;


public class MainActivity extends AppCompatActivity {

    private Button OKbutton;
    private Button Cancelbutton;
    private static final int REQUEST_CODE = 1;
    private static final int resultCode = 0;
    private RadioGroup type;
    private SeekBar amount;
    private RatingBar rating;
    private EditText date;
    private Switch fromSavings;
    private ToggleButton planned;
    private ArrayList<Cash> transactions = new ArrayList<>();
    private Intent intent;
    private Bundle bundle = new Bundle();
    private Cash cash;
    private TextView tvSeekBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeAllComponents();
        OKbutton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), CashActivity.class);
               Cash cash = getInfoFromControls();
               bundle.putParcelable("value", cash);
               bundle.putParcelableArrayList("totalTransactions", transactions);
               intent.putExtras(bundle);
               startActivityForResult(intent, REQUEST_CODE);
            }
        });
        Cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, intent);
                finish();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.setText("");
            }
        });
        amount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSeekBar = findViewById(R.id.textView4);
                tvSeekBar.setText("Selected amount: "+String.valueOf(seekBar.getProgress()));
            }
        });
    }

    protected void InitializeAllComponents()
    {
        OKbutton = findViewById(R.id.OKbtn);
        Cancelbutton = findViewById(R.id.btnCancel);
        type = findViewById(R.id.radioGroup);
        amount = findViewById(R.id.seekBar2);
        rating = findViewById(R.id.ratingBar2);
        fromSavings = findViewById(R.id.switch1);
        date = findViewById(R.id.editTextDate2);
        planned = findViewById(R.id.checkBox);
        tvSeekBar = findViewById(R.id.textView4);
    }

    protected void clearAllComponents()
    {
        date.setText("");
        planned.setChecked(false);
        fromSavings.setChecked(false);
        rating.setRating(0);
        amount.setProgress(0);
        type.clearCheck();
        tvSeekBar.setText("Selected amount: ");
    }

    protected Cash getInfoFromControls()  {
        String theType=null;
        try{
            RadioButton btn = (RadioButton)findViewById(type.getCheckedRadioButtonId());
            theType = btn.getText().toString();
        }
        catch(NullPointerException ex) {
            //messagebox?
        }

        float theAmount = amount.getProgress();

        Date theDate = null;
        try {
            theDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(date.getText().toString());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if(theDate == null)
            {
                Date newDate = new Date();
                theDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(newDate.toString());
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


        float theRating = rating.getRating();

        boolean isPlanned;
        isPlanned = planned.isChecked();
        String plannedValue;
        if(isPlanned==true)
            plannedValue = "Yes";
        else
            plannedValue = "No";


        boolean isFromSavings;
        isFromSavings = fromSavings.isChecked();
        String fromSavings = null;
        if(isFromSavings==true)
            fromSavings = "Yes";
        else
            fromSavings = "No";
        return new Cash(theType, theAmount, theDate, theRating, plannedValue, fromSavings);
    }

    @Override
    protected void onActivityResult(int REQUEST_CODE, int resultCode, @Nullable Intent data) {
        super.onActivityResult(REQUEST_CODE, resultCode, data);
        if(resultCode == 2)
        {
            assert data != null;
            Bundle bundle = data.getExtras();
            ArrayList<Cash> transactions2 = bundle.getParcelableArrayList(TRANSACTIONS);
            for(int i=0; i<transactions2.size(); i++) {
                transactions.add(transactions2.get(i));
            }
            clearAllComponents();
        }
        else
        {
            if(resultCode == 1)
            {
                clearAllComponents();
                //ArrayList<Cash> newTransactions;
                //newTransactions = data.getParcelableArrayListExtra(CashActivity.TRANSACTIONS);
                //if(cash!=null)
                //{
                //   for(int i=0; i<newTransactions.size(); i++)
                //        transactions.add(newTransactions.get(i));
                //}
            }
        }
    }
}