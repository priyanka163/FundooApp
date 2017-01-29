package com.fundoo.bridgelabz.attendence_proj.model;

/**
 * Created by bridgelabz on 9/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class. */

public class GmailModel {
    private long mTimeStamp;
    private String mMessage;
    private int mStatus;

    public GmailModel() {
    }

    public GmailModel(int mTimeStamp, String mMessage, int mStatus) {
        this.mTimeStamp = mTimeStamp;
        this.mMessage = mMessage;
        this.mStatus = mStatus;
    }

    public long getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }
}
