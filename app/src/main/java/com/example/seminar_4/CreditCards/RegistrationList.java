package com.example.seminar_4.CreditCards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.seminar_4.R;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class RegistrationList extends AppCompatActivity {
    private static final String TAG = RegistrationList.class.getSimpleName();
    private TextView list;
    private String iban;
   // private Button btn_drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);
        list = findViewById(R.id.tv_transactions);

//        btn_drop = findViewById(R.id.btn_dropTransaction);
//        btn_drop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final AccountDBHelper accountDBHelper = new AccountDBHelper(getApplicationContext());
//                accountDBHelper.dropTransaction();
//            }
//        });
//        listview = findViewById(R.id.lv_transactions);
//
//        spn = findViewById(R.id.spinner_categories);
//        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.choose_category,
//                android.R.layout.simple_spinner_dropdown_item);
//        spn.setAdapter(adapter);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        iban = extras.getString("paramIBAN");

        AccountDBHelper accountDBHelper = new AccountDBHelper(this);

        for(int i=0;i<TransactionTable.TABLE_TODO.length();i++) {
            Cursor cursor = accountDBHelper.getDataCursorTransaction(i);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();

                String iban = cursor.getString(cursor.getColumnIndex("IBAN"));
                String amount = cursor.getString(cursor.getColumnIndex("ActualAmount"));
                String type = cursor.getString(cursor.getColumnIndex("SumType"));
                String category = cursor.getString(cursor.getColumnIndex("Category"));
                String date = cursor.getString(cursor.getColumnIndex("Date"));
                String limit = cursor.getString(cursor.getColumnIndex("AmountLimit"));
                String bank = cursor.getString(cursor.getColumnIndex("Bank"));
                cursor.close();
                CreditEntry entry = new CreditEntry(iban, amount, type, category, date);

                list.setText(list.getText().toString() + "\n" + entry.toString() + " / " + limit + ", Bank: " + bank);
            }
        }


    }

    public void returnToMainActivityFromList(View view) {
        Intent intent = new Intent(this, BankDetails.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }

    public void SaveFile(View view){
        writeToFile(list.getText().toString());
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("Transactions.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }

    }


}