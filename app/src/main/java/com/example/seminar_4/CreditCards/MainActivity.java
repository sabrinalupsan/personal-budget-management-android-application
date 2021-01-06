package com.example.seminar_4.CreditCards;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seminar_4.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView date_text;
    private SeekBar seekbar;
    private TextView tv_SeekBarValue;
    private TextView tv;
    private String sIBAN, sLimit;


//    @Override
//    protected void onCreate(final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_credit_cards);
//
//    }
//
//    @Override
//    protected void onStart()
//    {
//        // TODO Auto-generated method stub
//        super.onStart();
//        Progress(findViewById(R.id.tv_seekBarValue));
//    }
//
//
//    @Override
//    protected void onResume()
//    {
//        // TODO Auto-generated method stub
//        super.onResume();
//        Progress(findViewById(R.id.tv_seekBarValue));
//    }
//
//
//
//    public void Progress(View view)
//    {
//        seekbar = (SeekBar)findViewById(R.id.sb_limit);
//        tv_SeekBarValue = findViewById(R.id.tv_seekBarValue);
//        seekbar.setMax(Integer.parseInt(sLimit));
//        tv_SeekBarValue.setText(seekbar.getProgress() + "/" + seekbar.getMax());
//
//        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                tv_SeekBarValue.setText(seekbar.getProgress() + "/" + sLimit);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                tv_SeekBarValue.setText(seekbar.getProgress() + "/" + sLimit);
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                tv_SeekBarValue.setText(seekbar.getProgress() + "/" + sLimit);
//
//            }
//        });
//    }

    public void callSecondActivity(View view)
    {
        Intent intent = new Intent(this, CreditCards_2.class);
        Bundle bundle = new Bundle();

        // startActivity(intent);
        bundle.putString("param2", sIBAN);

        TextView value2 = (TextView)findViewById(R.id.et_actualAmount);
        bundle.putString("param3", value2.getText().toString());

        RadioGroup radiogroup = findViewById(R.id.rg_typeSum);
        String selectedButton = ((RadioButton)findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString();
        bundle.putString("param4", selectedButton);

        ArrayList<String> array = new ArrayList<String>();
        if(((CheckBox)findViewById(R.id.checkBox)).isChecked() == true)
            array.add(((CheckBox)findViewById(R.id.checkBox)).getText().toString());
        if(((CheckBox)findViewById(R.id.checkBox2)).isChecked() == true)
            array.add(((CheckBox)findViewById(R.id.checkBox2)).getText().toString());
        if (((CheckBox)findViewById(R.id.checkBox3)).isChecked() == true)
            array.add(((CheckBox)findViewById(R.id.checkBox3)).getText().toString());

        bundle.putStringArrayList("param5", array);

        date_text = (TextView)findViewById(R.id.tv_DatePlace);
        bundle.putString("param9", date_text.getText().toString());

        intent.putExtras(bundle);
        //intent.addFlags(intent.FLAG_ACTIVITY_FORWARD_RESULT);

        startActivityForResult(intent, 100);
    }



    public void CallCalendarActivity(View view)
    {
        Intent intent = new Intent(this, calendar_activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("param7", "Data received from MainActivity!");

        intent.putExtras(bundle);
        startActivityForResult(intent, 200);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100)
        {
            if(data != null)
            {
                Bundle extras = data.getExtras();
                String param2 = extras.getString("param2");
                Toast.makeText(this, param2, Toast.LENGTH_LONG).show();

            }
        }
        else
        if(requestCode == 200)
        {
            if(data != null)
            {
                Bundle extras = data.getExtras();
                String param8 = extras.getString("param8");
                tv = (TextView)findViewById(R.id.tv_DatePlace);
                tv.setText(param8);
            }
        }
        else if(requestCode == 1011 || requestCode == 1012) {
            if (data != null) {
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();

                sIBAN = extras.getString("paramIBAN");
                sLimit = extras.getString("paramLimit");
            }
        }
    }


}