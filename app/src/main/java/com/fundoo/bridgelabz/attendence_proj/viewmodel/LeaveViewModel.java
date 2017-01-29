package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.ILeave;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.LeaveController;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 16/1/17.
 * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * 4.This Class Has Attribute Like ContentImg,ContentTitle,LastView Date Time,
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * From Our Project We ContentModel And ContentViewModels Both Require Different
 * Data So Over
 * Here We Can Take The Necessary Data From Both And Pass It To The View.
 */

public class LeaveViewModel {
    public  void getRequest(String leaveurl, RequestParams params, String tokendataH, final ILeave iLeave) {
        final ArrayList<UnmarkEmplyModel> arrayList=new ArrayList<UnmarkEmplyModel>();


       LeaveController cntrl= new LeaveController();
        cntrl.reqViewmodel(leaveurl,params,tokendataH, new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {
                try {
                    JSONObject object= new JSONObject(new String(bytes));
                    JSONArray jsonArray=object.getJSONArray("leaveOutEmployee");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject childObject=jsonArray.getJSONObject(i);
                        Log.i("employeejson", "controllerData: "+childObject);
                        //create model
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


                        arrayList.add(model);
                        Log.i("data in model", "unmarkDetail  "+arrayList);
                        Log.i("data in model", "unmarkDetail  "+arrayList.size());
                    }
                    UnmarkEmplyModel model= new UnmarkEmplyModel();
                    model.setTimeStamp(object.getLong("timeStamp"));
                    model.setmEmployeeleave(object.getInt("employeeLeave"));
                    model.setTotalEmployee(object.getInt("totalEmployee"));
                    Log.i("timestampleave", "controllerData: "+object.getLong("timeStamp"));
                    Log.i("employeLeaveleave", "controllerData: "+object.getInt("employeeLeave"));
                    Log.i("totalEmployeeleave", "controllerData: "+object.getInt("totalEmployee"));
                    arrayList.add(model);
                    Log.i("data in model", "arraylist ....................................... "+arrayList);
                    iLeave.populateDataI(arrayList);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
