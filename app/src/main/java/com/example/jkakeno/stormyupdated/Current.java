package com.example.jkakeno.stormyupdated;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//Created as the model to extract data CURRENT FORECAST received from the service to display in our app

public class Current {

    private long mTime;
    private String mSummary;
    private String mIcon;
    //Added mTimeZone to use it in getFormattedTime()
    private String mTimeZone;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipChance;



    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

//Call getIconId() from Forecast class and pass the icon we have as a property of this current object
    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    public long getTime() {
        return mTime;
    }

//Format the time since the JSON provides a UNIX number represention of time
//Checking the Simple Time format doc indicates the SimpleDateFormat class has available methods to format time
    public String getFormattedTime(){
//Create a formatter object from the SimpleDateFormat class with the h:mm a format
//Formating options in this link: https://developer.android.com/reference/java/text/SimpleDateFormat.html
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
//Set the time zone by pushing the time zone from JSON as the parameter; we are setting the value in Main we can use mTimeZone or getTimeZone() as the parameter
//This converts the long value to a formated time
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
//Checking the Date doc indicates that Date class has method to format time
//Create a date object from the Date class, since class Date's constructor takes parameter in milliseconds and our data is in sec we multiply the data by 1000
//we can use mTime or getTime() as the parameter
        Date dateTime = new Date(getTime() *1000);
        String timeString = formatter.format(dateTime);

        return  timeString;
    }

    public void setTime(long time) {
        mTime = time;
    }
//Modified return type to return int instead of double so that the temp on the display is a whole number instead of decimalpoints
//Use round() from the Math class Math.round(mTemperature) returns a long so cast it into Int to get a small whole number
    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }
//Since getPrecipChance() returns a double number between 0 to 1 we need to multiply it by 100 to make it a percentage
    public int getPrecipChance() {
        double precipPercentage = mPrecipChance *100;
        return (int) Math.round(precipPercentage);
    }

    public void setPrecipChance(double precipChance) {
        mPrecipChance = precipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }


}