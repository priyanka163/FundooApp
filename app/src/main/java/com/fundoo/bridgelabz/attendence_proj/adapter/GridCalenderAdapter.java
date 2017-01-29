package com.fundoo.bridgelabz.attendence_proj.adapter;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.controller.AttendSummryCntrl;
import com.fundoo.bridgelabz.attendence_proj.model.AttendanceSumryModel;
import com.fundoo.bridgelabz.attendence_proj.view.Attendence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static android.content.ContentValues.TAG;

//import android.support.v4.app.FragmentManager;

/**
 * Created by bridgelabz on 15/12/16.
 */


public class GridCalenderAdapter extends BaseAdapter implements View.OnClickListener {
    String mCount1;
    int mYear;
    int mMonth;
    FragmentManager fragmentManager;
    Attendence mAttendence;
    ArrayList<AttendanceSumryModel> mArrayList = new ArrayList<>();
    ProgressDialog mProgressDialog;
    private Context mContext;
    private List<String> list;
    private String[] months1;
    private int[] daysOfMonth;
    private int mDaysInMonth;
    private TextView mGridcell;
    private TextView mTextMark;
    private int currentDayOfMonth;

    //constructor
    public GridCalenderAdapter(Context context, ArrayList<AttendanceSumryModel> arrayList, int month, int year, FragmentManager fragmentManager, Attendence attendence) {
        super();
        this.mContext = context;
        this.mYear = year;
        this.mMonth = month;
        this.list = new ArrayList<String>();
        this.fragmentManager = fragmentManager;
        this.mAttendence = attendence;
        this.mArrayList = arrayList;
        // Print Month
        printMonth(mMonth, mYear);
        Log.i("...... year", "" +"...."+ mArrayList.size());
        sortList(mArrayList);
    }

    private void sortList(ArrayList<AttendanceSumryModel> arrayList) {
        ArrayList<Integer> intDay = new ArrayList<>();
        //Log.i("sort data ", "sortListData: "+arrayList.get(0));
        if (arrayList.size() != 0 && arrayList != null) {

            for (int i = 0; i < arrayList.size(); i++) {
                int model = arrayList.get(i).getDay();
                Log.i("get days", "sortList: "+model);
                if (arrayList.get(i).getDay() !=0) {

                }
                intDay.add(model);
            }
            Collections.sort(intDay);
            for (int i = 1; i < intDay.size(); i++) {
                Log.i("sort data ", "sortList: " + intDay.get(i));
            }
        }
    }

    private void printMonth(int mm, int yy) {
        int trailingSpaces = 0;
        int currentMonth = mm - 1;
        int prevMonth = 0;
        months1 = mContext.getResources().getStringArray(R.array.months);
        daysOfMonth = mContext.getResources().getIntArray(R.array.daysOfMonth);
        mDaysInMonth = getNumberOfDaysOfMonth(currentMonth);


        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);

        if (currentMonth == 11) {
            prevMonth = currentMonth - 1;
        } else if (currentMonth == 0) {
            prevMonth = 11;
        } else {
            prevMonth = currentMonth - 1;
        }

        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        trailingSpaces = currentWeekDay;

        if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
            ++mDaysInMonth;
        }

        // Trailing Month days
        //25-WHITE-December-2016
        for (int i = 0; i < trailingSpaces; i++) {
            //list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-"+ getMonthAsString(prevMonth) + "-" + prevYear);
            list.add(" " + "-" + " " + "-" + " " + "-" + " ");
        }

        // Current Month Days
        for (int i = 1; i <= mDaysInMonth; i++) {
            if (i == getCurrentDayOfMonth())
                list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);

            else
                list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
            // Log.i("current", list.toString());
        }

        // Leading Month days
        for (int i = 0; i < list.size() % 7; i++) {
            //list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
            list.add(" " + "-" + " " + "-" + " " + "-" + " ");
        }
    }


    @Override
    public int getCount() {
        Log.i(TAG, "getCount: dataDetail" + list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
/*int a=mArrayList.get(position).getDay()
        Collections.sort(mArrayList.get(position).getDay());*/

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_grid_calendar, parent, false);
        }

        // Get a reference to the Day gridcell
        mGridcell = (TextView) row.findViewById(R.id.calendar_day_gridcell);
        mTextMark = (TextView) row.findViewById(R.id.text_mark);

        // ACCOUNT FOR SPACING
        // 25-WHITE-December-2016
        String[] day_color = list.get(position).split("-");
        String theday = day_color[0];
        String themonth = day_color[2];
        String theyear = day_color[3];

        // Set the Day GridCell
        mGridcell.setText(theday);
        try {
            if (!theday.equals(" ")) {
                AttendanceSumryModel model = mArrayList.get(position);
                if (model.getUnmarked().equals("-")) {
                    mTextMark.setTextColor(Color.GREEN);
                } else {
                    mTextMark.setTextColor(Color.RED);

                }

                mTextMark.setText(model.getUnmarked() + "/" + mArrayList.get(mArrayList.size() - 1).getmTotalEmplopyee());
                final String unmark = mArrayList.get(position).getUnmarked();
                final int totalcalemp = mArrayList.get(mArrayList.size() - 1).getmTotalEmplopyee();
                //  AttendanceSumryModel model= mArrayList.get(position);
                Log.i("GridCalendarView", "getView: .day......" + mArrayList.get(position).getDay());
                Log.i("GridCalendarView", "getView: .unmark......" + mArrayList.get(position).getUnmarked());
                Log.i("GridCalendarView", "getView: .totalemp......" + mArrayList.get(mArrayList.size() - 1).getmTotalEmplopyee());
                Log.i("GridCalendarView", "getView: .timestamp......" + mArrayList.get(mArrayList.size() - 1).getmTimeStamp());


                mGridcell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mCount1 = mTextMark.getText().toString();
                        Log.i("GridCalView", "onClick: ............................." + mCount1);
                        Toast.makeText(mContext, mCount1, Toast.LENGTH_SHORT).show();
                        //dont use new to transfer data from fragmentll
                        fragmentManager.beginTransaction().replace(R.id.frame, mAttendence.newInstance(unmark, totalcalemp, mMonth, mYear)).commit();

                        //calling controller
                        AttendSummryCntrl unmarkedcontroller = new AttendSummryCntrl(mContext);

                    }
                });

            }
            mGridcell.setTag(theday + "-" + themonth + "-" + theyear);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    private String getMonthAsString(int i) {
        return months1[i];
    }

    private int getNumberOfDaysOfMonth(int i) {
        if (i == 1) {
            int a = leapYear(mYear);
            if (a == 29) {
                Log.i("if loop", "" + a);
                return daysOfMonth[12];
            } else
                return daysOfMonth[i];

        } else
            return daysOfMonth[i];
    }

    public int getCurrentDayOfMonth() {
        return currentDayOfMonth;
    }

    //leap year
    public int leapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + " is a leap year");
            Log.i("leap year", "" + daysOfMonth[12]);
            return daysOfMonth[12];

        } else
            System.out.println(year + " is not a leap year");
        Log.i("not a leap year", "" + daysOfMonth[1]);
        return daysOfMonth[1];
    }

    @Override
    public void onClick(View view) {

    }
}