package com.example.jkakeno.stormyupdated;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//Created as the model to extract data FORECAST BY DAYS received from the service to display in our app
//Implement parcelable interface to use the interface to pass data to another class

public class Day implements Parcelable{
    private long  mTime;
    private String mSummary;
    private String mIcon;
    private String mTimeZone;
    private double mTemperatureMax;


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

//    public double getTemperatureMax() {
//        return mTemperatureMax;
//    }

//Convert the method to return int instead of double
    public int getTemperatureMax(){
        return (int)Math.round(mTemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }

    public String getIcon() {
        return mIcon;
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


//Call getIconId() from Forecast class and pass the icon we have as a property of this current object
    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }
//Set the day of the week
    public String getDayOfTheWeek (){
//Date formating options in this link: https://developer.android.com/reference/java/text/SimpleDateFormat.html
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date dateTime = new Date(mTime * 1000);
        return formatter.format(dateTime);
    }
//Method that need to be overwrote as part of implementing parcelable interface
    @Override
    public int describeContents() {
        return 0; //Don't need this method
    }
//Method that need to be overwrote as part of implementing parcelable interface
//Write the data of this object into the destination (Parcel parameter)
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperatureMax);
        dest.writeString(mIcon);
        dest.writeString(mTimeZone);
    }

//Write the private constructor that will be used to read in from a parcel
//The constructor is private because it will only be accessed in this class
//Data must be read in the same order it was wrote in writeToParcel()
    private Day(Parcel in){
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperatureMax = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();

    }
//Add a blank constructor (doesn't do any thing) to escape the building error "private Day(Parcel in) cannot be applied"
    public Day(){}

//Per the Parcelable doc a CREATOR field is required for Parcelable objects
    public static final Creator<Day> CREATOR = new Creator<Day>() {
//Here we call the private constructor and pass the parcelable item
        @Override
        public Day createFromParcel(Parcel source) {
            return new Day(source);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}
