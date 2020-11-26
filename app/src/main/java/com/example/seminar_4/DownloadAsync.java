package com.example.seminar_4;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static android.content.ContentValues.TAG;
import static java.net.HttpURLConnection.HTTP_OK;

public class DownloadAsync extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0]; // URL to call
        /*String yourURLStr = null;
        try {
            yourURLStr = "http://host.com?param=" + java.net.URLEncoder.encode(urlString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        String data = strings[1]; //data to post
        JSONObject object=null;
        try {
            object = (JSONObject) new JSONTokener(data).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OutputStream out = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(String.valueOf(object));
            writer.flush();
            writer.close();
            out.close();
            Log.d(TAG, urlString);

            urlConnection.connect();
            InputStream is = urlConnection.getInputStream();

            int responseCode = urlConnection.getResponseCode();
            Log.d(TAG, String.valueOf(responseCode));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "Content: "+s);

    }
}
