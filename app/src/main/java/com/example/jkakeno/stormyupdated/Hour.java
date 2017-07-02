package com.example.jkakeno.stormyupdated;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;

//Created as the model to extract data FORECAST BY HOURS received from the service to display in our app
//Implement parcelable interface to use the interface to pass data to another class

public class Hour implements Parcelable{
    private long mTime;
    private String mSummary;
    private String mIcon;
    private String mTimeZone;
    private double mTemperature;

//Add a blank constructor (doens't do any thing) to escape the building error
    public Hour(){}

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

//    public double getTemperature() {return mTemperature; }

//This will be used in the HourAdapter to bind data
//Convert the method to return int instead of double
    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public String getIcon() {
        return mIcon;
    }

//Add a method to get the icon using the utility method in Forcast class
//This will be used in the HourAdapter to bind data
    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }
//Add the method to get the hour formated so we can use it in our HourAdapter
//This will be used in the HourAdapter to bind data
    public String getHour(){
//Date formating options in this link: https://developer.android.com/reference/java/text/SimpleDateFormat.html
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date date = new Date(mTime * 1000);
        return formatter.format(date);
    }

//Method that need to be overwrote as part of implementing parcelable interface
    @Override
    public int describeContents() {
        return 0;//Don't need this method
    }
//Method that need to be overwrote as part of implementing parcelable interface
//Need to write the informatoin from this class to the destination parcel where is passed in
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperature);
        dest.writeString(mIcon);
        dest.writeString(mTimeZone);
    }

//Write the private constructor that will be used to read in from a parcel
//The constructor is private because it will only be accessed in this class
//Data must be read in the same order it was wrote in writeToParcel()
    private Hour(Parcel in){
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperature = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();
    }
//Per the Parcelable doc a CREATOR field is required for Parcelable objects
    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
    @Override
    public Hour createFromParcel(Parcel source) {
//Here we call the private constructor and pass the parcelable item
        return new Hour(source);
    }
    @Override
    public Hour[] newArray(int size) {
        return new Hour[size];
    }
};
}
