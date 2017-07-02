package com.example.jkakeno.stormyupdated;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//This class creates the item view and binds the item view into the holder to create a list
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Day[] mDays;
    private Hour[] mHours;
    private Day mDay;
    private Hour mHour;

    //Get the data from DailyWeatherFragment to initialize member variables; context (which is main activity savedInstanceState context), array of days
    public RecyclerViewAdapter(Context context, Day[] days) {
        mContext = context;
        mDays = days;
    }

    //Get the data from DailyWeatherFragment to initialize member variables; context (which is main activity savedInstanceState context), array of hours
    public RecyclerViewAdapter(Context context, Hour[] hours) {
        mContext = context;
        mHours = hours;
    }

    //Create view item holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = parent;
        if (mDays instanceof Day[]) {
//Create the item view of the daily forecast list
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.daily_list_item, parent, false);
//Pass the item view to the view holder to place the item view on a list

        } else if (mHours instanceof Hour[]) {
//Create the item view of the hourly forecast list
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.hourly_list_item, parent, false);
//Pass the item view to the view holder to place the item view on a list
        }
        return new ViewHolder(view);
    }


    //Bind each view holder into position with in the adapter
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//If the data passed is an array of days
        if (mDays instanceof Day[]) {
//Store each item of the array of days into mDay variable to be used to set the name of the day in the holder
            mDay = mDays[position];
//Pass a day into a holder
            holder.bindDayViewHolder(mDays[position]);
//If the data passed is an array of hours
        } else if (mHours instanceof Hour[]) {
//Store each item of the array of hours into mHour variable to be used to set the hour in the holder
            mHour = mHours[position];
//Pass an hour into a holder
            holder.bindHourViewHolder(mHours[position]);
        }
    }

//Gets the number of item view the adapter needs to hold depending on the size of array passed
    @Override
    public int getItemCount() {
        return mDays instanceof Day[]? mDays.length : mHours.length;
    }

    @Override
    public long getItemId(int i) {
        return 0; // Won't be used
    }


    //This class creates a holder and dynamical sets the values for each view in the item view.
//Also it makes a formatted message to display when a holder is tapped.
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mCircleImageView;
        private ImageView mIconImageView;
        private TextView mDayNameLabel;
        private TextView mTemperatureLabel;
        private TextView mTimeLabel;
        private TextView mSummaryLabel;

        //Get the item view from the RecyclerViewAdapter
        private ViewHolder(View itemView) {
            super(itemView);
//Bind the temp label and icon image view of the item view to member variables
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
//Since the item view for list of days and hours differ except for temp label and icon image change the remaining views based on the data its passed in
//If the passed array is days bind circle image and day name to member variables
            if (mDays instanceof Day[]) {
                mCircleImageView = (ImageView) itemView.findViewById(R.id.circleImageView);
                mDayNameLabel = (TextView) itemView.findViewById(R.id.dayNameLabel);
//If the passed array is hours bind hour label and summary label to member variables
            } else {
                mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
                mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            }
//Set the click listener to the item view created
            itemView.setOnClickListener(this);
        }

        //This method gets a day or hour passed from the adapter
        private void bindDayViewHolder(Day day) {
//set the first item name label to "Today"
            if (day == mDays[0]) {
                mDayNameLabel.setText(R.string.today_label);
//set the remaining item name labels to days of the week using the helper method in Day class
            } else {
                mDayNameLabel.setText(mDay.getDayOfTheWeek());
            }
//Set the remaining day list item view variable with the corresponding data
            mCircleImageView.setImageResource(R.drawable.bg_temperature);
            mTemperatureLabel.setText(day.getTemperatureMax() + "");
            mIconImageView.setImageResource(day.getIconId());
//If the passed data is hour
        }

        private void bindHourViewHolder(Hour hour) {
//Set the hour list item view variable with the corresponding data
            mTimeLabel.setText(mHour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());
        }

        //Toast a message when user taps on a view holder
        @Override
        public void onClick(View view) {
            String label;
            String conditions;
//If the data passed is days
            if (mDays instanceof Day[]) {
//Store the day name label on a variable
                label = mDayNameLabel.getText().toString();
                conditions = mDay.getSummary();
//If the data passed is hours
            } else {
//Store the time label on a variable
                label = mTimeLabel.getText().toString();
                conditions = mHour.getSummary();
            }
//Format the toast message using the variables stored above
            String message = String.format("The summary for %s is: %s", label, conditions);
//Toas the formated message
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
}


