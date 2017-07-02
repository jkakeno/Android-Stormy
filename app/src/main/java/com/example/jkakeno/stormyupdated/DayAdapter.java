package com.example.jkakeno.stormyupdated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//This class is to use to adapt the layout in daily_list_item.xml to ListView using ViewHolders
//It also attaches data to the ViewHolders and updates the holders with new data when the user scrolls down the list
//By extending the BaseAdapter we can use properties of the BaseAdapter making this class a subclass of BaseAdapter
//This adapter needs to know the cotext and the data it needs to map

public class DayAdapter extends BaseAdapter {
    private Context mContext;
    private Day[] mDays;

//Add a constructor that allow us to add these new values
    public DayAdapter (Context context, Day[] days){
        mContext = context;
        mDays = days;
    }

//Methods that need to be overwrode when extending BaseAdapter
//This method gets us the count of the elements in the array
    @Override
    public int getCount() {
        return mDays.length;
    }
//This method get us the item in the array at a given position
    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int i) {
        return 0; //We are not going to use this. Tag items for easy reference
    }


//Add the method that will map all the data to the view
//This technique is done to recycle views of list from the user is scrolling thru the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
//View convertView will be null the first time but if will be reused on subsequent calls
        if (convertView == null){
//Inflate the layout in daily_list_item.xml
//A Layer inflator is an android object that takes x amount of layouts and turns them into views in code that we can use (we can get it from the context)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
//Initialize the holder we defined
            holder = new ViewHolder();
//Set the views to the holders
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel= (TextView) convertView.findViewById(R.id.dayNameLabel);
//Set the view that we'll recycle to "convertView"
            convertView.setTag(holder);
        }else{
//Recycle holder
            holder = (ViewHolder) convertView.getTag();
        }
//Set the data to the holders
        Day day = mDays[position];
        holder.iconImageView.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getTemperatureMax() + "");
//Change the data in the holder when user scroll down the list
//Make the top holder today at the top holder
        if (position ==0){
            holder.dayLabel.setText("today");
        }else {
//When the user scrolls down the list day data displayed at the top holder is no longer at 0 position so holders are updated with day data for rest of the week
            holder.dayLabel.setText(day.getDayOfTheWeek());
        }
        return convertView;
    }

//Just views
    private static class ViewHolder{
        ImageView iconImageView; //public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }
}
