package com.example.jeremy.recycler.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jeremy.recycler.model.NewsModel;
import com.example.jeremy.recycler.OnTaskCompleted;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 04/02/16.
 */
public class NewsLoader extends AsyncTask<Void, Void, Void> {

    private List<NewsModel> data;
    private Context context;
    private OnTaskCompleted listener;

    public NewsLoader(Context context, OnTaskCompleted listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        this.data = getDataAndloadNews();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onTaskCompleted(data);
    }

    private List<NewsModel> getDataAndloadNews(){
        List<NewsModel> infos = new ArrayList<NewsModel>();

//        final String URL = "smashbros.json";
//        String jsonString =  loadJSONFromAsset(URL);

        final String URL = "https://raw.githubusercontent.com/JeremyKreactive/resources/develop/smashbros.json";
        String jsonString =  getJSONFromUrl(URL);

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length() ; i++) {
                NewsModel tempData = new NewsModel();
                JSONObject currentObject = jsonArray.getJSONObject(i);
                String imageUrl = currentObject.getString("ImageUrl");
                String text = currentObject.getString("text");
                String date = currentObject.getString("date");
                Log.d("#", text + date);
                tempData.setImageUrl(imageUrl);
                tempData.setText(text);
                tempData.setDate(date);
                infos.add(tempData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infos;
    }


    public String getJSONFromUrl(String dataUrl) {
        URL url;
        HttpURLConnection connection = null;
        String responseStr = "";
        //String dataUrlParameters = "key1="+"value1"+"&key2="+"value2";
        try {
            // Create connection
            url = new URL(dataUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            //connection.setRequestProperty("Content-Length","" + Integer.toString(dataUrlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            //wr.writeBytes(dataUrlParameters);
            wr.flush();
            wr.close();
            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                //response.append('\r');
            }
            rd.close();
            responseStr = response.toString();
            Log.d("Server response",responseStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        Log.d("Response: ", responseStr);
        return responseStr;
    }

    public String loadJSONFromAsset(String url) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(url);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


}
