package com.example.daochang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;


public class MainWindows_Home_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view=layoutInflater.inflate(R.layout.activity_main_windows__home,container,false);
        replaceFragment(new Home_community_Fragment());

        final TabLayout tabLayout=(TabLayout)view.findViewById(R.id.home_TabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:replaceFragment(new Home_community_Fragment());
                    break;
                    case 1:replaceFragment(new Home_weather_Fragment());
                    break;
                    case 2:replaceFragment(new Home_news_Fragment());
                    break;
                    case 3:replaceFragment(new Home_music_Fragment());
                    break;
                    case 4:replaceFragment(new Home_article_Fragment());
                    break;
                    case 5:replaceFragment(new Home_film_Fragment());
                    break;
                    case 6:replaceFragment(new Home_more_Fragment());
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


    public void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_windows_home_FrameLayout,fragment);
        fragmentTransaction.commit();
    }

}
