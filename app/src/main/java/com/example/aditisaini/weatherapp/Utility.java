package com.example.aditisaini.weatherapp;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditi.saini on 12/05/18.
 */

public class Utility {

    public static final String LOG_TAG = MainActivity.class.getName();

    public Utility(){}

    public static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";
        if (url == null)
            return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(50000 );
            urlConnection.setConnectTimeout(150000 );
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Bad response");
            }

        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    @NonNull
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Forecast> fetchData(String Url) {

        URL url = Utility.createUrl(Url);
        String jsonResponse = "";
        try {
            jsonResponse = Utility.makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

        List<Forecast> forecast = extractFeatureFromJson(jsonResponse);
        Log.i("Return", "list returned");
        return forecast;

    }

    private static List<Forecast> extractFeatureFromJson(String valJSON) {

        if (TextUtils.isEmpty(valJSON))
            return null;
        List<Forecast> forecast = new ArrayList<>();
        try {

            JSONObject root = new JSONObject(valJSON);
            JSONObject location = root.optJSONObject("location");
            String locationName = location.getString("name");
            JSONObject current = root.optJSONObject("current");
            String currentTemp = current.getString("temp_c");
            forecast.add(new Forecast(locationName,currentTemp));
            JSONObject fforecast = root.optJSONObject("forecast");
            JSONArray forecastday = fforecast.optJSONArray("forecastday");
            if(forecastday == null){
                return null;
            }
            for(int i = 0; i < forecastday.length(); i++){
                JSONObject rec = forecastday.getJSONObject(i);
                Log.e("hello",String.valueOf(rec));
                String date = rec.getString("date");
                JSONObject day = rec.optJSONObject("day");
                String avgTemp = day.getString("avgtemp_c");

                forecast.add(new Forecast(date,avgTemp));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }

        return forecast;

    }


}
