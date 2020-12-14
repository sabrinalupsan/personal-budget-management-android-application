package com.example.seminar_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.seminar_4.CreditCards.RegistrationList;

public class EditDatabase extends AppCompatActivity {

    private EditText limit;
    private EditText amount;
    private EditText typeSum;
    private EditText category;
    private EditText date;
    private String id;
    RepoDatabase repoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        id = extras.getString("parameter1");
        String param2 = extras.getString("parameter2");
        String param3 = extras.getString("parameter3");
        String param4 = extras.getString("parameter4");
        String param5 = extras.getString("parameter5");
        String param6 = extras.getString("parameter6");


        limit = findViewById(R.id.et_limitEdit);
        limit.setText(param2);

        amount = findViewById(R.id.et_amountEdit);
        amount.setText(param3);

        typeSum = findViewById(R.id.et_typeSumEdit);
        typeSum.setText(param4);

        category = findViewById(R.id.et_categoryEdit);
        category.setText(param5);

        date = findViewById(R.id.et_dateEdit);
        date.setText(param6);

        repoDatabase = RepoDatabase.getInstance(this);
    }


    public void back()
    {
        Intent intent = new Intent(this, RegistrationList.class);
        startActivityForResult(intent, 3000);
    }


    public void deleteItem(View view)
    {
        final UserDataSource userDataSource = new UserDataSource(repoDatabase.userDao());
        userDataSource.deleteItem(id);
        back();
    }

    public void insertItem(View view)
    {
            final UserDataSource userDataSource = new UserDataSource(repoDatabase.userDao());
            final User user = new User(id, limit.getText().toString(),
                    amount.getText().toString(), typeSum.getText().toString(),
                    category.getText().toString(), date.getText().toString());
            userDataSource.insertUser(user);
            back();
    }

    public void updateItem(View view)
    {
        final UserDataSource userDataSource = new UserDataSource(repoDatabase.userDao());
        userDataSource.updateItem(id, limit.getText().toString(),
                amount.getText().toString(), typeSum.getText().toString(),
                category.getText().toString(), date.getText().toString());
        back();
    }
}