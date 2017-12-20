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
    private int FIRST_BUTTON = 0, SECOND_BUTTON = 1, THIRD_BUTTON = 2;
    private String urlJsonArry = "https://unaccommodating-com.000webhostapp.com/priestDetails/php/getTimeline.php";
    private String jsonResponse;
    Matcheslist matches;
    RecyclerviewAdapter adapter;
    List<Matcheslist> list = new ArrayList<Matcheslist>();
    private static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setUpViewPager(FIRST_BUTTON);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        ImageView firstButton = (ImageView) findViewById(R.id.first_button);
        ImageView secondButton = (ImageView) findViewById(R.id.second_button);
        ImageView thirdButton = (ImageView) findViewById(R.id.third_button);

        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);
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
            default:
                break;
        }

        viewPager.setAdapter(adapter);
    }

    public void makeJsonArrayRequest() {


        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);
                                //JSONObject new1= (JSONObject) response.get(0);
                                String name = person.getString("name");
                                String email = person.getString("email");
                                /*JSONObject phone = person
                                        .getJSONObject("phone");*/
                                String home = person.getString("housename");
                                String mobile = person.getString("contact");
                                String photo=person.getString("photo");
                                String id=person.getString("id");

                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Email: " + email + "\n\n";
                                jsonResponse += "Home: " + home + "\n\n";
                                jsonResponse += "Mobile: " + mobile + "\n\n\n";
                                matches = new Matcheslist(name, email, mobile,home,id,photo);
                                list.add(matches);


                            }


                            // txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        } finally {
                            adapter.notifyDataSetChanged();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_button:
                setUpViewPager(FIRST_BUTTON);
                break;
            case R.id.second_button:
                setUpViewPager(SECOND_BUTTON);
                break;
            case R.id.third_button:
                setUpViewPager(THIRD_BUTTON);
                break;
        }
    }
}
