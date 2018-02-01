package com.giga_appz.newmatrimony;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity LoginActivity;

    private EditText mPasswordText;
    private EditText mEmailText;
    private Button mSignInButton;
    private static String TAG = LoginActivity.class.getSimpleName();
    private String jsonResponse;
    private String urlJsonObj = "http://www.mangallyam.com/mob/login.php";
    private String password;
    private String userName;
    private String deviceId;
    private TextView register;
    String temptoken="0e91267025793cb64ae0b6ee85b2b860ee9da6c2aefdeb61320d860ff0c3762e311";
    public boolean isLogin=false;
    private SweetAlertDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        LoginActivity   = this;
        mPasswordText   = (EditText) findViewById(R.id.password);
        mEmailText      = (EditText) findViewById(R.id.email);
        mSignInButton   = (Button) findViewById(R.id.email_sign_in_button);
        register        = (TextView) findViewById(R.id.register);
        /*this.registerReceiver(this.mConnReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));*/
        mSignInButton.setOnClickListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progress1=new ProgressBar(LoginActivity.this);
                progress1.setVisibility(View.VISIBLE);
                final Dialog dialog1;
                // custom dialog
                dialog1 = new Dialog(LoginActivity.this);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.registerpopup);
                dialog1.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                WebView browser = (WebView) dialog1.findViewById(R.id.webview);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.loadUrl("http://www.mangallyam.com/registration");

                browser.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(request.getUrl().toString());
                        return false;
                    }
                });


                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
                progress1.setVisibility(View.INVISIBLE);


            }
        });
    }
    /*public BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

            if(currentNetworkInfo.isConnected()){
                Toast.makeText(getApplicationContext(), "Connected To Internet", Toast.LENGTH_LONG).show();
            }else{
                internetError();
            }
        }
    };*/
    private void internetError(){
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Please check internet connectivity")
                .show();
    }

    private void makeJsonObjectRequest() {
        String tag_json_obj = "json_obj_req";
        JSONObject obj=new JSONObject();
        try{
            obj.put("userid",mEmailText.getText().toString());
            obj.put("userpwd",mPasswordText.getText().toString());
        } catch (JSONException e) {
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                urlJsonObj, obj, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object

                    String result ="";

                    if(response.has("status")) {
                        result = response.getString("status");

                        String status = response.getString("status");
                        if (status.equalsIgnoreCase("sucess")){/*
                            if (userName.equalsIgnoreCase("9746244333")){
                                SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
                                editor.putString("token", temptoken);
                                editor.putString("username",mEmailText.getText().toString() );
                                editor.apply();
                            }else{*/
                                String tokengen = response.getString("token");
                                SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
                                editor.putString("token", tokengen);
                                editor.putString("username",mEmailText.getText().toString() );
                                editor.apply();



                           /* WebView browser = (WebView) findViewById(R.id.webview);
                            browser.getSettings().setJavaScriptEnabled(true);
                            browser.loadUrl("http://mangallyam.com/mob/session.php");*/
                           pDialog.cancel();
                            mSignInButton.setBackgroundColor(Color.parseColor("#5B45D9"));
                            Intent main=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(main);
                            finish();
                            //successAlert();
                        }else if (status.equalsIgnoreCase("fail")){
                            pDialog.cancel();
                            mSignInButton.setBackgroundColor(Color.parseColor("#5B45D9"));
                            errorAlert();
                        }

                    }

                    //txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    System.out.println("error::::"+e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }

        }) /*{
            *//*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }*//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s",mEmailText.getText().toString().trim(),mPasswordText.getText().toString().trim());

                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        }*/;
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        //AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
    private void successAlert(){
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("SuccessFul")

                .show();


    }
    private void errorAlert(){
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Username or Password is Incorrect")
                .show();

    }
    /**
     * Enables https connections
     */
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    public void onClick(View view) {

        userName = mEmailText.getText().toString();
        password = mPasswordText.getText().toString();
        if (password.isEmpty() || userName.isEmpty()) {
            if (userName.isEmpty()) {
                mEmailText.setError("Enter username");
            }
            if (password.isEmpty()) {
                mPasswordText.setError("Enter password");
            }
        }else {
            if (isNetworkConnected()) {
                pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Checking Credentials");
                pDialog.setCancelable(false);
                pDialog.show();
                mSignInButton.setBackgroundColor(Color.GRAY);
                //handleSSLHandshake();
                makeJsonObjectRequest();
            }else{
                internetError();
            }


            /*new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Successful")
                    .show();*/

        }
    }



}
