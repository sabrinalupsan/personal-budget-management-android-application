package com.example.seminar_4.CreditCards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seminar_4.Cash.CashDBHelper;
import com.example.seminar_4.MainMenuActivity;
import com.example.seminar_4.R;

public class BankDetails extends AppCompatActivity {

    private Spinner spn;
    private Button btn_cancel;
    private Button btn_ok;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        listView = findViewById(R.id.lv_banks);

        final AccountDBHelper accountDBHelper = new AccountDBHelper(this);


        final Cursor cursor = accountDBHelper.getDataCursorAccount();
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"_id", "Bank"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(cursorAdapter);



        spn = findViewById(R.id.spn_bankName);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.choose_bank,
                android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);


        btn_cancel = findViewById(R.id.btn_cancelBank);
        btn_ok = findViewById(R.id.btn_okBank);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });



        final Context context = this;
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AccountDBHelper accountDBHelper = new AccountDBHelper(context);
                EditText etIBAN = findViewById(R.id.et_IBAN);
                EditText etLimit = findViewById(R.id.et_limit);
                spn = findViewById(R.id.spn_bankName);

                accountDBHelper.insertSampleAccount(etIBAN.getText().toString(), spn.getSelectedItem().toString(), etLimit.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("paramIBAN", etIBAN.getText().toString());
                bundle.putString("paramLimit", etLimit.getText().toString());
                intent.putExtras(bundle);

                startActivityForResult(intent, 1011);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();

                TextView txtValue = view.findViewById(android.R.id.text1);
                String iban = txtValue.getText().toString();
                final AccountDBHelper accountDBHelper = new AccountDBHelper(context);
                String limit = accountDBHelper.getLimit(iban);


                bundle.putString("paramIBAN", iban);
                bundle.putString("paramLimit", limit);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1012);
            }
        });
    }

}