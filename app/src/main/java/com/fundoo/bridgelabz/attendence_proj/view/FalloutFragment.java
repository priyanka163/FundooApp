package com.fundoo.bridgelabz.attendence_proj.view;

import android.annotation.SuppressLint;
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
import com.fundoo.bridgelabz.attendence_proj.Interface.IFallout;
import com.fundoo.bridgelabz.attendence_proj.Interface.IFalloutGmail;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.adapter.EmployeeAdapter;
import com.fundoo.bridgelabz.attendence_proj.adapter.FallAdapter;
import com.fundoo.bridgelabz.attendence_proj.controller.EmployeeController;
import com.fundoo.bridgelabz.attendence_proj.model.EmployeeModelData;
import com.fundoo.bridgelabz.attendence_proj.model.GmailModel;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.FallgmailViewmodel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.FalloutViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

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


public class FalloutFragment extends Fragment implements IFallout, IFalloutGmail {
    RecyclerView mRecyclerView;
    //firebase comment
    ArrayList<EmployeeModelData> mData;
    ImageView mBackicon, mGmailbutton;
    TextView mCount;
    TextView mSetDate;
    TextView mDatedisplay;
    ArrayList<UnmarkEmplyModel> mArrayList;
    String mFalloutGmailUrl,mFallouturl;
    int falloutEmployee, totalemployee;
    ProgressDialog mProgressDialog;


    @SuppressLint("ValidFragment")
    public FalloutFragment(ProgressDialog progressDialog, int totalfalloutemp, int totalemp) {

        this.falloutEmployee = totalfalloutemp;
        this.totalemployee = totalemp;

    }
    public FalloutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fallout_recyclerview,container,false);
        mProgressDialog=new ProgressDialog(getActivity());

        mCount= (TextView) v.findViewById(R.id.count_fall);
        mSetDate = (TextView) v.findViewById(R.id.dateview_fall);

        //reading saved data from sharedpreference
        SharedPreferences preferences=getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokeninHeader=preferences.getString("token",null);
        Log.i( "AttendanceClass: ","unmarkedEmplytoken"+tokeninHeader);
        Long tsLong = System.currentTimeMillis();
        String tsmillisec = tsLong.toString();
        Log.i("currenttime", "onCreateView: "+tsmillisec);
        String fallouturl= getResources().getString(R.string.Fallout);

        // sending request
        RequestParams params= new RequestParams();
        //params.put("",tokendata3);
        params.put("timeStamp",tsmillisec);

        FalloutViewModel viewModel=new FalloutViewModel();
        viewModel.getRequest(fallouturl,params,tokeninHeader,this);
        showProgressDailog("progressing",true,true);
        Log.i("fallouturl", "onCreateView: "+fallouturl);
        mCount.setText(falloutEmployee+"/"+totalemployee);
       // mCount.setText("0/100");
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_fallout);
        mDatedisplay= (TextView) v.findViewById(R.id.dateview_fall);
        mBackicon = (ImageView) v.findViewById(R.id.id_backicon_fall);
        mGmailbutton = (ImageView) v.findViewById(R.id.gmail_fallout);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mlayoutManager);
        EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), mData);
       // mRecyclerView.setAdapter(adapter);
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
              // getFragmentManager().beginTransaction().replace(R.id.frame, new DashBoardActivity()).commit();
                Intent i = new Intent(getActivity(), DashBoardActivity.class);
                getActivity().startActivity(i);
                Toast.makeText(getActivity(), "backButtonFall", Toast.LENGTH_SHORT).show();
            }
        });
        mGmailbutton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                mFalloutGmailUrl=getResources().getString(R.string.FalloutGmail);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to send email alerts?");

                builder.setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                SharedPreferences preferences=getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
                                String tokeninHead=preferences.getString("token",null);
                                Log.i( "FalloutFragment: ","unmarkedEmplytoken"+tokeninHead);
                                Long tsLong = System.currentTimeMillis();
                                String tsmillisec = tsLong.toString();
                                Log.i("falloutcalss", "onCreateView: "+tsmillisec);

                                // sending request
                                RequestParams params= new RequestParams();
                               // params.put("",tokendata3);
                                params.put("timeStamp",tsmillisec);
                                FallgmailViewmodel ViewModel= new FallgmailViewmodel();
                                ViewModel.getRequest(mFalloutGmailUrl,params,tokeninHead,FalloutFragment.this);
                                //showing progressdialog by calling showprgdailog method
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
        Log.i(TAG, "FalloutContentI: "+mArrayList.size());
        FallAdapter adapter = new FallAdapter(mArrayList, getActivity());
        mRecyclerView.setAdapter(adapter);

            hideProgressDialog();


    }

  //  @Override
    public void gmailInterface(GmailModel gmailModel) {
        gmailModel.getmStatus();
        String message = gmailModel.getmMessage();
        Log.i("falloutstatus", "populateData: "+ message);
        Log.i("falloutstatus", "populateData: "+ gmailModel.getmStatus());
        Log.i("falloutmsg", "populateData: "+ gmailModel.getmMessage());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        Log.i("falloutmagSuccess", "populateData:35454564 "+ message);
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


    public static Fragment newInstance(int totalfalloutemp, int totalemp) {
        FalloutFragment fragment= new FalloutFragment();
        Log.i("leavefragm", "newInstance: "+totalfalloutemp);
        Bundle bundle= new Bundle();
        bundle.putInt("falloutemp",totalfalloutemp);
        bundle.putInt("totalemp",totalemp);
        fragment.setArguments(bundle);
        return fragment;

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