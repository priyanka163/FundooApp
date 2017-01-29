package com.fundoo.bridgelabz.attendence_proj.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.fundoo.bridgelabz.attendence_proj.Interface.ISummary;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.adapter.GridCalenderAdapter;
import com.fundoo.bridgelabz.attendence_proj.model.AttendanceSumryModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.AttndSumryViewModel;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

public class MonthYearPickerDialog extends DialogFragment {
    TextView mDatetextview;
  //  private static final int MAX_YEAR = 2099;
    private static final int MIN_YEAR = 2016;
    private DatePickerDialog.OnDateSetListener mListener;
    private Context mContext;
    private GridView mCalendarView;

    public MonthYearPickerDialog() {
    }

    //
    public void setListener(Context context, DatePickerDialog.OnDateSetListener listener) {
        this.mListener = listener;
        mContext = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

       // LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_attendance_summary, null);

        View dialog = inflater.inflate(R.layout.date_picker_dialog, null);
        final NumberPicker monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        String[] monthNames ;
        monthNames=getResources().getStringArray(R.array.months);
        //monthPicker.setMinValue(1);
        /// monthPicker.setMaxValue(12);
        //it converts intiger to months
        monthPicker.setDisplayedValues(monthNames);
        //
        monthPicker.setMaxValue(monthNames.length - 1);
      //  monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(MIN_YEAR);
        yearPicker.setMaxValue(year);

        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        AtndSummaryActivity ldf = new AtndSummaryActivity(mContext);
                        Bundle args = new Bundle();
                        args.putInt("month", monthPicker.getValue());
                        args.putInt("year", yearPicker.getValue());

                        final int mMonth=monthPicker.getValue();
                        final int mYear=yearPicker.getValue();
                       // ldf.setArguments(args);
                        Log.i("monthyear", "onClick: "+mMonth+mYear);


                        SharedPreferences preferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
                        String tokenHeader = preferences.getString("token", null);
                        Log.i("CalenderFrag: ", "newtoken" + tokenHeader);
                   /* Long tsLong = System.currentTimeMillis();
                    String tsmillisec = tsLong.toString();*/

                        int day, second, minute, hour;
                        long epoch=0;
                        GregorianCalendar calendar = new GregorianCalendar();

                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        second = calendar.get(Calendar.SECOND);
                        minute = calendar.get(Calendar.MINUTE);
                        hour = calendar.get(Calendar.HOUR);

                        try {
                            epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                                    .parse(monthPicker.getValue()+"/"+day+"/"+yearPicker.getValue()+" "+hour+":"+minute+":"+second)
                                    .getTime();
                            Log.i("Atten..", "onClick: ..epochfgfhbgnjhnvbn....."+epoch);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // sending token and timestamp from view to viewmodel
                        RequestParams params = new RequestParams();
                       // params.put("", tokendata2);
                        Log.i("Atten..", "onClick: ..epochfgfhbgnjhnvbn..test..."+epoch);
                        params.put("timeStamp", epoch);
                        String attendanceSumryurl=getResources().getString(R.string.Dasboardcontrller);
                        AttndSumryViewModel sviewmodel = new AttndSumryViewModel();
                        sviewmodel.getData(mContext,attendanceSumryurl, params, tokenHeader, new ISummary() {
                            @Override
                            public void dataView(ArrayList<AttendanceSumryModel> model) {
                                Log.i("saxcsadref", "dataView: "+model);

                                for (int i = 0; i <model.size() ; i++) {
                                    String model1 = model.get(i).getUnmarked();
                                    Log.i(TAG, "dataView MonthtrPicker:1   "+model1);
                                }

                                mCalendarView = (GridView) view.findViewById(R.id.calendar);
                                GridCalenderAdapter adapter = new GridCalenderAdapter(getActivity(), model, mMonth, mYear, getFragmentManager(), new Attendence());
                                mCalendarView.setAdapter(adapter);

                            }
                        });

                       // getFragmentManager().beginTransaction().add(R.id.frame, ldf).commit();


                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonthYearPickerDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}

