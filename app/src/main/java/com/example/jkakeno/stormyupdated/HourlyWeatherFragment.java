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


public class HourlyWeatherFragment extends Fragment {

    RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hourly_forecast, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.hourlyWeatherRecyclerView);


        Bundle bundle = getArguments();
        Parcelable[] parcelables = bundle.getParcelableArray(MainActivity.HOURS);
        Hour[] hours = (Hour[]) parcelables;

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), hours);
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);


//Return the view for the fragment's UI
        return view;
    }
}
