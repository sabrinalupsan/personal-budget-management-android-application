package com.example.seminar_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;
//import static com.example.seminar_4.R.id.imageView;

public class MainMenuActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private Spinner spnMenu;
    private Button btnOK;
    private Button btnDelete;
    private GridView gridView;
    private ArrayList<Cash> transactions = new ArrayList<Cash>();
    private CheckBox box;
    ListView listView;
    //private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initializeComponents();

        makeTransactions();
        String s = makeJSONFile();

        DownloadAsync downloadAsync = new DownloadAsync();
        downloadAsync.execute("https://ptsv2.com/t/j5cq1-1606468043/post", s);

        //TypeAdapter adapter = new TypeAdapter(getApplicationContext(), transactions);
        //gridView.setAdapter(adapter);

        final CashDBHelper databaseHelper = new CashDBHelper(this);
        //databaseHelper.insertSample();

        final Cursor cursor = databaseHelper.getDataCursor();
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"_id", "type"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(cursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtValue = view.findViewById(android.R.id.text1);
                String value = txtValue.getText().toString();
                Toast.makeText(getApplicationContext(), "Clicked on item: " + value, Toast.LENGTH_SHORT).show();
                databaseHelper.deleteItemById(value);
                cursorAdapter.swapCursor(databaseHelper.getDataCursor());
                cursorAdapter.notifyDataSetChanged();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spnChoice;
                spnChoice = spnMenu.getSelectedItem().toString();
                if(spnChoice.compareTo("Cash")==0)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void makeTransactions()
    {
        Date date = new Date(2020,07,15);
        Date date2 = new Date(2019,10,8);
        Cash cash1 = new Cash("Expense", 101, date, 2, "True", "False", "https://www.enca.com/sites/default/files/LOTTO%20BALLS.jpg");
        Cash cash2 = new Cash("Income", 148, date2, 5, "False", "False", "https://insights.dice.com/wp-content/uploads/2018/01/Salary-Satisfaction-Dice.jpeg");
        Cash cash3 = new Cash("Other", 248, date2, 7, "False", "True", "https://insights.dice.com/wp-content/uploads/2018/01/Salary-Satisfaction-Dice.jpeg");
        transactions.add(cash2);
        transactions.add(cash1);
        transactions.add(cash3);
    }

    private void populateSpinner()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.add_to_menu,
                android.R.layout.simple_spinner_dropdown_item);
        spnMenu.setAdapter(adapter);
    }

    private void initializeComponents()
    {
        spnMenu = findViewById(R.id.spinner);
        btnOK = findViewById(R.id.button);
        gridView = findViewById(R.id.dgv);
        btnDelete = findViewById(R.id.button2);
        listView = findViewById(R.id.lv);
        //img = findViewById(R.id.imageView);
    }

    /*public void downloadImage(String s) {
        Log.d(TAG, "---------downloadImage method--------");
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://www.ci.brownsville.or.us/sites/default/files/styles/gallery500/public/imageattachments/utilities/page/541/light-water-phone-house-icons-by-david-castillo-dominici-freedigitalphotos_net_.jpg?itok=PAq7p0PS");
        urls.add("https://insights.dice.com/wp-content/uploads/2018/01/Salary-Satisfaction-Dice.jpeg");
        urls.add("https://www.enca.com/sites/default/files/LOTTO%20BALLS.jpg");
        DownloadImage imageTask = new DownloadImage(urls[0]);
        Thread downloadThread = new Thread(imageTask);
        downloadThread.start();
    }*/

    public File makeFILE()
    {
        File file = new File(getApplicationContext().getFilesDir(), "LUPSAN_SABRINA_FILE");
        JSONObject obj = new JSONObject();
        try {
            FileWriter fileWriter =new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            JSONObject jsonObject = new JSONObject();
            JSONObject joCollectionOfTransactions = new JSONObject();
            JSONArray jaTransactions = new JSONArray();

            for (int i = 0; i < transactions.size(); i++) {
                Cash c = transactions.get(i);
                JSONObject joBook = new JSONObject();
                joBook.put("type", c.getType());
                joBook.put("amount", c.getCashAmount());
                joBook.put("rating", c.getRating());
                joBook.put("date", c.getDate());
                jaTransactions.put(joBook);
                String userString = joBook.toString();
                bufferedWriter.write(userString);
                Log.d(TAG, "Content: "+userString);
            }
            Log.d(TAG, "Content: "+jaTransactions);
            for(int i=0; i<jaTransactions.length(); i++)
            {
                joCollectionOfTransactions.put("transaction"+(i+1), jaTransactions.getJSONObject(i));
            }
            jsonObject.put("LUPSAN_SABRINA", joCollectionOfTransactions);
            obj = jsonObject;
            Log.d(TAG, "Content: "+jsonObject);
            //joCollectionOfTransactions.put(jaTransactions)
            bufferedWriter.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public String makeJSONFile()
    {
        File file = new File(getApplicationContext().getFilesDir(), "LUPSAN_SABRINA_FILE");
        JSONObject obj = new JSONObject();
        try {
        FileWriter fileWriter =new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            JSONObject jsonObject = new JSONObject();
            JSONObject joCollectionOfTransactions = new JSONObject();
            JSONArray jaTransactions = new JSONArray();

            for (int i = 0; i < transactions.size(); i++) {
                Cash c = transactions.get(i);
                JSONObject joBook = new JSONObject();
                joBook.put("type", c.getType());
                joBook.put("amount", c.getCashAmount());
                joBook.put("rating", c.getRating());
                joBook.put("date", c.getDate());
                jaTransactions.put(joBook);
                String userString = joBook.toString();
                bufferedWriter.write(userString);
                Log.d(TAG, "Content: "+userString);
            }
            Log.d(TAG, "Content: "+jaTransactions);
            for(int i=0; i<jaTransactions.length(); i++)
            {
                joCollectionOfTransactions.put("transaction"+(i+1), jaTransactions.getJSONObject(i));
            }
            jsonObject.put("LUPSAN_SABRINA", joCollectionOfTransactions);
            obj = jsonObject;
            Log.d(TAG, "Content: "+jsonObject);
            //joCollectionOfTransactions.put(jaTransactions)
            bufferedWriter.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    public JSONObject makeJSONFile2()
    {
        File file = new File(getApplicationContext().getFilesDir(), "my_json");
        JSONObject obj = new JSONObject();
        try {
            FileWriter fileWriter =new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            JSONObject jsonObject = new JSONObject();
            JSONObject joCollectionOfTransactions = new JSONObject();
            JSONArray jaTransactions = new JSONArray();

            for (int i = 0; i < transactions.size(); i++) {
                Cash c = transactions.get(i);
                JSONObject joBook = new JSONObject();
                joBook.put("type", c.getType());
                joBook.put("amount", c.getCashAmount());
                joBook.put("rating", c.getRating());
                joBook.put("date", c.getDate());
                jaTransactions.put(joBook);
                String userString = joBook.toString();
                bufferedWriter.write(userString);
                Log.d(TAG, "Content: "+userString);
            }
            Log.d(TAG, "Content: "+jaTransactions);
            for(int i=0; i<jaTransactions.length(); i++)
            {
                joCollectionOfTransactions.put("transaction"+(i+1), jaTransactions.getJSONObject(i));
            }
            jsonObject.put("Sabrina", joCollectionOfTransactions);
            obj = jsonObject;
            Log.d(TAG, "Content: "+jsonObject);
            //joCollectionOfTransactions.put(jaTransactions)
            bufferedWriter.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}