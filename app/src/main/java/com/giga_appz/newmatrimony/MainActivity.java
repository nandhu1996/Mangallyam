package com.giga_appz.newmatrimony;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    LinearLayout linear,linear2,linear3,linear4;
    ImageView firstButton;
    Toolbar toolbar;
    private int FIRST_BUTTON = 0, SECOND_BUTTON = 1, THIRD_BUTTON = 2, FOURTH_BUTTON = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linear= (LinearLayout) findViewById(R.id.home);
        linear2= (LinearLayout) findViewById(R.id.message);
        linear3= (LinearLayout) findViewById(R.id.search);
        linear4= (LinearLayout) findViewById(R.id.settings);

        linear.setBackgroundColor(getColor(R.color.colorPrimary));
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setUpViewPager(FIRST_BUTTON);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        firstButton = (ImageView) findViewById(R.id.first_button);
        ImageView secondButton = (ImageView) findViewById(R.id.second_button);
        ImageView thirdButton = (ImageView) findViewById(R.id.third_button);
        ImageView fourthButton = (ImageView) findViewById(R.id.fourth_button);

        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);
        fourthButton.setOnClickListener(this);
    }

    private void setUpViewPager(int mode) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        switch (mode){
            case 0:
                adapter.addFragment(new FirstFragment(), "Matches");
                adapter.addFragment(new SecondFragment(), "Requests");
                adapter.addFragment(new ThirdFragment(), "Views");
                adapter.addFragment(new FourthFragment(), "Profile Viewed");
                adapter.addFragment(new FifthFragment(), "Accepted");
                adapter.addFragment(new SixthFragment(), "Rejected");
                break;
            case 1:
                adapter.addFragment(new FirstFragment(), "S_Tab 1");
                adapter.addFragment(new SecondFragment(), "S_Tab 2");
                break;
            case 2:
                adapter.addFragment(new FirstFragment(), "T_Tab 1");
                adapter.addFragment(new SecondFragment(), "T_Tab 2");
                adapter.addFragment(new ThirdFragment(), "T_Tab 3");
                break;
            case 3:
                adapter.addFragment(new FirstFragment(), "T_Tab 1");
                adapter.addFragment(new SecondFragment(), "T_Tab 2");
                break;
            default:
                break;
        }

        viewPager.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_button:
                linear.setBackgroundColor(getColor(R.color.colorPrimary));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FIRST_BUTTON);
                break;
            case R.id.second_button:
                linear2.setBackgroundColor(getColor(R.color.colorPrimary));
                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FIRST_BUTTON);
                setUpViewPager(SECOND_BUTTON);
                break;
            case R.id.third_button:
                linear3.setBackgroundColor(getColor(R.color.colorPrimary));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FIRST_BUTTON);
                setUpViewPager(THIRD_BUTTON);
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                break;
            case R.id.fourth_button:
                linear4.setBackgroundColor(getColor(R.color.colorPrimary));

                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FIRST_BUTTON);
                setUpViewPager(FOURTH_BUTTON);
                break;
        }
    }
}
