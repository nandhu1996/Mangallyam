package com.giga_appz.newmatrimony;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Flash extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private static final int MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
       //    checkPermissions();
        SharedPreferences sharedpreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        if (sharedpreferences.contains("token")) {
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity


                    Intent loginCheck = new Intent(Flash.this, MainActivity.class);
                    startActivity(loginCheck);
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

        } else {
            Intent loginCheck = new Intent(Flash.this, LoginActivity.class);
            startActivity(loginCheck);
            finish();
        }
    }
  /*  *//**
     * method to check whether app have all permissions needed
     *//*
    private void checkPermissions() {
        //checking whether have external storage write permission
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            //checking already user denied the previous request for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(Flash.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Toast.makeText(getApplicationContext(), "Please grand permission for normal flow of app", Toast.LENGTH_SHORT).show();
            }

            //requesting permission for external storage write
            ActivityCompat.requestPermissions(Flash.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);

        }
    }
    *//**
     * the permission request result will be getting here
     * @param requestCode request code for select the request
     * @param permissions permissions
     * @param grantResults user reply for request
     *//*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission granted
                    //exportButton.setVisibility(View.VISIBLE);
                } else {
                    //permission denied
                    //removing export button since it requires permission
                   // Toast.makeText(getApplicationContext(), "Please grand permission for normal flow of app", Toast.LENGTH_SHORT).show();
                    //exportButton.setVisibility(View.INVISIBLE);
                    //addButton.setVisibility(View.INVISIBLE);
                    //shareButton.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }*/
}
