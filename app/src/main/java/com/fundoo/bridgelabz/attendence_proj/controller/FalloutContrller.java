package com.fundoo.bridgelabz.attendence_proj.controller;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 6/1/17.
 * * Purpose:
 * It is data controller in mvvm arch.
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models and Views .Controller Will Get The Data From The Server
 * And Will Pass Data To viewmodel.
 * It will interact with rest service to get data with the cloud
 * It encapsulates content info model
 * This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer data model.
 */

public class FalloutContrller {
    public void reqViewmodel(String mFallouturl, RequestParams params, String tokeninHeader, final ILoginDataPass loginInterface) {

        AsyncHttpClient client= new AsyncHttpClient();
        client.addHeader("x-token",tokeninHeader);
        client.get(mFallouturl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody!=null)

                {
                    loginInterface.controllerData(responseBody);
                    Log.i("fallControl", "onSuccess: ");
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("fallControl", "fail: ");

            }
        });
    }
}
