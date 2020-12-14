package com.example.seminar_4.Wishlist;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Async extends AsyncTask<String,Void, String> {
    private static final String TAG =Async.class.getSimpleName() ;
//    private  String url;
//    private JSONObject jsonObject;
//    public Async(String url, JSONObject jsonObject){
//        this.url=url;
//        this.jsonObject=jsonObject;
//    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection=null;

        try {
            URL url = new URL(strings[0]);
//            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte[] postData       = strings[1].getBytes( StandardCharsets.UTF_8 );

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=*****");

            connection.setRequestProperty("charset", "utf-8");
//            connection.setRequestProperty("Content-Disposition", "form-data; name=\"myfile\"; filename=\"LepirdaDamon.json\"");
            connection.setRequestProperty("Connection", "keep-alive");

            connection.setDoOutput(true);



            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.writeBytes("--*****--\\r\\n");
                wr.writeBytes("Content-Disposition: form-data; name=\"myfile\"; filename=\"LepirdaDamon.json\"\\r\\n"
                        );
                wr.write( postData );
                wr.writeBytes("\\r\\n--*****--");

            }


            connection.connect();
            int responseCode = connection.getResponseCode();
            Log.d(TAG, String.valueOf(responseCode));

//        return data;

//            try(OutputStream os = connection.getOutputStream()) {
//                byte[] input = strings[1].getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }

//            try(BufferedReader br = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine = null;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println(response.toString());
//            }

//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
//            writer.write(strings[1]);
//            writer.close();

//            connection.connect();

            // Response: 400
//            Log.e("Response", connection.getResponseMessage() + "");

//            String result=null;
//            URL url = new URL(strings[0]);
//            URLConnection connection = url.openConnection();
//            if(connection instanceof HttpURLConnection) {
//                httpURLConnection = (HttpURLConnection) connection;
//                httpURLConnection.connect();
//                int resultCode = httpURLConnection.getResponseCode();
//                if (resultCode == HTTP_OK) {
//                    OutputStream os = httpURLConnection.getOutputStream();
//
//                    @SuppressLint({"NewApi", "LocalSuppress"}) OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName(StandardCharsets.UTF_8.name()));
//                    StringBuilder textBuilder = new StringBuilder();
//                    try (Writer writer = new BufferedWriter(osw)) {
////                        int c = 0;
////                        while ((c = writer.read()) != -1) {
////                            textBuilder.append((char) c);
////
////                        }
//                        writer.write(strings[1]);
//                    }
//                    result = textBuilder.toString();
//                    publishProgress();
//                    Log.d(TAG, "Content: " + textBuilder.toString());
//                }
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


}
