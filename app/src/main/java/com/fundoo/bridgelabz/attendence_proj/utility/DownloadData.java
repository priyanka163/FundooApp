package com.fundoo.bridgelabz.attendence_proj.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fundoo.bridgelabz.attendence_proj.Interface.IData;
import com.fundoo.bridgelabz.attendence_proj.model.EmployeeModelData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 2/1/17.
 */

public class DownloadData extends AsyncTask<String,Void,ArrayList<EmployeeModelData>> {
    ProgressDialog mProgressDialog;
    Context mContext;
    DatabaseReference mRef;

    public DownloadData(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Create a progressdialog
        mProgressDialog = new ProgressDialog(mContext);
        // Set progressdialog title
        mProgressDialog.setTitle("Download Image Tutorial");
        // Set progressdialog message
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog
        mProgressDialog.show();
        //AsyncTask
    }

    @Override
    protected ArrayList<EmployeeModelData> doInBackground(String... URL) {

        String imageURL = URL[0];

        final ArrayList<EmployeeModelData> arrayList = new ArrayList<EmployeeModelData>();
      /*  try {
            // Download Image from URL
            // Get a reference to our posts
            FirebaseDatabase database= FirebaseDatabase.getInstance();

            DatabaseReference reference=database.getReference(imageURL);


            // new MyTask();


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<ArrayList<EmployeeModelData>> ref = new GenericTypeIndicator<ArrayList<EmployeeModelData>>() {
                    };

                    arrayList.addAll(dataSnapshot.getValue(ref));

                    // demoInterface.requireData(arrayList);
                    for (int i = 0; i < arrayList.size(); i++) {
                        Log.i("morning", arrayList.get(i).getEmployee_company());
                    }


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }*/




        getReference(new IData() {
            @Override
            public void requireData(ArrayList<EmployeeModelData> arrayInterfaceData) {
                for (int i = 0; i < arrayInterfaceData.size(); i++) {
                    Log.i("morning", arrayInterfaceData.get(i).getEmployee_company());
                }
            }
        });

        return arrayList;
    }



    public DatabaseReference getReference(final IData dataInterface) {
        FirebaseDatabase database= FirebaseDatabase.getInstance();

        DatabaseReference reference=database.getReference("data");

        reference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<EmployeeModelData>> indicator =
                        new GenericTypeIndicator<ArrayList<EmployeeModelData>>() {
                        };
                ArrayList<EmployeeModelData> arrayList = new ArrayList<EmployeeModelData>();
                arrayList.addAll(dataSnapshot.getValue(indicator));
                dataInterface.requireData(arrayList);
                // IData.requireData(arrayList);
               /* for (int i = 0; i < arrayList.size(); i++) {
                    Log.i("hi", arrayList.get(i).getEmployee_company());

                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));
        return reference;
    }

    @Override
    protected void onPostExecute(ArrayList<EmployeeModelData> result) {

        mProgressDialog.dismiss();
        for (int i = 0; i < result.size(); i++) {
            Log.i("employee data", result.get(i).getEmployee_company());


        }
    }
}