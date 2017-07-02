package com.example.jkakeno.stormyupdated;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DailyWeatherFragment extends Fragment {

    RecyclerView mRecyclerView;
    @Nullable
    @Override
//onCreateView is called to have the fragment instantiate its user interface view.
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Create the fragment that will be placed in the follow up fragment page viewer view
        View view = inflater.inflate(R.layout.fragment_daily_forecast, container, false);


//Bind the views in fragment_daily_weather.xml to the following variables
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dailyWeatherRecyclerView);


//Get the days daily weather data from mForecast
//Put the arguments past by ViewPagerFragment (main activity savedInstanceState context) into a bundle
        Bundle bundle = getArguments();
//Use that bundle to access the array of Days stored in the DAYS_KEY tag and store it in a parcelable array
        Parcelable[] parcelables = bundle.getParcelableArray(MainActivity.DAYS);
//Cast that parcelable array to an array of Days
        Day[] days = (Day[]) parcelables;

//Create a RecyclerViewAdapter object and pass the context (which is main activity savedInstanceState context) and the array of days
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), days);
//Pass the RecyclerViewAdapter object (which has daily forecast data from main Activity) to set the adapter and displayed the data on the RecyclerView view of fragment_daily_weather.xml
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

//Return the view for the fragment's UI
        return view;
    }
}
