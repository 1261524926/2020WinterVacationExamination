package com.example.daochang;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_community_Fragment extends Fragment{
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=layoutInflater.inflate(R.layout.home_community,container,false);
        replaceFragment(PageData.getPageData().community_publicPost_fragment);

        bottomNavigationView=(BottomNavigationView)view.findViewById(R.id.home_community_bottomNavigationView);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case(R.id.public_post):
                        replaceFragment(PageData.getPageData().community_publicPost_fragment);
                        break;
                    case(R.id.publish_post):
                        replaceFragment(PageData.getPageData().community_publishPost_fragment);
                        break;
                    case(R.id.history_post):
                        replaceFragment(PageData.getPageData().community_historyPost_fragment);
                        break;
                }
                return false;
            }
        });

        return view;
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_community_frameLayout,fragment);
        fragmentTransaction.commit();
    }
}

