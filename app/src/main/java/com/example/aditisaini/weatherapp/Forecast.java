package com.example.aditisaini.weatherapp;

import android.location.LocationListener;

import java.util.Date;

/**
 * Created by aditi.saini on 12/05/18.
 */

public class Forecast {
    private Date Date;
    private Float AvgTemp;
    private String LocationName;
    private String AvgTemp2;

    public Forecast(String locationName,String avgTemp){
        LocationName = locationName;
        AvgTemp2 = avgTemp;
    }

//    public Forecast(java.util.Date date, Float avgTemp){
//        Date = date;
//        AvgTemp = avgTemp;
//    }
//
//    public Date getDate(){
//        return Date;
//    }
//
//    public Float getAvgTemp(){
//        return AvgTemp;
//    }

    public String getLocationName(){
        return LocationName;
    }

    public String getAvgTemp2(){
        return AvgTemp2;
    }

}
