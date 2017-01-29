package com.fundoo.bridgelabz.attendence_proj.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgelabz on 6/1/17.
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

public class AttendSummryCntrl {
    private Context mContext;
    public AttendSummryCntrl(Context context){
        this.mContext = context;
    }


    public void getRegistereddata(String attendanceSumryurl, RequestParams params, String tokenHeader, final ILoginDataPass loginInterface) {
        Log.i("AttendSummryCntrl..", "getRegistereddata: "+params);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("x-token",tokenHeader);
        client.get(attendanceSumryurl, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody!=null)

                {
                   loginInterface.controllerData(responseBody);
                }

                //  Toast.makeText(mContext,"statusCode: "+statusCode,Toast.LENGTH_SHORT).show();
                Log.i("AttendSummryCntrl", "onSuccess: ");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
             //   loginInterface.controllerData(responseBody);

                Toast.makeText(mContext,"Error: "+error,Toast.LENGTH_SHORT).show();

                Log.i("AttendSummryCntrl", "onFail: "+statusCode);
                Log.i("AttendSummryCntrl", "onFail: "+error);
                Log.i("AttendSummryCntrl", "onFail: "+responseBody);
            }
        });
    }
}