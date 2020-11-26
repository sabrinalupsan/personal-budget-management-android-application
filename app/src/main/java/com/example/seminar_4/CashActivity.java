package com.example.seminar_4;
import com.example.seminar_4.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class CashActivity extends AppCompatActivity {
    protected static final String TRANSACTIONS = "transactions";

    private TextView info;
    private RadioButton btnYES;
    private RadioButton btnNO;
    private Button btnDone;
    private ListFragment fragment;
    private ListView lvTransactions;
    private ArrayList<Cash> transactions = new ArrayList<>();
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        info = findViewById(R.id.infoID);
        btnYES = findViewById(R.id.btnSave);
        btnNO = findViewById(R.id.btnCancel);
        btnDone = findViewById(R.id.btnOK);
        lvTransactions = findViewById(R.id.lvFragment);
        intent = getIntent();
        bundle = intent.getExtras();

        transactions = bundle.getParcelableArrayList("totalTransactions");

        Cash cash = (Cash)bundle.getParcelable("value");
        info.setText(cash.toString());
        transactions.add(cash);
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = ListFragment.newInstance(transactions);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, intent);
                finish();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2, intent);
                bundle.putParcelableArrayList(TRANSACTIONS, transactions);
                intent.putExtras(bundle);
                finish();
            }
        });    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 3)
        {
            Bundle bundle = data.getExtras();
            Cash cash =(Cash) bundle.getSerializable("selectedCash");
            ArrayList<Cash> transactions = bundle.getParcelableArrayList(TRANSACTIONS);
            EditFragment fragment;
            fragment = EditFragment.newInstance(cash, transactions);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.commit();
        }
        }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        bundle.putParcelableArrayList(TRANSACTIONS, transactions);
    }
}