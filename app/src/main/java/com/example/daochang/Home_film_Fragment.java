package com.example.daochang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Home_film_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=layoutInflater.inflate(R.layout.home_film,container,false);
        return view;
    }
}
