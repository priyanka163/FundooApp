package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.IUnmarkedEmployee;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.UnmarkedEmpContrl;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 9/1/17.
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

public class UnmarkedViewModel {
    public  void getRequest(String unmarkemployUrl, RequestParams params, String tokeninHeader, final IUnmarkedEmployee unmarkinterface) {
        final ArrayList<UnmarkEmplyModel> arrayList=new ArrayList<UnmarkEmplyModel>();


        UnmarkedEmpContrl cntrl= new UnmarkedEmpContrl();
        cntrl.reqViewmodel(unmarkemployUrl,params,tokeninHeader, new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {
                try {
                    JSONObject object= new JSONObject(new String(bytes));
                  long time_stamp= object.getLong("timeStamp");
                    int unmarked_Number= object.getInt("unmarkedNumber");
                   int total_employee=object.getInt("totalEmployee");
                    Log.i("UnmarkedEmpltimestamp", "Timestamp: "+time_stamp);
                    Log.i("UnmarkedEmployeenumber", "Timestamp: "+unmarked_Number);


                    JSONArray jsonArray=object.getJSONArray("umarkedEmployee");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject childObject=jsonArray.getJSONObject(i);
                        Log.i("employeejson", "controllerData: "+childObject);

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

                       /* Log.i("unmarkempname", "controllerData: "+ childObject.getString("employeeName"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("employeeStatus"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("company"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("mobile"));
                        Log.i("unmarkempname", "controllerData: "+ childObject.getString("emailId"));*/

                        arrayList.add(model);
                        Log.i("data in model", "unmarkDetail  "+arrayList);
                        Log.i("data in model", "unmarkDetail  "+arrayList.size());
                    }
                    UnmarkEmplyModel model= new UnmarkEmplyModel();
                    model.setTimeStamp(object.getLong("timeStamp"));
                    model.setUnmarkedNumber(object.getInt("unmarkedNumber"));
                    model.setTotalEmployee(object.getInt("totalEmployee"));
                    arrayList.add(model);
                    Log.i("data in model", "arraylist ....................................... "+arrayList);
                    unmarkinterface.populateDataI(arrayList);
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
