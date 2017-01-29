package com.fundoo.bridgelabz.attendence_proj.view;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.Interface.ISummary;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.adapter.GridCalenderAdapter;
import com.fundoo.bridgelabz.attendence_proj.model.AttendanceSumryModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.AttndSumryViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class AtndSummaryActivity extends AppCompatActivity implements ISummary {

    ProgressDialog progressDialog;
    Context mContext;
    String[] monthNames;
    private GridView mCalendarView;
    private GridCalenderAdapter adapter;
    private Calendar mCalendar;
    private int mDate, mMonth, mYear;
    private ImageView mDownarrow;
    private TextView mTxtdate;
    ArrayList<AttendanceSumryModel> arrayList = new ArrayList<>();
    String value;
    long epoch;

    private String[] months;

    DatePickerDialog.OnDateSetListener pick;

    public AtndSummaryActivity(Context context, ProgressDialog progressDialog) {
        this.mContext=context;
        this.progressDialog = progressDialog;
    }


    public AtndSummaryActivity(Context mContext) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atnd_summary);

        months=getResources().getStringArray(R.array.months);
            SharedPreferences preferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
            String tokenHeader = preferences.getString("token", null);
            Log.i("CalenderFrag: ", "newtoken" + tokenHeader);
            Long tsLong = System.currentTimeMillis();
            String tsmillisec = tsLong.toString();
            String attendanceSumryurl=getResources().getString(R.string.AttendSummryCntrl);

//      sending token and timestamp from view to viewmodel
            RequestParams params = new RequestParams();
            // params.put("", tokendata2);
            params.put("timeStamp", tsmillisec);
            Log.i("AttenSum: ", "timeStamp" + tsmillisec);
            AttndSumryViewModel sviewmodel = new AttndSumryViewModel();
            sviewmodel.getData(mContext,attendanceSumryurl,params,tokenHeader, this);

            //get current month and year
            mCalendar = Calendar.getInstance(Locale.getDefault());
            mMonth = mCalendar.get(Calendar.MONTH) + 1;
            mYear = mCalendar.get(Calendar.YEAR);
            mDate = mCalendar.get(Calendar.DATE);

            mCalendarView = (GridView)findViewById(R.id.calendar);
            mDownarrow = (ImageView)findViewById(R.id.image_down_arrow);
            mTxtdate = (TextView)findViewById(R.id.text_mnth_year);
            mTxtdate.setText( months[mMonth - 1] + " , " + mYear);
       /* adapter = new GridCalenderAdapter(getActivity(), arrayList, mMonth, mYear, getFragmentManager(), new Attendence());
        mCalendarView.setAdapter(adapter);*/

            mDownarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MonthYearPickerDialog pd = new MonthYearPickerDialog();
                    pd.setListener(AtndSummaryActivity.this, pick);
                    pd.show(getFragmentManager(), "MonthYearPickerDialog");

                    Bundle bundle =new Bundle();
                    if (bundle != null) {
                        int getMonth = bundle.getInt("month", 0);
                        int getYear = bundle.getInt("year", 0);

                        // set data to textview
                        mTxtdate.setText(months[getMonth] + " ," + getYear);
                        String date = mTxtdate.getText().toString();
                        Toast.makeText(AtndSummaryActivity.this, date, Toast.LENGTH_SHORT).show();

                    }
                }
            });


            Bundle bundle = new Bundle();
            if (bundle != null) {
                int getMonth = bundle.getInt("month", 0);
                int getYear = bundle.getInt("year", 0);

                // setting data to textview
                mTxtdate.setText(months[getMonth] + " ," + getYear);
                String date = mTxtdate.getText().toString();
                Toast.makeText(AtndSummaryActivity.this, date, Toast.LENGTH_SHORT).show();

                //pass data from  this fragment to attendence
                Attendence attendence = new Attendence();
                Bundle data = new Bundle();
                String month = months[getMonth];
                data.putString("month", month);
                data.putInt("year", getYear);
                attendence.setArguments(data);
                Log.i("CalenderFrag", "onCreateView: " + months[getMonth]);
                Log.i("CalenderFrag", "onCreateView: " + getYear);



                // set adapter
                adapter = new GridCalenderAdapter(this, arrayList, getMonth + 1, getYear, getFragmentManager(), attendence);
                adapter.notifyDataSetChanged();
                mCalendarView.setAdapter(adapter);
            }


        }


        @Override
        public void dataView(ArrayList<AttendanceSumryModel> model) {

            this.arrayList = model;

            for (int i = 0; i <arrayList.size() ; i++) {
                String model1 = arrayList.get(i).getUnmarked();
                Log.i(TAG, "dataView xxxx: "+model1);
            }
            adapter = new GridCalenderAdapter(this, arrayList, mMonth, mYear, getFragmentManager(), new Attendence());
            mCalendarView.setAdapter(adapter);


            Log.i("CalView", "dataView: timeStamp  " + arrayList.get(arrayList.size() - 1).getmTimeStamp());
            for (int i = 0; i < model.size(); i++) {
                Log.i("CalView", "dataView: day  " + arrayList.get(i).getDay());
                int h=arrayList.get(i).getDay();

                //  Collections.sort( arrayList.get(i).getDay());
                //Log.i(TAG, "dataView: "+);

                Log.i("CalView", "dataView: unmarked  " + arrayList.get(i).getUnmarked());
            }
            Log.i("CalView", "dataView: totalemployee  " + arrayList.get(arrayList.size() - 1).getmTotalEmplopyee());

            if (progressDialog != null){
                progressDialog.dismiss();
            }

        }



    }



