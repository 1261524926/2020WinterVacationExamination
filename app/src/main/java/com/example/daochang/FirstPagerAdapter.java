package com.example.daochang;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

public class FirstPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewArrayList;
    FirstPagerAdapter(ArrayList<View> viewArrayList)
    {
        this.viewArrayList=viewArrayList;
    }

    @Override
    public int getCount()
    {
        return viewArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position)
    {
        View view=viewArrayList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view,Object object)
    {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        container.removeView((View)object);
    }
}
