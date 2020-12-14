package com.example.seminar_4.CreditCards;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.GridView;
import com.example.s4.BudgetApp.CreditEntry;
import com.example.seminar_4.Cash.DownloadContent;
import com.example.seminar_4.EditDatabase;
import com.example.seminar_4.R;
import com.example.seminar_4.RepoDatabase;
import com.example.seminar_4.User;
import com.example.seminar_4.UserDataSource;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistrationList extends AppCompatActivity {
    private static final String TAG = RegistrationList.class.getSimpleName();
    GridView gv;
    public CreditEntry p200;
    ArrayAdapter adapter2;
    public ArrayList<CreditEntry> creditEntries = new ArrayList<>();
    RepoDatabase repoDatabase;
    private Spinner spn;
    JSONArray jsonArray = new JSONArray();
    CreditEntry c1,c2,c3;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list);

        DownloadContent.handler = new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(TAG, "----------image received from thread------------");
                Bundle data = msg.getData();
                Bitmap image = data.getParcelable("image");

            }
        };

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
//        mEditor.putString("key", "bogdan");
//        mEditor.commit();
//
//        String name = mPreferences.getString("key", "All");




        spn = findViewById(R.id.spinner_categories);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.choose_category,
                android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);




        gv = (GridView) findViewById(R.id.gv_information);
        AddEntry();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        try{
        p200 = (CreditEntry) extras.getSerializable("p200");} catch (Exception e) {
            e.printStackTrace();
        }
        // CreditEntry p200 = (CreditEntry) extras.getSerializable("p200");
         c1 = new CreditEntry("100/150", "30", "Expense", "Food", "26-11-2020");
         c2 = new CreditEntry("130/150", "75", "Income", "Services", "27-11-2020");
         c3 = new CreditEntry("80/150", "50", "Expense", "Clothing", "30-11-2020" );
        creditEntries.add(c1);
        creditEntries.add(c2);
        creditEntries.add(c3);
        if (p200 != null) {
            Toast.makeText(getApplicationContext(),
                    p200.toString(),
                    Toast.LENGTH_SHORT).show();
            creditEntries.add(p200);
            adapter2 = (ArrayAdapter) gv.getAdapter();
            adapter2.notifyDataSetChanged();
        }

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spn.getSelectedItem().toString();
                if(text != "All")
                {
                    adapter2.getFilter().filter(text);
                    adapter2.notifyDataSetChanged();
                    Log.d(TAG, "-----------");
                    Log.d(TAG, text);
                }
                else
                    AddEntry();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               //AddEntry();
            }
        });

            for(int i=0;i<creditEntries.size();i++)
            {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(creditEntries.get(i).toString(), i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }


            Log.d(TAG, "---------------JSON Object-------------------------");
            Log.d(TAG, jsonArray.toString());



        repoDatabase = RepoDatabase.getInstance(this);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               callEditing(view, position);

            }
        });
    }



    private void checkSharedPreferences(){

    }



    public void callEditing(View view, int position)
    {
        Intent intent = new Intent(this, EditDatabase.class);
        Bundle bundle = new Bundle();

        CreditEntry selItem = (CreditEntry)gv.getItemAtPosition(position);

        bundle.putString("parameter1", String.valueOf(position));
        bundle.putString("parameter2", selItem.getLimit());
        bundle.putString("parameter3", selItem.getAmount());
        bundle.putString("parameter4", selItem.getType());
        bundle.putString("parameter5", selItem.getCategory());
        bundle.putString("parameter6", selItem.getDate());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void save(View view)
    {
            final UserDataSource userDataSource = new UserDataSource(repoDatabase.userDao());

            for(int i=0;i<creditEntries.size();i++) {
                final User user = new User(String.valueOf(i), creditEntries.get(i).getLimit(), creditEntries.get(i).getAmount(),
                        creditEntries.get(i).getType(), creditEntries.get(i).getCategory(), creditEntries.get(i).getDate());
                userDataSource.insertUser(user);
            }
    }




    public void get(View view) {
        final UserDataSource userDataSource = new UserDataSource(repoDatabase.userDao());
        Log.d(TAG, "----------------------Elements in the db---------------------");
        try {
            for (int i = 0; i < creditEntries.size(); i++) {

                    User user = userDataSource.getUser(String.valueOf(i));
                if (user != null) {
                    Log.d(TAG, user.getId() + " " + user.getMLimit() + " " + user.getMAmount() + " " + user.getMType() + " "
                            + user.getMCategory() +  " " + user.getMDate());
                }
            }
        }catch(Exception ex) {
            Log.e(TAG, "NO DATA IN THE DB");
            ex.printStackTrace();
        }
    }



    public void deleteAll(View view)
    {
        final UserDataSource userDataSource = new UserDataSource(repoDatabase.userDao());
        userDataSource.deleteAllUsers();
    }



    private void AddEntry() {
        ArrayAdapter<CreditEntry> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                creditEntries);
        gv.setAdapter(adapter);
    }



    public void returnToMainActivityFromList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 300);
    }



}