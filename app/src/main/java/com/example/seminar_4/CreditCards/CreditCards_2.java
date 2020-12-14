package com.example.seminar_4.CreditCards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s4.BudgetApp.CreditEntry;
import com.example.seminar_4.CreditCards.RegistrationList;
import com.example.seminar_4.R;


import java.util.ArrayList;

public class CreditCards_2 extends AppCompatActivity {

    private TextView seekBar;
    private TextView amount;
    private TextView typeSum;
    private TextView category;
    private TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_cards_2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String param1 = extras.getString("param1");
        String param2 = extras.getString("param2");
        String param3 = extras.getString("param3");
        String param4 = extras.getString("param4");
        ArrayList<String> param5 = extras.getStringArrayList("param5");
        String param9 = extras.getString("param9");

        Toast.makeText(this, param1, Toast.LENGTH_SHORT).show();

        seekBar = (TextView)findViewById(R.id.tv_limitCorrect);
        seekBar.setText(param2);


        amount = (TextView)findViewById(R.id.tv_amountCorrect);
        amount.setText(param3);

        typeSum = (TextView)findViewById(R.id.tv_typeSumCorrect);
        typeSum.setText(param4);

        category = (TextView)findViewById(R.id.tv_categoryCorrect);

        String value5 = "";
        for(int i=0;i<param5.size();i++)
            if(param5.get(i) != null)
                value5 += param5.get(i) + "\n";

        category.setText(value5);

        date = (TextView)findViewById(R.id.tv_dateCorrect);
        date.setText(param9);
        }


    public void returnToMainActivity(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("param2", "Data coming from the second activity!");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void CallListActivity(View view)
    {

        Intent intent = new Intent(this, RegistrationList.class);
        //Intent intent = new Intent(this, ListFragment.class);

        Bundle extras = new Bundle();
        //bundle.putString("p1", "Your activity was added to the list");
        CreditEntry creditEntry = CreateEntry();

        extras.putSerializable("p200", creditEntry);
        //Intent intent = new Intent();
        intent.putExtras(extras);
        // AddBundleForFragment();


        //Intent intent = new Intent(this, ListFragment.class);
     //   ListFragment fragment = new ListFragment();
       // Bundle bundle = new Bundle();
       // bundle.putString("p100", "Your activity was added to the list");

        //CreditEntry creditEntry = CreateEntry();
        //bundle.putSerializable("p200", creditEntry);


      //  fragment.setArguments(bundle);

//        ListFragment fragment = new ListFragment();
//        fragment.setArguments(bundle);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, fragment);
//        fragmentTransaction.commit();


        //intent.putExtras(bundle);
        //startActivityForResult(intent, 300);
        startActivity(intent);
    }




    public CreditEntry CreateEntry(){

        CreditEntry creditEntry = new CreditEntry(seekBar.getText().toString(), amount.getText().toString(),
                typeSum.getText().toString(), category.getText().toString(), date.getText().toString());
        return creditEntry;
    }


}