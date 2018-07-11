package com.example.aditisaini.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aditi.saini on 12/05/18.
 */

public class ForecastAdapter extends ArrayAdapter<Forecast> {

    public ForecastAdapter(Context context, ArrayList<Forecast> forecast){
        super(context,0,forecast);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.weather_forecast, parent, false);
        }
        Forecast currentForecast = getItem(position);

        if (position == 0) {
            // define layout here list 1st item
            Log.e("1st elem", String.valueOf(listItemView));
            listItemView.setBackgroundColor(R.color.bg);
//            listItemView.setPadding(100,100,100,100);
            // others
//            listItemView.setBackground(R.drawable.bg);
            Log.e("not 1st elem", String.valueOf(position));
        }

        TextView currentTitle = (TextView) listItemView.findViewById(R.id.date);
        currentTitle.setText(currentForecast.getLocationName()+"");

        TextView authors = (TextView) listItemView.findViewById(R.id.avgTemp);
        authors.setText(currentForecast.getAvgTemp2()+" °C");

        return listItemView;
    }
}
