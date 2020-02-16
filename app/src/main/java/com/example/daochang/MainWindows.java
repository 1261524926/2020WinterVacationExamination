package com.example.daochang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainWindows extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.MainWindowsStyle);
        super.onCreate(savedInstanceState);
        CurrentData.setBufferString1(null);
        setContentView(R.layout.activity_main_windows);
        final Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("主页");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setTitleCenter(toolbar);
        replaceFragment(new MainWindows_Home_Fragment());

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setItemIconTintList(null);

    /*    Resources resources=(Resources)getBaseContext().getResources();
        ColorStateList colorStateList1=(ColorStateList)resources.getColorStateList(R.color.bottomnavigationviewcolor);
        bottomNavigationView.setItemTextColor(colorStateList1);

     */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case (R.id.family):
                        toast=Toast.makeText(getApplicationContext(),"主页",Toast.LENGTH_SHORT);
                        toast.show();
                        replaceFragment(new MainWindows_Home_Fragment());
                        toolbar.setTitle("主页");
                        break;
                    case (R.id.set):
                        toast=Toast.makeText(getApplicationContext(),"设置",Toast.LENGTH_SHORT);
                        toast.show();
                        replaceFragment(new MainWindows_Set_Fragment());
                        toolbar.setTitle("设置");
                        break;
                    case (R.id.account):
                        toast=Toast.makeText(getApplicationContext(),"个人",Toast.LENGTH_SHORT);
                        toast.show();
                        replaceFragment(new MainWindows_Account_Fragment());
                        toolbar.setTitle("个人");
                        break;
                    case (R.id.other):
                        toast=Toast.makeText(getApplicationContext(),"其它",Toast.LENGTH_SHORT);
                        toast.show();
                        replaceFragment(new MainWindows_Other_Fragment());
                        toolbar.setTitle("其它");
                        break;

                }
                return true;
            }
        });
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainwindowstop,menu);
        return true;
    }
*/

    public void setTitleCenter(Toolbar toolbar) {
        String title = "title";
        final CharSequence originalTitle = toolbar.getTitle();
        toolbar.setTitle(title);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (title.equals(textView.getText())) {
                    textView.setGravity(Gravity.CENTER);
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER;
                    textView.setLayoutParams(params);
                }
            }
            toolbar.setTitle(originalTitle);
        }
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }


}
