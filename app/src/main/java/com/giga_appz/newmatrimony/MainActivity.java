package com.giga_appz.newmatrimony;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.giga_appz.newmatrimony.home.FifthFragment;
import com.giga_appz.newmatrimony.home.FirstFragment;
import com.giga_appz.newmatrimony.home.FourthFragment;
import com.giga_appz.newmatrimony.home.Payment;
import com.giga_appz.newmatrimony.home.SecondFragment;
import com.giga_appz.newmatrimony.messages.Blockedprofile;
import com.giga_appz.newmatrimony.messages.Chat;
import com.giga_appz.newmatrimony.messages.Rejectedbyothers;
import com.giga_appz.newmatrimony.messages.Requestsent;
import com.giga_appz.newmatrimony.messages.SeventhFragment;
import com.giga_appz.newmatrimony.messages.SixthFragment;
import com.giga_appz.newmatrimony.home.ThirdFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    LinearLayout linear,linear2,linear3,linear4,linear5;
    ImageView firstButton;
    Toolbar toolbar;
    TabLayout tabLayout;
    private int FIRST_BUTTON = 0, SECOND_BUTTON = 1, THIRD_BUTTON = 2, FOURTH_BUTTON = 3,FIFTH_BUTTON = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        linear= (LinearLayout) findViewById(R.id.home);
        linear2= (LinearLayout) findViewById(R.id.message);
        linear3= (LinearLayout) findViewById(R.id.search);
        linear4= (LinearLayout) findViewById(R.id.settings);
        linear5= (LinearLayout) findViewById(R.id.payment);
        linear.setBackgroundColor(getColor(R.color.colorPrimary));
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPager.setOffscreenPageLimit(7);
        setUpViewPager(FIRST_BUTTON);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        firstButton = (ImageView) findViewById(R.id.first_button);
        ImageView secondButton = (ImageView) findViewById(R.id.second_button);
        ImageView thirdButton = (ImageView) findViewById(R.id.third_button);
        ImageView fourthButton = (ImageView) findViewById(R.id.fourth_button);


        linear.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        linear5.setOnClickListener(this);
    }


    private void setUpViewPager(int mode) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        switch (mode){
            case 0:
                adapter.addFragment(new FirstFragment(), "Matches");
                adapter.addFragment(new ThirdFragment(), "Requests");
                adapter.addFragment(new FourthFragment(), "Views");
                adapter.addFragment(new FifthFragment(), "Profile Visited");
                break;
            case 1:
                adapter.addFragment(new SixthFragment(), "Accepted");
                adapter.addFragment(new Chat(), "Chat");
                adapter.addFragment(new SeventhFragment(), "Request");
                adapter.addFragment(new Requestsent(), "Request sent");
                adapter.addFragment(new Rejectedbyothers(), "Rejected by others");
                adapter.addFragment(new Blockedprofile(), "Blocked profiles");

                break;
            case 2:
                adapter.addFragment(new ThirdFragment(), "T_Tab 1");
                adapter.addFragment(new SecondFragment(), "T_Tab 2");
                adapter.addFragment(new ThirdFragment(), "T_Tab 3");
                break;
            case 3:
                adapter.addFragment(new ThirdFragment(), "T_Tab 1");
                adapter.addFragment(new SecondFragment(), "T_Tab 2");
                break;
            case 4:
                adapter.addFragment(new Payment(),"");
                break;
            default:

                break;
        }

        viewPager.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:
                tabLayout.setVisibility(View.VISIBLE);
                linear.setBackgroundColor(getColor(R.color.colorPrimary));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear5.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FIRST_BUTTON);
                break;
            case R.id.message:
                tabLayout.setVisibility(View.VISIBLE);
                linear2.setBackgroundColor(getColor(R.color.colorPrimary));
                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear5.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(SECOND_BUTTON);
                break;
            case R.id.search:
                tabLayout.setVisibility(View.VISIBLE);
                linear3.setBackgroundColor(getColor(R.color.colorPrimary));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear5.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(THIRD_BUTTON);
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                break;
            case R.id.settings:
                tabLayout.setVisibility(View.VISIBLE);
                linear4.setBackgroundColor(getColor(R.color.colorPrimary));

                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear5.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FOURTH_BUTTON);
                break;
            case R.id.payment:
                tabLayout.setVisibility(View.GONE);
                linear5.setBackgroundColor(getColor(R.color.colorPrimary));

                linear.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear2.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear3.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                linear4.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                setUpViewPager(FIFTH_BUTTON);
                break;
        }
    }
}
