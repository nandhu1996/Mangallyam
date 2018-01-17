package com.giga_appz.newmatrimony.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.giga_appz.newmatrimony.R;


public class Payment extends Fragment {
    ProgressDialog pd;
    String temptoken="0e91267025793cb64ae0b6ee85b2b860ee9da6c2aefdeb61320d860ff0c3762e311";
    public Payment() {
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
        View rootView=inflater.inflate(R.layout.fragment_payment,container,false);
        WebView browser = (WebView) rootView.findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);
        //SharedPreferences sharedpreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait Loading...");
        pd.setCancelable(false);
        pd.show();
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        browser.loadUrl("http://www.mangallyam.com/mob/payment.php?tk="+temptoken);
        browser.setWebViewClient(new MyWebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
    return rootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("on finish");
            if (pd.isShowing()) {
                pd.dismiss();
            }

        }
    }


}
