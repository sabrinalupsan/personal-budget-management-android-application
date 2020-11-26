package com.example.seminar_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EditFragment extends Fragment {
    protected static final String TRANSACTIONSFORCHART = "transactionsForChart";
    private ArrayList<Cash> listOFTransactions = new ArrayList<Cash>();
    private EditText text;
    private Button btnBack;
    public EditFragment() {
    }

    public static EditFragment newInstance(Cash cash, ArrayList<Cash> transactions) {
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cash", cash);
        bundle.putParcelableArrayList(TRANSACTIONSFORCHART, transactions);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        initComponents(view);
        btnBack = view.findViewById(R.id.BackBtn);
        btnBack.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }



    private void initComponents(View view)
    {
        if (getArguments() != null)
        {
            Cash cash = (Cash)getArguments().getSerializable("cash");
            listOFTransactions = getArguments().getParcelableArrayList(CashActivity.TRANSACTIONS);
            if(getContext() != null)
            {
                text = (EditText) view.findViewById(R.id.cashToString);
                text.setText(cash.toString());
            }
        }
    }
}