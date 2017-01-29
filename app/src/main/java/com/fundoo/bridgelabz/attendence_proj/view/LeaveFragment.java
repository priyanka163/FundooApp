package com.fundoo.bridgelabz.attendence_proj.view;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
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
import com.fundoo.bridgelabz.attendence_proj.Interface.ILeave;
import com.fundoo.bridgelabz.attendence_proj.Interface.ILeaveGmail;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.adapter.EmployeeAdapter;
import com.fundoo.bridgelabz.attendence_proj.adapter.LeaveAdapter;
import com.fundoo.bridgelabz.attendence_proj.controller.EmployeeController;
import com.fundoo.bridgelabz.attendence_proj.model.EmployeeModelData;
import com.fundoo.bridgelabz.attendence_proj.model.GmailModel;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.LeaveViewModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.LeavegmailViewmodel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 16/1/17.
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


public class LeaveFragment extends Fragment implements ILeave,ILeaveGmail {

    RecyclerView mRecyclerView;
    //firebase comment
    ArrayList<EmployeeModelData> mData;
    ImageView mBackicon, mGmailbutton;
    TextView mCount;
    TextView mSetDate;
    TextView mDatedisplay;
    ArrayList<UnmarkEmplyModel> mArrayList;
    private static final String TAG = "LeaveFragment";
    ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leave_recyclerview, container, false);
        mProgressDialog=new ProgressDialog(getActivity());
        mCount= (TextView) v.findViewById(R.id.count_leave);
        mSetDate = (TextView) v.findViewById(R.id.dateview_leave);
        //reading saved data from sharedpreference

     String leave = getArguments().getString("leave");
         int totalEmp = getArguments().getInt("total");
        mCount.setText(leave+"/"+totalEmp);

        SharedPreferences preferences=getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokendataH=preferences.getString("token",null);
        Log.i( "LeaveCls: ","unmarkedEmplytoken"+tokendataH);
        Long tsLong = System.currentTimeMillis();
        String tsmillisec = tsLong.toString();
        Log.i("LeaveFragment", "onCreateView: "+tsmillisec);
        String Leaveurl= getResources().getString(R.string.leave);


        // sending request
        RequestParams params= new RequestParams();
        //params.put("",tokendata3);
        params.put("timeStamp",tsmillisec);
        LeaveViewModel leaveViewModel=new LeaveViewModel();
       // UnmarkedViewModel unmarkedViewModel= new UnmarkedViewModel();
        leaveViewModel.getRequest(Leaveurl,params,tokendataH,this);
        showProgressDailog("plz wait its loading",true,true);
        // params.put();
       /* Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        Log.i("date",""+date+":"+month+":"+year);*/
      //  mCount.setText("0/100");
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_leave);
        mDatedisplay= (TextView) v.findViewById(R.id.dateview_leave);
        mBackicon = (ImageView) v.findViewById(R.id.id_backicon_leave);
        mGmailbutton = (ImageView) v.findViewById(R.id.gmail_leave);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mlayoutManager);

        // firebase comment
        mData = new ArrayList<EmployeeModelData>();

        try {
            String textMonth = getArguments().getString("month");
            int textYear = getArguments().getInt("year");
            mDatedisplay.setText(textMonth+" ,"+textYear);
            Log.i("Attendance", "onCreateView: "+textMonth+" ,"+textYear);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mBackicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  getFragmentManager().beginTransaction().replace(R.id.frame, new DashBoardActivity()).commit();
                Intent i = new Intent(getActivity(), DashBoardActivity.class);
                getActivity().startActivity(i);
                Toast.makeText(getActivity(), "backfromLeave", Toast.LENGTH_SHORT).show();
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
                                String tokeninHead=preferences.getString("token",null);
                                Log.i( "LeaveClass: ","unmarkedEmplytoken"+tokeninHead);
                                Long tsLong = System.currentTimeMillis();
                                String tsmillisec = tsLong.toString();
                                Log.i("LeaveClscurrenttime", "onCreateView: "+tsmillisec);
                                String leavegmailUrl=getResources().getString(R.string.Leavegmail);
                                // sending request
                                RequestParams params= new RequestParams();
                               // params.put("",tokendata3);
                                params.put("timeStamp",tsmillisec);
                                LeavegmailViewmodel ViewModel= new LeavegmailViewmodel();
                                ViewModel.getRequest(leavegmailUrl,params,tokeninHead,LeaveFragment.this);
                                showProgressDailog("plz wait its loading",true,true);


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


        // EmployeeAdapter adapter = null;


        //  FirebaseAsynctask myAsyncTask=new  FirebaseAsynctask(getActivity());

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

        Log.i("leaveleave", "adapter: "+mArrayList);
        Log.i("leaveleave", "adapter: "+mArrayList.size());
        LeaveAdapter adapter = new LeaveAdapter(mArrayList, getActivity());
        mRecyclerView.setAdapter(adapter);
        hideProgressDialog();
    }

   // @Override
    public void gmailInterface(GmailModel gmailModel) {
        gmailModel.getmStatus();
        String message = gmailModel.getmMessage();
        Log.i("hm", "populateData: "+ message);
        Log.i("hm", "populateData: "+ gmailModel.getmStatus());
        Log.i("hm", "populateData: "+ gmailModel.getmMessage());

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

    public static LeaveFragment newInstance(String leavecard, int totalEmp) {
        LeaveFragment leaveFragment = new LeaveFragment();
        Log.i(TAG, "newInstance: LeaveFrag "+leavecard);
        Bundle bundle = new Bundle();
        bundle.putString("leave",leavecard);
        bundle.putInt("total",totalEmp);
        leaveFragment.setArguments(bundle);
        return leaveFragment;
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