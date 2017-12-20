package com.giga_appz.newmatrimony;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

/**
 * Created by USER X64 on 12/3/2017.
 */

public class FirstFragment extends Fragment {
    private String urlJsonArry = "https://unaccommodating-com.000webhostapp.com/priestDetails/php/getTimeline.php";
    private String jsonResponse;
    Matcheslist matches;
    RecyclerviewAdapter adapter;
    RecyclerView recyclerView;
    List<Matcheslist> list = new ArrayList<Matcheslist>();
    private static String TAG = MainActivity.class.getSimpleName();
    public FirstFragment(){
        //Empty Constructor
    }

    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();


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
                            Toast.makeText(getActivity(),
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
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one,container,false);
        /*WebView browser = (WebView) rootView.findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);*/
        //browser.loadUrl("");

        adapter = new RecyclerviewAdapter(getActivity(), list);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        makeJsonArrayRequest();
        return rootView;
    }
}
