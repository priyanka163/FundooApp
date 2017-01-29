package com.fundoo.bridgelabz.attendence_proj.model;

/**
 * Created by bridgelabz on 16/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class. */

public class DashBoardModel {

    private long mTimeStamp;
    private int mMarked;
    private String mUnmarked;
    private int mTotalEmployee;
    private int mFalloutEmployee;
    private String mLeave;


    public DashBoardModel() {
    }

    public DashBoardModel(long mTimeStamp, int mMarked, String mUnmarked, int mTotalEmployee, int mFalloutEmployee, String mLeave) {
        this.mTimeStamp = mTimeStamp;
        this.mMarked = mMarked;
        this.mUnmarked = mUnmarked;
        this.mTotalEmployee = mTotalEmployee;
        this.mFalloutEmployee = mFalloutEmployee;
        this.mLeave = mLeave;
    }

    public long getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public int getmMarked() {
        return mMarked;
    }

    public void setmMarked(int mMarked) {
        this.mMarked = mMarked;
    }

    public String getmUnmarked() {
        return mUnmarked;
    }

    public void setmUnmarked(String mUnmarked) {
        this.mUnmarked = mUnmarked;
    }

    public int getmTotalEmployee() {
        return mTotalEmployee;
    }

    public void setmTotalEmployee(int mTotalEmployee) {
        this.mTotalEmployee = mTotalEmployee;
    }

    public int getmFalloutEmployee() {
        return mFalloutEmployee;
    }

    public void setmFalloutEmployee(int mFalloutEmployee) {
        this.mFalloutEmployee = mFalloutEmployee;
    }

    public String getmLeave() {
        return mLeave;
    }

    public void setmLeave(String mLeave) {
        this.mLeave = mLeave;
    }
}
