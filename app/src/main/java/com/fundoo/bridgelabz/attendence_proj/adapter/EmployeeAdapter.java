package com.fundoo.bridgelabz.attendence_proj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.model.AttendanceSumryModel;
import com.fundoo.bridgelabz.attendence_proj.model.EmployeeModelData;
import com.fundoo.bridgelabz.attendence_proj.model.UnmarkEmplyModel;

import java.util.ArrayList;

import static android.R.id.list;
import static android.content.ContentValues.TAG;


/**
 * Created by bridgelabz on 15/12/16.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<EmployeeModelData> mData;
    private ArrayList<UnmarkEmplyModel> mArraylist;

    public EmployeeAdapter(Context context, ArrayList<EmployeeModelData> data) {
        this.mData = data;
        this.mContext = context;
    }

    //  private ArrayList<EmployeeModelData> mDData;


    public EmployeeAdapter(ArrayList<UnmarkEmplyModel> arrayList, Context context) {
        this.mContext = context;
        this.mArraylist = arrayList;
        Log.i("GridCalendarView", "Gerrffdgbfdh: ......."+mArraylist.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_employee, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //data setting from firebase
       /* EmployeeModelData modeldata = mData.get(position);
        holder.email.setText(modeldata.getEmployee_email());
        holder.name.setText(modeldata.getEmployee_name());
        holder.profile.setText(modeldata.getEmployee_profile());
        holder.mob.setText(modeldata.getEmployee_mob_no());
        holder.company.setText(modeldata.getEmployee_company());
        Toast.makeText(mContext, "display", Toast.LENGTH_SHORT).show();*/
        //  holder.mTextview.setText(teamModel.getOwner());
        //String name= (String) mData.get(position);
        // holder.profile.setText(name);

        //restapi call
        UnmarkEmplyModel model= mArraylist.get(position);
        holder.email.setText(model.getmEmailId());
        Log.i(TAG, "onBindViewHolder: ");holder.email.setText(model.getmEmailId());
      // for (int i = 0; i <mArraylist.size()-1 ; i++) {
            holder.name.setText(model.getmEmployeeName());
            holder.profile.setText(model.getmEmployeeStatus());
            holder.company.setText(model.getmCompany());
            holder.mob.setText(model.getmMobile());
            holder.email.setText(model.getmEmailId());
       // }

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getCount value arraylist:"+mArraylist.size());
        return mArraylist.size()-1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email, name, company, mob, profile;

        public MyViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.email);
            name = (TextView) itemView.findViewById(R.id.name);
            company = (TextView) itemView.findViewById(R.id.company);
            mob = (TextView) itemView.findViewById(R.id.mobile_no);
            profile = (TextView) itemView.findViewById(R.id.profile);

        }
    }
}
