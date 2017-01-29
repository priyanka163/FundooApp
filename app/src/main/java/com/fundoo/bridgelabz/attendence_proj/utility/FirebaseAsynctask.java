package com.fundoo.bridgelabz.attendence_proj.utility;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.R.attr.data;
import static android.content.ContentValues.TAG;

/**
 * Created by bridgelabz on 2/1/17.
 */
public class FirebaseAsynctask {/*extends AsyncTask<String,Void,ArrayList<>> {

    DataSnapshot mDataSnapshot;

    ValueEventListener mListener;

    DatabaseReference ref2;

    public FirebaseAsynctask(Context context) {

    }

    @Override
    protected Void doInBackground(String... params) {
        String para1 = params[0];
        String para2 = params[1];

        performCalculations(para1,para2);

        return null;
    }

    public void performCalculations(String para1, String para2) {

        TaskCompletionSource<DataSnapshot> taskCompletionSource = new TaskCompletionSource<>();

        ref2 = FirebaseDatabase.getInstance().getReference("data");

        ref2.addListenerForSingleValueEvent(new MyValueEventListener(taskCompletionSource));

        try {
            // Get the data first time before you proceed
         DataSnapshot   dataSnapshot = Tasks.await(taskCompletionSource.getTask());

        } catch (ExecutionException | InterruptedException e) {
            Log.v(TAG, "Exception Catched");
            e.printStackTrace();
        }

        // Register the value event listener to keep the data updated
        mListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDataSnapshot = dataSnapshot;
                Log.v(TAG, "Data updated");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref2.addValueEventListener(mListener);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(para1);

        // Run the transaction here
        ref.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

                // Perform some stuff based on mDataSanpshot



               // mutableData.setValue();
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    Log.d(TAG, "onComplete: Error: " + databaseError.getMessage());
                } else {
                    Log.d(TAG, "onComplete: Transaction was successful");
                    ref2.removeEventListener(mListener);
                }
            }

        });
    }

    // My value event listener here ...



private class MyValueEventListener implements ValueEventListener {

    private final TaskCompletionSource<DataSnapshot> taskSource;

    public MyValueEventListener(TaskCompletionSource<DataSnapshot> taskSource) {
        this.taskSource = taskSource;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.v(TAG, "Looks like MyValueEventListener received the data");
        taskSource.setResult(dataSnapshot);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.v(TAG, "Looks like MyValueEventListener thrown an error");
        taskSource.setException(databaseError.toException());
    }

}*/

}