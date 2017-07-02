package com.example.jkakeno.stormyupdated;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//This class is to use to adapt the layout in hourly_list_item.xml to RecyclerView
//It also attaches data to the ViewHolders and updates the holders with new data when the user scrolls down the list
//Difference of RecycleView and ListView:
//      RecyclerView require View holders
//      RecycleView holders in addition to hold data they are also responsible for mapping data to the view
//      With RecyclerView we can't use the holders provided by android we did with ListView in DayAdapter so we have to create our own holders methods

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder>{

//Create the variable to be used in the constructor
    private Hour[] mHours;
//Created to pass the context from the class where this class is used to the constructor of this class to use in the OnClick() below
    private Context mContext;

//Create a constructor that will let us use it in the activity and set its data
//Modified constructor to be able to receive the context (also need to change it in HourlyForecastActivity since that's where it is used)
    public HourAdapter(Context context, Hour[] hours){
        mContext = context;
        mHours = hours;
    }

//Methods that need to be overwrode by extending RecyclerView.Adapter
//This method creates the views holders that will hold the data which they can be recycled
    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//Inflate the layout in hourly_list_item.xml
//A Layer inflator is an android object that takes x amount of layouts and turns them into views in code that we can use
//Inflator parameters are: context,layout,ViewGroup
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item,parent,false);
//Create a view holder object with the inflated view from hourly_list_item.xml
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

//Bind the data in the hour object to the holder at a particular position
    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHours[position]);
    }
//Gets the number of items in the hour object array
    @Override
    public int getItemCount() {
        return mHours.length;
    }

//This inner class (nested class) recycles the holders and also reacts when a particular holder is touched
    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//Add variables for the view items in the hourly_list_item.xml
        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;

//Add the constructor for the nested class HourViewHolder
        public HourViewHolder(View itemView){
            super(itemView);
//Initialize the items views in hourly_list_item.xml
            mTimeLabel=(TextView) itemView.findViewById(R.id.timeLabel);
            mSummaryLabel=(TextView) itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel=(TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
//Set the OnClickListener in the view holder
//Because "ViewHolder" is implementing View.OnClickListener OnClick method is required for OnClick to work
            itemView.setOnClickListener(this);
        }
//Map the data in hour object to the items views in hourly_list_item.xml
        public void bindHour (Hour hour){
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());
        }
//Method that needs to be overwrote to implement "View.OnClickListener" in the RecycleView holder
    @Override
        public void onClick(View view) {
//Set the variables to use in our toast message
//We can use the items in the bindHour() since they have the data we need
            String time = mTimeLabel.getText().toString();
            String temperature = mTemperatureLabel.getText().toString();
            String summary = mSummaryLabel.getText().toString();
            String message = String.format("At %s it will be %s and %s",time,temperature,summary);
//Since we are in a custom adapter we need to pass the context where this adapter is been used
//to do that we can add it as a parameter in the "class HourAdapter" constructor,
//because "class HourViewHolder" is a nested class of "class HourAdapter" we have access to all the "class HourAdapter" properties
//so the mContext variable we set in the "class HourAdapter" is available for "class HourViewHolder"
            Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
        }
    }
}
