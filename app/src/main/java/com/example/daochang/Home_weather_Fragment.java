package com.example.daochang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;

public class Home_weather_Fragment extends Fragment {
    TabLayout tableLayout;
    FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=layoutInflater.inflate(R.layout.home_weather,container,false);
        tableLayout=(TabLayout)view.findViewById(R.id.home_weather_tabLayout);
        frameLayout=(FrameLayout)view.findViewById(R.id.home_weather_frameLayout);

        tableLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:replaceFragment(PageData.getPageData().weather_list_fragment);
                    break;
                    case 1:replaceFragment(PageData.getPageData().weather_seek_fragment);
                    break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_weather_frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
