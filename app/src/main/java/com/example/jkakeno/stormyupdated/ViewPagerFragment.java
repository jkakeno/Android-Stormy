package com.example.jkakeno.stormyupdated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//Create a view variable for the follow up fragment
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
//Create the data objects to be displayed in the view pager view
        final DailyWeatherFragment dailyWeatherFragment = new DailyWeatherFragment();
        final HourlyWeatherFragment hourlyWeatherFragment = new HourlyWeatherFragment();
//Create the view pager variable with in the view variable
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
//Set up an adapter to the view pager variable
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//If the position in the view pager is 0 return daily weather other wise return hourly weather
            @Override
            public Fragment getItem(int position) {
                dailyWeatherFragment.setArguments(getArguments());
                hourlyWeatherFragment.setArguments(getArguments());
                return position == 0 ? dailyWeatherFragment : hourlyWeatherFragment;
            }
//Put title to the tabs
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "This Week" : "Today";
            }

            //There's only 2 items to display in the view pager daily weather and hourly weather so the return is 2
            @Override
            public int getCount() {
                return 2;
            }
        });

//Hook up tabs to the view pager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getContext(), R.color.white));

        return view;
    }
}


