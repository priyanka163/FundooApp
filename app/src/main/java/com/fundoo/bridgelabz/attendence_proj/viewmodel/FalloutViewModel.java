package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.IFallout;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.FalloutContrller;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 16/1/17.

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

public class FalloutViewModel {

    public  void getRequest(String fallouturl, RequestParams params, String tokeninHeader, final IFallout iFallout) {
        final ArrayList<UnmarkEmplyModel> arrayList=new ArrayList<UnmarkEmplyModel>();


        FalloutContrller cntrl= new FalloutContrller();
        cntrl.reqViewmodel(fallouturl,params,tokeninHeader, new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {
                try {
                    JSONObject object= new JSONObject(new String(bytes));
                    long time_stamp= object.getLong("timeStamp");
                    int falloutNumber= object.getInt("falloutNumber");
                    int total_employee=object.getInt("totalEmployee");
                    Log.i("fallouttimestamp", "Timestamp: "+time_stamp);
                    Log.i("falloutnumber", "Timestamp: "+falloutNumber);


                    JSONArray jsonArray=object.getJSONArray("falloutEmployee");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject childObject=jsonArray.getJSONObject(i);
                        Log.i("falloutjson", "controllerData: "+childObject);

                      /*  childObject.getString("employeeName");
                        childObject.getString("employeeStatus");
                        childObject.getString("company");
                        childObject.getString("mobile");
                        childObject.getString("emailId");
                        childObject.getString("blStartDate");
                        childObject.getString("companyJoinDate");
                        childObject.getString("companyLeaveDate");
                        childObject.getInt("leaveTaken");
                        childObject.getString("engineerId");*/

                        //call model
                        UnmarkEmplyModel model= new UnmarkEmplyModel();
                        model.setmEmployeeStatus(childObject.getString("employeeStatus"));
                        model.setmEmployeeName(childObject.getString("employeeName"));
                        model.setmCompany(childObject.getString("company"));
                        model.setmMobile(childObject.getString("mobile"));
                        model.setmEmailId(childObject.getString("emailId"));
                        model.setmBlStartDate(childObject.getString("blStartDate"));
                        model.setmCompanyJoinDate(childObject.getString("companyJoinDate"));
                        model.setmCompanyLeaveDate(childObject.getString("companyLeaveDate"));
                        model.setmLeaveTaken(childObject.getInt("leaveTaken"));
                        model.setmEngineerId(childObject.getString("engineerId"));

                        Log.i("faloutviewmod", "controllerData: "+ childObject.getString("employeeStatus"));

                       /* Log.i("unmarkempname", "controllerData: "+ childObject.getString("employeeName"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("employeeStatus"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("company"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("mobile"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("emailId"));*/

                        arrayList.add(model);
                        Log.i("fall in model", "unmarkDetail  "+arrayList);
                        Log.i("fallin model", "unmarkDetail  "+arrayList.size());
                    }
                    UnmarkEmplyModel model= new UnmarkEmplyModel();
                    model.setTimeStamp(object.getLong("timeStamp"));
                    model.setmFalloutNumber(object.getInt("falloutNumber"));
                    model.setTotalEmployee(object.getInt("totalEmployee"));
                    arrayList.add(model);
                    Log.i("fall in model", "arraylist ....................................... "+arrayList);
                    iFallout.populateDataI(arrayList);
                   /* Log.i(TAG, "ygyugiuh: efthgy sedfsfdggdf   "+object.getInt("totalEmployee"));
                    Log.i(TAG, "ygyugiuh: efthgy sedfsfdggdf   "+object.getInt("unmarkedNumber"));
                    Log.i(TAG, "ygyugiuh: efthgy sedfsfdggdf   "+object.getInt("timeStamp"));
                    Log.i(TAG, "ygyugiuh: efthgy sedfsfdggdf   "+arrayList.size());*/



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

