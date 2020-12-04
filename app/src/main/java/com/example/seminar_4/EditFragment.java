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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.example.seminar_4.CashActivity.TRANSACTIONS;

public class EditFragment extends Fragment {
    protected static final String TRANSACTIONSFORCHART = "transactionsForChart";
    private ArrayList<Cash> listOFTransactions = new ArrayList<Cash>();
    private EditText text;
    private Button btnBack;


    LineChart lineChart;
    LineData lineData;
    ArrayList<Entry> entryList = new ArrayList<>();

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
        if (getArguments() != null) {
            this.listOFTransactions = getArguments().getParcelableArrayList(TRANSACTIONS);
            if(listOFTransactions!=null) {
                lineChart = view.findViewById(R.id.lineChart);

                for (int i = 0; i < listOFTransactions.size(); i++) {
                    float x = listOFTransactions.get(i).getCashAmount();
                    float y = listOFTransactions.get(i).getRating();
                    entryList.add(new Entry(x, y));
                }
                LineDataSet lineDataSet = new LineDataSet(entryList, "transactions");
                lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                lineDataSet.setFillAlpha(110);
                lineData = new LineData(lineDataSet);
                lineChart.setData(lineData);
                lineChart.setVisibleXRangeMaximum(10);
                lineChart.invalidate();
            }
            else
            {
                lineChart = view.findViewById(R.id.lineChart);
                Cash cash = (Cash) getArguments().getSerializable("cash");
                float x = cash.getCashAmount();
                float y = cash.getRating();
                entryList.add(new Entry(x, y));
                LineDataSet lineDataSet = new LineDataSet(entryList, "transactions");
                lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                lineDataSet.setFillAlpha(110);
                lineData = new LineData(lineDataSet);
                lineChart.setData(lineData);
                lineChart.setVisibleXRangeMaximum(10);
                lineChart.invalidate();
            }
        }

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
            listOFTransactions = getArguments().getParcelableArrayList(TRANSACTIONS);
            if(getContext() != null)
            {
                text = (EditText) view.findViewById(R.id.cashToString);
                text.setText(cash.toString());
            }
        }
    }
}