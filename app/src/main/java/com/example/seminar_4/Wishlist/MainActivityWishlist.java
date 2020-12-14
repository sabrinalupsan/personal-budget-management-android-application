package com.example.seminar_4.Wishlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sem4.model.Wish;
import com.example.seminar_4.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityWishlist extends AppCompatActivity  {

    private  static final int ADD_WISH_REQUEST_CODE=210;
    private List<Wish> wishList=new ArrayList<>();
//    RepoDataBase repoDatabase;

    private ListView lvWishes;
    private GridView gvWishes;
    private static final String TAG = MainActivityWishlist.class.getSimpleName() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wishes);
//        repoDatabase = RepoDataBase.getInstance(this);
        init();
        
        addLvWishesAdapter();
    }



    private void addLvWishesAdapter() {
        ArrayAdapter<Wish> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, //aici bag layout ul facut de mn
                wishList);
        lvWishes.setAdapter(adapter);

        Date date=new Date();
        Wish wish1= new Wish(1,"https://www.autocar.co.uk/sites/autocar.co.uk/files/images/car-reviews/first-drives/legacy/large-2479-s-classsaloon.jpg","Car",5, date,3000,"weekly","Material Good");
        Wish wish2= new Wish(2,"https://didmdw8v48h5q.cloudfront.net/wp-content/uploads/2019/12/New-York-Study-915x580-1.jpg","Trip to USA",3, date,12000,"weekly","Experience");



        Map<Long, Wish> wishMap = new HashMap<>();
        wishMap.put(wish1.getId(),wish1);
        wishMap.put(wish2.getId(),wish2);
        JSONArray jsonArray= new JSONArray();
        JSONObject jsonWishes= new JSONObject();

        try {
            JSONObject jsonObject=new JSONObject();

            jsonObject.put("url",wish1.getUrl());
            jsonObject.put("name",wish1.getName());
            jsonObject.put("importance",wish1.getImportance());
            jsonObject.put("deadline",wish1.getDeadline());
            jsonObject.put("cost",wish1.getCost());
            jsonObject.put("alert",wish1.getAlert());
            jsonObject.put("category",wish1.getCategory());
            jsonArray.put(jsonObject);

            JSONObject jsonObject1=new JSONObject();

            jsonObject1.put("url",wish2.getUrl());
            jsonObject1.put("name",wish2.getName());
            jsonObject1.put("importance",wish2.getImportance());
            jsonObject1.put("deadline",wish2.getDeadline());
            jsonObject1.put("cost",wish2.getCost());
            jsonObject1.put("alert",wish2.getAlert());
            jsonObject1.put("category",wish2.getCategory());

            jsonArray.put(jsonObject1);

            jsonWishes.put("Lepirda Damon-Gabriel",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        Log.d(TAG,jsonWishes.toString());

        Async postAsync=new Async();
        String url = "https://ptsv2.com/t/azjlv-1606407696/post";
//        String url = "http://167.99.143.42/upload";

        postAsync.execute(url,jsonWishes.toString());

//        String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";

//        postAsync.execute(url,jsonInputString);

//        for (Wish wish : wishList) {
//            wishMap.put(wish.getId(), wish);
//        }
//
        MyAdapter customAdapter = new MyAdapter(this, wishMap);
        gvWishes.setAdapter(customAdapter);
//        customAdapter.

    }

    private void init(){
        lvWishes=findViewById(R.id.wishesLv);
        gvWishes=findViewById(R.id.gvWishes);
//        Button btn1 = findViewById(R.id.wishesBtn);
//        Button btn2 = findViewById(R.id.addWishBtn);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
    }




    public void startAddWish(View view) {
        Intent intent= new Intent(getApplicationContext(), AddWishActivity.class);
        startActivityForResult(intent,ADD_WISH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== ADD_WISH_REQUEST_CODE&&resultCode==RESULT_OK&&data!=null){
            Wish wish =(Wish)data.getSerializableExtra("wish"); // getParcealableExtra for yk
            if(wish!=null){
                Toast.makeText(getApplicationContext(),"Wish was added succesfully",Toast.LENGTH_LONG);
                wishList.add(wish);
                ArrayAdapter adapter=(ArrayAdapter)lvWishes.getAdapter();
            }
        }
    }

    public void btnSave(View view) {
//        int count = gvWishes.getAdapter().getCount();
//        View view1= gvWishes.getAdapter().getView();
        MyAdapter adapter = (MyAdapter)gvWishes.getAdapter();
        for(int i=0;i<adapter.getCount();i++){
            if(adapter.checkedHolder[i]){
                wishList.add((Wish)adapter.getItem(i));
            }
//            ArrayAdapter adapter1=(ArrayAdapter)lvWishes.getAdapter();

            ArrayAdapter<Wish> adapter1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, //aici bag layout ul facut de mn
                    wishList);
            lvWishes.setAdapter(adapter);

        }
//            Wish wish=(Wish) gvWishes.getAdapter().getItem(i);
//            if(wish.)
//        }
    }


    //    @Override
//    public void onClick(View v) {
////        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show();
//        Fragment fragment = null;
//        switch (v.getId())
//        {
//            case R.id.wishesBtn:
//                fragment = mainFragment.newInstance("main"," Fragment");
//                break;
//            case R.id.addWishBtn: {
//                //creezi un nou wish
//                //modifici newInstance din addFragment sa accepte un Wish
//                fragment = addFragment.newInstance();
//                break;
//            }
//        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, fragment);
//        fragmentTransaction.commit();
//    }
//
//
//
//
//    @Override
//    public void onViewClick(String p1, String p2) {
//        Context context =null;
////        Toast.makeText(context,"",1).show();
//        Toast.makeText(this, p1+ " " + p2, Toast.LENGTH_LONG).show();
//    }
}