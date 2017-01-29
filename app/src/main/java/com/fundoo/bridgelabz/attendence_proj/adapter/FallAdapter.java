package com.fundoo.bridgelabz.attendence_proj.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.model.EmployeeModelData;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by bridgelabz on 17/1/17.
 */

public class FallAdapter extends RecyclerView.Adapter<FallAdapter.MyViewHolder> {

private ArrayList<UnmarkEmplyModel> mArrayList;
    private Context context;

    public FallAdapter(ArrayList<UnmarkEmplyModel> mArrayList) {
        this.mArrayList = mArrayList;
    }

    public FallAdapter(ArrayList<UnmarkEmplyModel> mArrayList, Context activity) {

        this.mArrayList = mArrayList;
        this.context = activity;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fallout_cardview, parent, false);
        return new FallAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // UnmarkEmplyModel model= mArraylist.get(position);
        UnmarkEmplyModel model=mArrayList.get(position);
        holder.email.setText(model.getmEmailId());
        Log.i(TAG, "onBindViewHolder: ");holder.email.setText(model.getmEmailId());
        // for (int i = 0; i <mArraylist.size()-1 ; i++) {
        holder.name.setText(model.getmEmployeeName());
        holder.profile.setText(model.getmEmployeeStatus());
        holder.company.setText(model.getmCompany());
        holder.mob.setText(model.getmMobile());
        holder.email.setText(model.getmEmailId());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size()-1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email, name, company, mob, profile;

        public MyViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.email_fall);
            name = (TextView) itemView.findViewById(R.id.name_fall);
            company = (TextView) itemView.findViewById(R.id.company_fall);
            mob = (TextView) itemView.findViewById(R.id.mobile_no_fall);
            profile = (TextView) itemView.findViewById(R.id.profile_fall);

        }
    }
}
