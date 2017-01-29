package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.IFalloutGmail;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.FalloutGmailControl;
import com.fundoo.bridgelabz.attendence_proj.model.GmailModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 18/1/17.

 * Purpose:
 /**
 * Created by bridgeit on 6/1/17.
 * * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * Here We Can Take The Necessary Data And Pass It To The View.
 */

public class FallgmailViewmodel {


    public void getRequest(String mFalloutGmailUrl, RequestParams params, String tokeninHead, final IFalloutGmail iFalloutGmail) {
        FalloutGmailControl control= new FalloutGmailControl();
        control.reqViewmodel(mFalloutGmailUrl, params,tokeninHead, new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {
                try {
                    JSONObject object= new JSONObject(new String(bytes));
                   /*  object.getInt("status");
                    object.getString("message");*/
                    GmailModel model= new GmailModel();
                    model.setmTimeStamp(object.getLong("timeStamp"));
                    model.setmStatus(object.getInt("status"));
                    model.setmMessage(object.getString("message"));
                    Log.i("activitygmaillfallout", "leavmailmsg: "+object.getString("message")+"timestamp"+object.getInt("status"));
                    iFalloutGmail.gmailInterface(model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }
}
