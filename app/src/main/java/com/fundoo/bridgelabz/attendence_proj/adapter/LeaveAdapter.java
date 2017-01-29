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
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;

import java.util.ArrayList;


/**
 * Created by bridgelabz on 16/1/17.
 */

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.MyVeiwHolder> {

    private ArrayList<UnmarkEmplyModel> mArrayList;
    private Context mContext;

    public LeaveAdapter() {
    }

    public LeaveAdapter(ArrayList<UnmarkEmplyModel> mArrayList) {
        this.mArrayList = mArrayList;
    }

    public LeaveAdapter(ArrayList<UnmarkEmplyModel> arrayList, Context context) {
        this.mArrayList = arrayList;
        this.mContext = context;
    }

    @Override
    public MyVeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_cardview, parent, false);
        return new LeaveAdapter.MyVeiwHolder(v);
    }

    @Override
    public void onBindViewHolder(MyVeiwHolder holder, int position) {
        UnmarkEmplyModel model = mArrayList.get(position);
       // holder.email.setText(model.getmEmailId());
        Log.i("gfdfhfgdt", "onBindViewHolder: ");
      //  holder.email.setText(model.getmEmailId());
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

    public class MyVeiwHolder extends RecyclerView.ViewHolder {
        TextView email, name, company, mob, profile;

        public MyVeiwHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.email_leave);
            name = (TextView) itemView.findViewById(R.id.name_leave);
            company = (TextView) itemView.findViewById(R.id.company_leave);
            mob = (TextView) itemView.findViewById(R.id.mobile_no_leave);
            profile = (TextView) itemView.findViewById(R.id.profile_leave);

        }
    }
}