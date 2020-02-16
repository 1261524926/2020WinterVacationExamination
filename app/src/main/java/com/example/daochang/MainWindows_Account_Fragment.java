package com.example.daochang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;

public class MainWindows_Account_Fragment extends Fragment {
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=layoutInflater.inflate(R.layout.activity_main_windows_account,container,false);
        replaceFragment(new Account_MyAccount());
        tabLayout=(TabLayout)view.findViewById(R.id.account_tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:replaceFragment(PageData.getPageData().account_myAccount);
                        break;
                    case 1:replaceFragment(PageData.getPageData().account_loadAccount);
                        break;
                    case 2:replaceFragment(PageData.getPageData().account_newAccount);
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
        FragmentManager fragmentManager =getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.account_frameLayout,fragment);
        fragmentTransaction.commit();
    }

}
