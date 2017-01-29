package com.fundoo.bridgelabz.attendence_proj.controller;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgelabz on 18/1/17.
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

public class LeaveGmailControl {
    public  void reqViewmodel(String leavegmailUrl, RequestParams params, String tokeninHead, final ILoginDataPass loginInterface){

        AsyncHttpClient client= new AsyncHttpClient();

        client.addHeader("x-token",tokeninHead);
        client.post(leavegmailUrl, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody!=null)

                {
                    //to pass data view model
                    loginInterface.controllerData(responseBody);
                }  Log.i("gmailleave", "onSuccess: ");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("gmailleave", "onfail: ");


            }

        });
    }
}


