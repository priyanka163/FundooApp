package com.fundoo.bridgelabz.attendence_proj.view;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.Interface.IData;
import com.fundoo.bridgelabz.attendence_proj.Interface.IGmail;
import com.fundoo.bridgelabz.attendence_proj.Interface.IUnmarkedEmployee;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.adapter.EmployeeAdapter;
//import com.fundoo.bridgelabz.attendence_proj.controller.EmployeeController;
import com.fundoo.bridgelabz.attendence_proj.controller.EmployeeController;
import com.fundoo.bridgelabz.attendence_proj.model.EmployeeModelData;
import com.fundoo.bridgelabz.attendence_proj.model.GmailModel;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.GmailViewModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.UnmarkedViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by bridgelabz on 15/12/16.
 * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 *
 * Displays The RecyclerView Containing All ContentList.It is The MainActivity
 * Which Need To Be Included In Manifest.xml File.
 *
 */


public class Attendence extends Fragment implements IUnmarkedEmployee, IGmail {
    RecyclerView mRecyclerView;
    //firebase comment
   ArrayList<EmployeeModelData> mData;
    ImageView mBackicon, mGmailbutton;
    TextView mCount;
    TextView mSetDate;
    TextView mDatedisplay;
    ArrayList<UnmarkEmplyModel> mArrayList;
    ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.employ_recycler, container, false);
        mProgressDialog=new ProgressDialog(getActivity());
        mCount= (TextView) v.findViewById(R.id.count);
        mSetDate = (TextView) v.findViewById(R.id.dateview);
        //reading saved data from sharedpreference

        SharedPreferences preferences=getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokeninHeader=preferences.getString("token",null);
        Log.i( "AttendanceClass: ","unmarkedEmplytoken"+tokeninHeader);
        Long tsLong = System.currentTimeMillis();
        String tsmillisec = tsLong.toString();
        Log.i("currenttime", "onCreateView: "+tsmillisec);

        // sending request
        RequestParams params= new RequestParams();
       // params.put("",tokendata3);
        params.put("timeStamp",tsmillisec);
        String unmarkemployUrl=getResources().getString(R.string.Unmark);
        UnmarkedViewModel unmarkedViewModel= new UnmarkedViewModel();
        unmarkedViewModel.getRequest(unmarkemployUrl,params,tokeninHeader,this);
        showProgressDailog("progressing",true,true);
      //  mCount.setText("0/100");
        //setting data
        String unmark=   getArguments().getString("unmark");
        int total=getArguments().getInt("totalcalemp");
        mCount.setText(unmark+"/"+total);
        int textMonth = getArguments().getInt("month");
        Log.i("Attendance", "newInstance: "+textMonth);
        int textYear = getArguments().getInt("year");
        Log.i("Attendance", "newInstance: "+textYear);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_attendence);
         mDatedisplay= (TextView) v.findViewById(R.id.dateview);
        mBackicon = (ImageView) v.findViewById(R.id.id_backicon);
        mGmailbutton = (ImageView) v.findViewById(R.id.gmail);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mlayoutManager);
        EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), mData);
        //mRecyclerView.setAdapter(adapter);
       // firebase comment
        mData = new ArrayList<EmployeeModelData>();

        try {

        // String[] months=getResources().getStringArray(R.array.months);
            Log.i("Attendance", "onCreateView: "+textMonth+" ,"+textYear);
              mDatedisplay.setText(textMonth+"/"+textYear);

        } catch (Exception e) {
            e.printStackTrace();
        }


        mBackicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getFragmentManager().beginTransaction().replace(R.id.frame, new AtndSummaryActivity()).commit();
                Intent intent = new Intent(getActivity(),AtndSummaryActivity.class);

                getActivity().startActivity(intent);

                Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
            }
        });
        mGmailbutton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to send email alerts?");

                builder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                SharedPreferences preferences=getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
                                String tokendata3=preferences.getString("token",null);
                                Log.i( "AttendanceClass: ","unmarkedEmplytoken"+tokendata3);
                                Long tsLong = System.currentTimeMillis();
                                String tsmillisec = tsLong.toString();
                                Log.i("currenttime", "onCreateView: "+tsmillisec);

                                // sending request
                                RequestParams params= new RequestParams();
                                params.put("",tokendata3);
                                params.put("timeStamp",tsmillisec);
                                String unmarkgmailUrl=getResources().getString(R.string.unamrkGmail);

                                GmailViewModel ViewModel= new GmailViewModel();
                                ViewModel.getRequest(unmarkgmailUrl,params,Attendence.this);
                                showProgressDailog("progressing",true,true);


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }

                        });
                AlertDialog alert = builder.create();
                alert.show();



            }

        });

       // mRecyclerView.setAdapter(adapter);
        EmployeeController controller= new EmployeeController();
        controller.getReference(new IData() {
            @Override
            public void requireData(ArrayList<EmployeeModelData> arrayInterfaceData) {
                mData=arrayInterfaceData;
                for (int i = 0; i <mData.size() ; i++) {
                    Log.i("hiiii",mData.get(i).getEmployee_company());
                }
                EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), mData);
               // mRecyclerView.setAdapter(adapter);
            }
        });
        return v;
    }


    @Override
    public void populateDataI(ArrayList<UnmarkEmplyModel> unmarkEmplyModel) {
        this.mArrayList = unmarkEmplyModel;
          EmployeeAdapter adapter = new EmployeeAdapter(mArrayList, getActivity());
        mRecyclerView.setAdapter(adapter);
        hideProgressDialog();
    }

    @Override
    public void gmailInterface(GmailModel gmailModel) {
        gmailModel.getmStatus();
        String message = gmailModel.getmMessage();
        Log.i("abcd", "populateData: "+ message);
        Log.i("abcd", "populateData: "+ gmailModel.getmStatus());
        Log.i("abcd", "populateData: "+ gmailModel.getmMessage());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        Log.i("abcd", "populateData:35454564 "+ message);
        builder.setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        hideProgressDialog();

    }


    public Fragment newInstance(String unmark, int totalcalemp, int month , int year) {
        Attendence frag= new Attendence();
        Bundle b= new Bundle();
        b.putString("unmark",unmark);
        b.putInt("totalcalemp",totalcalemp);
        b.putInt("month",month);
        b.putInt("year",year);
        Log.i("Attendance", "newInstance: "+month);
        Log.i("Attendance", "newInstance: "+year);

        frag.setArguments(b);
        return frag;
    }
    public void showProgressDailog(String message,boolean inderminate,boolean isCancelable){
        Log.i(TAG, "showProgressDailog: ");
        mProgressDialog.setIndeterminate(inderminate);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.show();

    }

    public void hideProgressDialog() {
        Log.i(TAG, "hideProgressDialog: ");
        mProgressDialog.dismiss();
    }
}