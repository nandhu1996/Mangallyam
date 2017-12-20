package com.giga_appz.newmatrimony;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by USER X64 on 12/3/2017.
 */

public class FifthFragment extends Fragment {

    public FifthFragment(){
        //Empty Constructor
    }

    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_five,container,false);
        WebView browser = (WebView) rootView.findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);
        return rootView;
    }
}
