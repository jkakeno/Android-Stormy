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

//This class creates the view for the hourly forecast
public class HourlyWeatherFragment extends Fragment {

    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Create the fragment that will be placed in the follow up fragment page viewer view
        View view = inflater.inflate(R.layout.fragment_hourly_forecast, container, false);

//Bind the views in fragment_hourly_forecast.xml to the variable
        mRecyclerView = (RecyclerView) view.findViewById(R.id.hourlyWeatherRecyclerView);
//Get the hourly forecast from main activity
        Bundle bundle = getArguments();
//Use that bundle to access the array of Hours stored in the HOURS tag and store it in a parcelable array
        Parcelable[] parcelables = bundle.getParcelableArray(MainActivity.HOURS);
//Cast the parcelable array to an array of Hours
        Hour[] hours = (Hour[]) parcelables;
//Create a RecyclerViewAdapter object and pass the context (which is main activity savedInstanceState context) and the array of hours
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), hours);
//Pass the RecyclerViewAdapter object (which has daily forecast data from main Activity) to set the adapter
        mRecyclerView.setAdapter(adapter);
//Displayed the data on the RecyclerView view of fragment_hourly_forecast.xml
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
//Return the view for the fragment's UI
        return view;
    }
}
