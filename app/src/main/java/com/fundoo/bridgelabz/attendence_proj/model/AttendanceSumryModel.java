package com.fundoo.bridgelabz.attendence_proj.model;

/**
 * Created by bridgelabz on 8/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class. */

public class AttendanceSumryModel {

    private   long mTimeStamp;
    private   String mAttendance;
    private   int mTotalEmplopyee;
    private  int day;
    private  String unmarked;
    private  String absent;

    public AttendanceSumryModel(int day, String unmarked, String absent) {
        this.day = day;
        this.unmarked = unmarked;
        this.absent = absent;
    }

    public AttendanceSumryModel() {
    }

    public AttendanceSumryModel(int mTimeStamp, String mAttendance, int mTotalEmplopyee) {
        this.mTimeStamp = mTimeStamp;
        this.mAttendance = mAttendance;
        this.mTotalEmplopyee = mTotalEmplopyee;
    }


    public String getmAttendance() {
        return mAttendance;
    }

    public void setmAttendance(String mAttendance) {
        this.mAttendance = mAttendance;
    }

    public long getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public int getmTotalEmplopyee() {
        return mTotalEmplopyee;
    }

    public void setmTotalEmplopyee(int mTotalEmplopyee) {
        this.mTotalEmplopyee = mTotalEmplopyee;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getUnmarked() {
        return unmarked;
    }

    public void setUnmarked(String unmarked) {
        this.unmarked = unmarked;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }
}
