package com.giga_appz.newmatrimony;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
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

public class FirstFragment extends Fragment implements  CustomOnClick {
    private String urlJsonArry = "https://unaccommodating-com.000webhostapp.com/priestDetails/php/getTimeline.php";
    private String jsonResponse;
    Matcheslist matches;
    RecyclerviewAdapter adapter;
    RecyclerView recyclerView;
    DatabaseHandler db;
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


        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.show();
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
                            progress.dismiss();
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        } finally {
                            progress.dismiss();
                            adapter.notifyDataSetChanged();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
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
        makeJsonArrayRequest();

        adapter = new RecyclerviewAdapter(getActivity(), list);
        adapter.setOnItemClickListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        db = new DatabaseHandler(getActivity());
        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.profile_image:
               // Toast.makeText(context, "photo", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "a"+db.getProfile(list.get(position).getId()), Toast.LENGTH_SHORT).show();
                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Profilesoffline> contacts = db.getAllContacts();

                for (Profilesoffline cn : contacts) {
                    String log = "Id: "+cn.getId()+" ,Name: " + cn.getName();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }
            break;
            default:
                Log.d("Insert: ", "Inserting ..");
                db.addProfile(new Profilesoffline(list.get(position).getId(), list.get(position).getName()));
                Toast.makeText(context, "id"+list.get(position).getId(), Toast.LENGTH_SHORT).show();

            break;

        }
        }
 }

