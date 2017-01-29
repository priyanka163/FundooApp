package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.IDashBoard;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.DashBoardContrl;
import com.fundoo.bridgelabz.attendence_proj.model.DashBoardModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 16/1/17.
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


public class DashBoardViewModel {

    public void getRequest(String dashboard, RequestParams params, String tokenHeader, final IDashBoard iDashBoard) {
        Log.i("dashboard", "getRequest: "+params);

        DashBoardContrl contrl = new DashBoardContrl();
        contrl.validData(dashboard,params,tokenHeader, new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {
                DashBoardModel model = new DashBoardModel();
                JSONObject object = null;
                try {
                    object = new JSONObject(new String(bytes));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    model.setmTimeStamp(object.getInt("timeStamp"));

                    JSONObject childobject = object.getJSONObject("attendanceSummary");
                    model.setmMarked(childobject.getInt("marked"));
                    model.setmUnmarked(childobject.getString("unmarked"));


                    JSONObject secObj = object.getJSONObject("attendanceFallout");
                    model.setmFalloutEmployee(secObj.getInt("falloutEmployee"));
                    model.setmTotalEmployee(secObj.getInt("totalEmployee"));


                    JSONObject thirdobj = object.getJSONObject("leaveSummary");
                    model.setmLeave(thirdobj.getString("leave"));

                    iDashBoard.content(model);
                    Log.i("ghij", "dashviewmodel: "+object.getLong("timeStamp"));
                    Log.i("ghij", "dashviewmodel: "+model.getmMarked());
                    Log.i("ghij", "dashviewmodel: "+model.getmUnmarked());
                    Log.i("ghij", "dashviewmodel: "+model.getmTotalEmployee());
                    Log.i("ghij", "dashviewmodel: "+model.getmFalloutEmployee());
                    Log.i("ghij", "dashviewmodel: "+model.getmLeave());
                    Log.i("ghij", "dashviewmodel: "+model);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
