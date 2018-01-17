package com.giga_appz.newmatrimony.messages;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.giga_appz.newmatrimony.AppController;
import com.giga_appz.newmatrimony.CustomOnClick;
import com.giga_appz.newmatrimony.DatabaseHandler;
import com.giga_appz.newmatrimony.Flash;
import com.giga_appz.newmatrimony.LoginActivity;
import com.giga_appz.newmatrimony.MainActivity;
import com.giga_appz.newmatrimony.Matcheslist;
import com.giga_appz.newmatrimony.Profilesoffline;
import com.giga_appz.newmatrimony.R;
import com.giga_appz.newmatrimony.RecyclerviewAdapter;
import com.giga_appz.newmatrimony.adapter.RecyclerviewAdapter6;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by USER X64 on 12/3/2017.
 */

public class SeventhFragment extends Fragment implements CustomOnClick {
    //private String urlJsonArry = "https://unaccommodating-com.000webhostapp.com/priestDetails/php/getTimeline.php";
    private String urlJsonArry = "http://www.mangallyam.com/mob/list.php?tk=";
    String urlaftertk="&pg=";
    String urlafterpage="&mt=rejected";
    public int page=0;
    private String jsonResponse;
    Matcheslist matches;
    RecyclerviewAdapter6 adapter;
    RecyclerView recyclerView;
    RelativeLayout bottomLayout;
    TextView nocontent;
    String temptoken="0e91267025793cb64ae0b6ee85b2b860ee9da6c2aefdeb61320d860ff0c3762e311";
    Profilesoffline employee_One;
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    DatabaseHandler db;
    List<Matcheslist> list = new ArrayList<Matcheslist>();
    private static String TAG = MainActivity.class.getSimpleName();

    public SeventhFragment(){
        //Empty Constructor
    }

    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();


    }



    /*public void jsonNew(){
        String tag_json_obj = "json_obj_req";
        JSONObject obj=new JSONObject();
        try{
            obj.put("tk","");
            obj.put("pg",page);
            obj.put("empty","newmatches");
        } catch (JSONException e) {
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                "http://www.mangallyam.com/mob/list.php", obj, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(final JSONObject response) {
                String result;
                try {


                    if (response.has("status")) {
                        result = response.getString("status");

                        if (result.equalsIgnoreCase("invalid")) {
                            //Getting Excellent array from response.

                        }
                    }else{
                        //JSONArray responseArray = array.getJSONArray("excellent");
                        for (int i = 0; i < responseArray.length(); i++) {

                                *//*JSONObject object = responseArray.getJSONObject(i);

                                if (object.has("feedbackDate")) {

                                }*//*
                            JSONObject person = (JSONObject) responseArray.get(i);
                            //JSONObject new1= (JSONObject) response.get(0);
                            String age = person.getString("age");
                            String education = person.getString("education");
                                *//*JSONObject phone = person
                                        .getJSONObject("phone");*//*
                            String home = person.getString("location");
                            //String mobile = person.getString("contact");
                            String photo=person.getString("image_path");
                            String id=person.getString("m_id");

                            jsonResponse += "Name: " + age + "\n\n";
                            jsonResponse += "Email: " + education + "\n\n";
                            jsonResponse += "Home: " + home + "\n\n";
                            //jsonResponse += "Mobile: " + mobile + "\n\n\n";
                            //employee_One = new Profilesoffline(id,age,education,BitmapFactory.decodeFile("http://www.mangallyam.com/public/images/profile/dp_170/"+photo),home);
                            //db.addProfile(employee_One);
                            // employee_One = db.retriveDetails();
                            //System.out.println("database"+employee_One.getAge());
                            matches = new Matcheslist(age, education,home,id,photo);
                            list.add(matches);


                        }


                    }


                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }

        }) *//*{
            *//**//*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }*//**//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s",mEmailText.getText().toString().trim(),mPasswordText.getText().toString().trim());

                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        }*//*;
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        //AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }*/

    public void makeJsonArrayRequest() {

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry+sharedpreferences.getString("token", "")+urlaftertk+page+urlafterpage,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parsing json array response
                            // loop through each json object
                            if (response.toString().equalsIgnoreCase("[]")){
                                recyclerView.setVisibility(View.INVISIBLE);
                                nocontent.setVisibility(View.VISIBLE);
                            }else {


                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response
                                            .get(i);
                                    //JSONObject new1= (JSONObject) response.get(0);
                                    String age = person.getString("age");
                                    String education = person.getString("education");
                                /*JSONObject phone = person
                                        .getJSONObject("phone");*/
                                    String home = person.getString("location");
                                    //String mobile = person.getString("contact");
                                    String photo = person.getString("image_path");
                                    String id = person.getString("m_id");

                                    jsonResponse += "Name: " + age + "\n\n";
                                    jsonResponse += "Email: " + education + "\n\n";
                                    jsonResponse += "Home: " + home + "\n\n";
                                    //jsonResponse += "Mobile: " + mobile + "\n\n\n";
                                    //employee_One = new Profilesoffline(id,age,education,BitmapFactory.decodeFile("http://www.mangallyam.com/public/images/profile/dp_170/"+photo),home);
                                    //db.addProfile(employee_One);
                                    // employee_One = db.retriveDetails();
                                    //System.out.println("database"+employee_One.getAge());
                                    matches = new Matcheslist(age, education, home, id, photo);
                                    list.add(matches);

                                }
                            }

                            // txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            adapter.notifyDataSetChanged();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errorjson1"+error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    public void arrayswipe(){
        page=page+1;
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        JsonArrayRequest req = new JsonArrayRequest("http://www.mangallyam.com/mob/list.php?tk="+sharedpreferences.getString("token", "")+"&pg="+page+"&mt="+urlafterpage,
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
                                String age = person.getString("age");
                                String education = person.getString("education");
                                /*JSONObject phone = person
                                        .getJSONObject("phone");*/
                                String home = person.getString("location");
                                //String mobile = person.getString("contact");
                                String photo=person.getString("image_path");
                                String id=person.getString("m_id");

                                jsonResponse += "Name: " + age + "\n\n";
                                jsonResponse += "Email: " + education + "\n\n";
                                jsonResponse += "Home: " + home + "\n\n";
                                //jsonResponse += "Mobile: " + mobile + "\n\n\n";
                                //employee_One = new Profilesoffline(id,age,education,BitmapFactory.decodeFile("http://www.mangallyam.com/public/images/profile/dp_170/"+photo),home);
                                // db.addProfile(employee_One);
                                //employee_One = db.retriveDetails();
                                //System.out.println("database"+employee_One.getAge());
                                matches = new Matcheslist(age, education,home,id,photo);
                                list.add(matches);

                            }


                            // txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {

                            adapter.notifyDataSetChanged();
                            bottomLayout.setVisibility(View.INVISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(req);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seventh,container,false);
        /*WebView browser = (WebView) rootView.findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);*/
        //browser.loadUrl("");


        bottomLayout = (RelativeLayout) rootView
                .findViewById(R.id.loadItemsLayout_listView6);
        makeJsonArrayRequest();
        adapter = new RecyclerviewAdapter6(getActivity(), list);
        adapter.setOnItemClickListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler6);
        nocontent= (TextView) rootView.findViewById(R.id.nocontent);
        final LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        db = new DatabaseHandler(getActivity());

        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
        //swipeView.setColorScheme();
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                arrayswipe();
                Log.d("Swipe", "Refreshing Number");
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);

                    }
                }, 3000);
            }
        });



        recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView,
                                                     int newState) {

                        super.onScrollStateChanged(recyclerView, newState);

                        // If scroll state is touch scroll then set userScrolled
                        // true
                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            userScrolled = true;
                        }

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx,
                                           int dy) {

                        super.onScrolled(recyclerView, dx, dy);
                        // Here get the child count, item count and visibleitems
                        // from layout manager

                        visibleItemCount = manager.getChildCount();
                        totalItemCount = manager.getItemCount();
                        pastVisiblesItems = manager
                                .findFirstVisibleItemPosition();

                        // Now check if userScrolled is true and also check if
                        // the item is end then update recycler view and set
                        // userScrolled to false
                        if (userScrolled && (visibleItemCount + pastVisiblesItems) == totalItemCount-3) {
                            bottomLayout.setVisibility(View.VISIBLE);
                            userScrolled = false;
                            arrayswipe();


                        }

                    }

                });




        return rootView;
    }


    @Override
    public void onItemClick(final View view, int position) {
        switch (view.getId()) {
            case R.id.profile_image:
                //Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                ProgressBar progress=new ProgressBar(getActivity());
                progress.setVisibility(View.VISIBLE);
                final Dialog dialog;
                // custom dialog
                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.imagepop);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                // set the custom dialog components - text, image and button
                ImageView close = (ImageView) dialog.findViewById(R.id.btnClose);
                ImageView profileopen=(ImageView)dialog.findViewById(R.id.profileenlarge);
                Picasso.with(getActivity()).load("http://www.mangallyam.com/public/images/profile/dp_350/"+list.get(position).getPhoto()).into(profileopen);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //TODO Close button action
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                progress.setVisibility(View.INVISIBLE);
                break;
            case R.id.card_view:
                view.setBackgroundColor(getResources().getColor(R.color.off_white));
                new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        view.setBackgroundColor(getResources().getColor(R.color.white));


                    }
                }, 200);

                if (isNetworkConnected()) {



                    ProgressBar progress1=new ProgressBar(getActivity());
                    progress1.setVisibility(View.VISIBLE);
                    final Dialog dialog1;
                    // custom dialog
                    dialog1 = new Dialog(getActivity());
                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setContentView(R.layout.profilepopup);
                    dialog1.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                    final WebView browser = (WebView) dialog1.findViewById(R.id.webview);
                    browser.getSettings().setJavaScriptEnabled(true);
                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                    browser.loadUrl("http://www.mangallyam.com/mob/viewpage.php?tk="+sharedpreferences.getString("token", "")+"&mt="+list.get(position).getId());

                    browser.setWebViewClient(new WebViewClient() {
                        ProgressBar progressBar= (ProgressBar) dialog1.findViewById(R.id.more_progress);
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                            view.loadUrl(request.getUrl().toString());

                            progressBar.setVisibility(View.VISIBLE);
                            browser.setVisibility(View.INVISIBLE);
                            return false;
                        }
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            progressBar.setVisibility(View.GONE);
                            browser.setVisibility(View.VISIBLE);

                        }
                    });

                    // set the custom dialog components - text, image and button
                    ImageView close1 = (ImageView) dialog1.findViewById(R.id.btnClose1);
                    close1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog1.dismiss();
                            //TODO Close button action
                        }
                    });
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog1.show();
                    progress1.setVisibility(View.INVISIBLE);
                }else{
                    internetError();
                }
                break;

        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    private void internetError(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Network Error")
                //.setContentText("Won't be able to recover this file!")
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}

