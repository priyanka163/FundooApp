package com.fundoo.bridgelabz.attendence_proj.controller;

import com.fundoo.bridgelabz.attendence_proj.Interface.IData;

/**
 * Created by bridgeit on 6/1/17.
 * * Purpose:
 * It is data controller in mvvm arch.
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models and Views .Controller Will Get The Data From The Server
 * And Will Pass Data To viewmodel.
 * It will interact with rest service to get data with the cloud
 * It encapsulates content info model
 * This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer data model.
 */

public class EmployeeController {
    public void getReference(IData hiiii) {

    }


   /* FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = database.getReference("data");

    public DatabaseReference getReference(final IData dataInterface) {

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
                for (int i = 0; i < arrayList.size(); i++) {
                    Log.i("hi", arrayList.get(i).getEmployee_company());

                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));
        return reference;
    }*/
}

