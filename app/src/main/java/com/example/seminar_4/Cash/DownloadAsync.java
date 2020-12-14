package com.example.seminar_4.Cash;

import android.os.AsyncTask;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
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
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty(
                    "\\r\\nContent-Type:", " multipart/form-data; boundary=*****\\r\\n");

            DataOutputStream request = new DataOutputStream(
                    urlConnection.getOutputStream());
            request.writeBytes("\\r\\n--*****--\\r\\n");

            request.writeBytes("\\r\\nContent-Disposition: form-data; name=\"" +
                    "myfile" + "\"; filename=\"" +
                    "LUPSAN_SABRINA_FILE.json" + "\"");
            request.writeBytes("\\r\\n");
            request.writeBytes(data);
            request.writeBytes("\\r\\n--*****--\\r\\n");
            request.flush();
            request.close();
            Log.d(TAG, urlString);

            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            Log.d(TAG, "RESPONSE CODE: "+String.valueOf(responseCode));

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
