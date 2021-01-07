package com.example.seminar_4.Wishlist;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.seminar_4.model.Image;
import com.example.seminar_4.model.Wish;
import com.example.seminar_4.Cash.DownloadContent;
import com.example.seminar_4.R;

import java.util.ArrayList;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyAdapter extends BaseAdapter {

    Map<Long, Wish> wishList;
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<Image> images;
   public boolean checked;

    public MyAdapter(Context context, Map<Long, Wish> wishList, ArrayList<Image> images) {
        this.wishList = wishList;
        this.context = context;
        this.images=images;
        this.layoutInflater = LayoutInflater.from(context);
        createCheckedHolder();

    }

    @Override
    public int getCount() {
        return wishList.size();
    }



    @Override
    public Object getItem(int position) {
        Object[] objects = wishList.keySet().toArray();
        return wishList.get(objects[position]);
    }

    @Override
    public long getItemId(int position) {
        Object[] objects = wishList.keySet().toArray();
        return wishList.get(objects[position]).getId();
    }

    private static class WishViewHolder
    {
        public TextView name;
        public TextView atributes;
        public CheckBox filtered;
        public ImageView img;
        private static final String TAG = MyAdapter.class.getSimpleName();



    }

//    public boolean getChecked(){
//        if(  )
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final WishViewHolder holder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.my_layout, parent, false);
            holder = new WishViewHolder();
            holder.atributes = convertView.findViewById(R.id.tvAtributes);
            holder.name = convertView.findViewById(R.id.tvName);
//            holder.filtered=convertView.findViewById(R.id.checkBox);
//            holder.population = convertView.findViewById(R.id.tvPopulation);

            holder.img = convertView.findViewById(R.id.imageView);

//            Log.d(TAG, "----------downloadImage method------------");
//            DownloadContent imageTask = new DownloadContent("https://didmdw8v48h5q.cloudfront.net/wp-content/uploads/2019/12/New-York-Study-915x580-1.jpg");
//            Thread downloadThread = new Thread(imageTask);
//            downloadThread.start();

            //casgList.get(i).

//            DownloadContent.handler = new Handler()
//            {
//                @Override
//                public void handleMessage(@NonNull Message msg) {
//                    Log.d(TAG, "----------image received from thread------------");
//                    Bundle data = msg.getData();
//                    Bitmap image = data.getParcelable("image");
//                    holder.img.setImageBitmap(image);
//                }
//            };


//        Log.d(TAG, "----------downloadImage method------------");
//            DownloadContent imageTask = new DownloadContent("https://didmdw8v48h5q.cloudfront.net/wp-content/uploads/2019/12/New-York-Study-915x580-1.jpg");
//            Thread downloadThread = new Thread(imageTask);
//            downloadThread.start();

            convertView.setTag(holder);


        }
        else
        {

            holder = (WishViewHolder) convertView.getTag();

        }
//        DownloadContent.handler = new Handler()
//        {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                Log.d(TAG, "----------image received from thread------------");
//                Bundle data = msg.getData();
//                Bitmap image = data.getParcelable("image");
//                holder.img.setImageBitmap(image);
//            }
//        };

        Wish wish = wishList.get(getItemId(position));
        holder.name.setText(wish.getName());
        holder.atributes.setText(wish.getAtributes());
//        if( wish.getCategory()=="experience")
        System.out.println("bbbbb"+images.get(0).toString());
        for (Image image : images) {
            if(wish.getCategory()==image.getCategory()){
                holder.img.setImageBitmap(image.getImage());

            }
        }
//        holder.img.setImageBitmap(this.images.get((int) getItemId(position)-1));
//        else
//            holder.img.setImageBitmap(this.images.get(1));

//        holder.filtered.setChecked(checkedHolder[position]);
//        holder.filtered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                    checkedHolder[position] = isChecked;
//            }
//        });


        return convertView;
    }

    public boolean[] checkedHolder;

    private void createCheckedHolder() {
        checkedHolder = new boolean[getCount()];
    }

//    public void downloadImage(View view) {
//        Log.d(TAG, "----------downloadImage method------------");
//        DownloadContent imageTask = new DownloadContent("http://pdm.ase.ro/images/tehnologii.png");
//        Thread downloadThread = new Thread(imageTask);
//        downloadThread.start();
//
//    }
    }


