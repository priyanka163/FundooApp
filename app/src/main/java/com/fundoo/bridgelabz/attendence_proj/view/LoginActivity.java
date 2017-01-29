package com.fundoo.bridgelabz.attendence_proj.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.Interface.ILogiinData;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.model.LoginModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.LoginViewmodel;

import java.util.regex.Pattern;

/**
 * Created by bridgeit on 19/12/16.
 *
 * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 *
 */

public class LoginActivity extends AppCompatActivity implements ILogiinData {
    private static final String TAG = "time stamp";
    EditText mUsername, mPassword;
    Button mClick;
    String mEmailId;
    String mPswrd;
    ProgressDialog mProgressDialog;
    SharedPreferences mSharedPreferences;
    LoginViewmodel mViewmodel;
    String loginUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mUsername = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mClick = (Button) findViewById(R.id.button2);


        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loginUrl=getResources().getString(R.string.url);
                loginValidation();
                 Log.i("LoginActivity", "onSuccess: ");

            }
        });
    }
    // validate the email and password befor call to restApi
    public  void  loginValidation() {
        mEmailId = mUsername.getText().toString();
        mPswrd = mPassword.getText().toString();

        //get the email pattern from resourse file
        String condition = getResources().getString(R.string.loginvalidation);

        //email pattern matches
        boolean emailMatch = Pattern.matches(condition, mEmailId);

        //validates the email and password
        if (!mEmailId.equals("") && !mPswrd.equals("") && emailMatch && mPswrd.length() > 5) {
            checkInternetConn();


        } else {
            Toast.makeText(this, "please Enter the Email and password", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void callBack(LoginModel loginModel) {
        mProgressDialog.dismiss();
        Log.i("call back dataaaaa", "onSuccess: ");

        /*mSharedPreferences.getString("MESSAGE", null);
         mSharedPreferences.getInt("STATUS", 0);*/

        String message = loginModel.getmMessage();
        String token = loginModel.getmToken();
        loginModel.getmToken();
        int status = loginModel.getmStatus();
        if (status == 200) {
            //to pass data

            //data eg:token stored in Sharedpreferences
            mSharedPreferences = getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("token", token);
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(intent);


            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

        }


    }

    private void checkInternetConn() {
        ConnectivityManager connection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connection.getActiveNetworkInfo();

        if (info !=null && info.isConnected()) {
            //show the progressDialog
            mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setMessage("Login please wait");
            mProgressDialog.show();

            //pass the url , email and pwd to view model class
            mViewmodel = new LoginViewmodel();
            mViewmodel.checkData(loginUrl,mEmailId, mPswrd, this);//this is interfce


        } else {
            mProgressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Check the internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
    }
