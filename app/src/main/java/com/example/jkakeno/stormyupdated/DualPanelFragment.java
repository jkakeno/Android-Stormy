package com.example.jkakeno.stormyupdated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//This class creates the view for the follow up fragment to be displayed for tablets
public class DualPanelFragment extends Fragment {
    private static final String DAILY_FRAGMENT = "daily_fragment";
    private static final String HOURLY_FRAGMENT = "hourly_fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){

//Create the dual pane view
    View view = inflater.inflate(R.layout.fragment_dualpane,container,false);
//Create the fragment manager
    FragmentManager fragmentManager = getChildFragmentManager();
//Create the variable to store the daily fragment
    DailyWeatherFragment savedDailyFragment = (DailyWeatherFragment) fragmentManager.findFragmentByTag(DAILY_FRAGMENT);
//Create the fragment if the fragment doesn't already exit
        if (savedDailyFragment == null){
            DailyWeatherFragment dailyWeatherFragment = new DailyWeatherFragment();
            dailyWeatherFragment.setArguments(getArguments());
            fragmentManager.beginTransaction().add(R.id.leftPlaceholder,dailyWeatherFragment,DAILY_FRAGMENT).commit();
        }
//Create the variable to store the hourly fragment
    HourlyWeatherFragment savedHourlyFragment = (HourlyWeatherFragment) fragmentManager.findFragmentByTag(HOURLY_FRAGMENT);
//Create the fragment if the fragment doesn't already exit
        if (savedHourlyFragment == null){
            HourlyWeatherFragment hourlyWeatherFragment = new HourlyWeatherFragment();
            hourlyWeatherFragment.setArguments(getArguments());
            fragmentManager.beginTransaction().add(R.id.rightPlaceholder,hourlyWeatherFragment,HOURLY_FRAGMENT).commit();
        }
//Return the fragment view
        return view;
    }
//Set the action bar back to app name when we return to main activity
    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
