package com.fundoo.bridgelabz.attendence_proj.viewmodel;

import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.ILogiinData;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILoginDataPass;
import com.fundoo.bridgelabz.attendence_proj.controller.LoginController;
import com.fundoo.bridgelabz.attendence_proj.model.LoginModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 5/1/17.
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

public class LoginViewmodel {


    RequestParams params = new RequestParams();


    public void checkData(String loginurl,String username, String password, final ILogiinData loginInterface) {
        params.put("emailId", username);
        params.put("password", password);

        // Log.i("LoginViewmodel", "onSuccess: ");

        //get data from controller through interface
        LoginController controller = new LoginController(loginurl);
        controller.getRegistereddata(params, new ILoginDataPass() {
            @Override
            public void controllerData(byte[] bytes) {

                try {
                    JSONObject object = new JSONObject(new String(bytes));
                    Log.i("LoginViewmodel:", "hiiiii");
                    LoginModel model = new LoginModel();
                    model.setmMessage(object.getString("message"));
                    model.setmToken(object.getString("token"));
                    model.setmStatus(object.getInt("status"));

                    //send this data to login class
                    loginInterface.callBack(model);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
