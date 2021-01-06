package com.example.seminar_4.CreditCards;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.seminar_4.R;

import java.util.ArrayList;


public class RegistrationList extends AppCompatActivity {
    private static final String TAG = RegistrationList.class.getSimpleName();
    private Spinner spn;
    private ListView listview;
    public ArrayList<CreditEntry> creditEntries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);

        listview = findViewById(R.id.lv_transactions);

        spn = findViewById(R.id.spinner_categories);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.choose_category,
                android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CreditEntry creditEntry;
        creditEntry = (CreditEntry) extras.getSerializable("p200");


        creditEntries.add(creditEntry);

         ArrayAdapter<CreditEntry> adapterList = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                creditEntries);
        listview.setAdapter(adapterList);
    }

    public void returnToMainActivityFromList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 300);
    }

}