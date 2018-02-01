package com.giga_appz.newmatrimony.search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.giga_appz.newmatrimony.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdvanced extends Fragment {
Spinner agefrom,ageto,heightfrom,heightto,religion,caste,maritalstatus,mothertongue,state,district,horoscope,education,incomefrom,incometo;
Context context;
    public SearchAdvanced() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_advanced, container, false);
        agefrom = (Spinner) rootView.findViewById(R.id.spinner);
        agefrommethod();
        return rootView;
    }


private void agefrommethod(){

    ArrayAdapter<String> adapter;
    List<String> list;

    list = new ArrayList<String>();
    for (int i=18;i<=50;i++){
        list.add(String.valueOf(i));
    }
    adapter = new ArrayAdapter<String>(context,
            android.R.layout.simple_spinner_item, list);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    agefrom.setAdapter(adapter);
}

}
