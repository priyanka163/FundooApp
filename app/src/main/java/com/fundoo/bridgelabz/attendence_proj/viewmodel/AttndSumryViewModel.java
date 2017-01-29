package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.ISummary;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.AttendSummryCntrl;
import com.fundoo.bridgelabz.attendence_proj.model.AttendanceSumryModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by bridgelabz on 8/1/17.

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
public class AttndSumryViewModel {
   // RequestParams params;
ArrayList<AttendanceSumryModel> arrayList=new ArrayList<AttendanceSumryModel>();
    AttendanceSumryModel model;
    public void getData(Context context, String attendanceSumryurl, RequestParams params, String tokenHeader, final ISummary summaryInterface) {

        AttendSummryCntrl cntrl = new AttendSummryCntrl(context);

        //  params.put("token", token);
        cntrl.getRegistereddata(attendanceSumryurl,params, tokenHeader,new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {
                try {

                    JSONObject object = new JSONObject(new String(bytes));

                    long time_stamp = object.getLong("timeStamp");
                    int total_emp = object.getInt("totalEmployee");
                    Log.i("AttndSumryViewModel", "timestamp: "+time_stamp+"total emp"+total_emp);
                    JSONArray jsonArray = object.getJSONArray("attendance");
                    for (int i = 0; i < jsonArray.length(); i++) {
                         model= new AttendanceSumryModel();
                        JSONObject childObject = jsonArray.getJSONObject(i);
                       /* childObject.get("day");*/
                        Log.i("jsoncontroller", "day: "+ childObject.getInt("day"));
                        childObject.getString("unmarked");
                        Log.i("jsoncontroller", "unmark: "+ childObject.getString("unmarked"));

                        model.setDay(childObject.getInt("day"));
                        model.setUnmarked(childObject.getString("unmarked"));
                    //   model.setmTotalEmplopyee(childObject.getInt("totalEmployee"));
                        arrayList.add(model);

                        Log.i(TAG, "controllerData: 23445gy   "+arrayList.size());
                    }
                    model=new AttendanceSumryModel();
                    model.setmTimeStamp(object.getLong("timeStamp"));
                    model.setmTotalEmplopyee(object.getInt("totalEmployee"));
                    arrayList.add(model);
                    Log.i(TAG, "controllerData: efthgy sedfsfdggdf   "+object.getInt("totalEmployee"));
                    Log.i(TAG, "controllerData: efthgy dggdf   "+object.getLong("timeStamp"));
                    Log.i(TAG, "controllerData: efthgy   "+arrayList.size());
                    summaryInterface.dataView(arrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
