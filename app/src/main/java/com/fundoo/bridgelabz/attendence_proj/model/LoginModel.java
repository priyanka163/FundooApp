package com.fundoo.bridgelabz.attendence_proj.model;

/**
 * Created by bridgelabz on 5/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class. */

public class LoginModel {
    private String mToken;
    private String mMessage;
    private int mStatus;


    public LoginModel() {
    }

    public LoginModel(String mToken, String mMessage, int mStatus) {
        this.mToken = mToken;
        this.mMessage = mMessage;
        this.mStatus = mStatus;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
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
