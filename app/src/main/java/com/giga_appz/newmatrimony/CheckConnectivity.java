package com.giga_appz.newmatrimony;

/**
 * Created by NANDHU on 04-01-2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CheckConnectivity extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent arg1) {

        boolean isConnected = arg1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        if(isConnected){

            Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show();
        }
        else{

        }
    }
}
