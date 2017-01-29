package com.fundoo.bridgelabz.attendence_proj.model;

/**
 * Created by bridgelabz on 11/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.*/

public class UnmarkEmplyModel {
    private  String umarkedEmployee;
    private Long mTimeStamp;
    private  int mUnmarkedNumber;
    private  int mTotalEmployee;
    private String mEmployeeName;
    private String mEmployeeStatus;
    private String mCompany;
    private String mMobile;
    private String mEmailId;
    private String mBlStartDate;
    private String mCompanyJoinDate;
    private String mCompanyLeaveDate;
    private int mLeaveTaken;
    private String mEngineerId;
    private int mFalloutNumber;
    private int mEmployeeleave;


    public int getmEmployeeleave() {
        return mEmployeeleave;
    }

    public void setmEmployeeleave(int mEmployeeleave) {
        this.mEmployeeleave = mEmployeeleave;
    }







    public UnmarkEmplyModel() {
    }

    public UnmarkEmplyModel(String umarkedEmployee, Long timeStamp, int unmarkedNumber, int totalEmployee,int mEmployeeleave) {
        this.umarkedEmployee = umarkedEmployee;
        this.mTimeStamp = timeStamp;
        this.mUnmarkedNumber = unmarkedNumber;
        this.mTotalEmployee = totalEmployee;
        this.mEmployeeleave=mEmployeeleave;
    }

    public UnmarkEmplyModel(String mEmployeeStatus, String mEmployeeName, String mCompany,
                            String mMobile, String mEmailId, String mBlStartDate,
                            String mCompanyJoinDate, int mLeaveTaken, String mCompanyLeaveDate,
                            String mEngineerId) {
        this.mEmployeeStatus = mEmployeeStatus;
        this.mEmployeeName = mEmployeeName;
        this.mCompany = mCompany;
        this.mMobile = mMobile;
        this.mEmailId = mEmailId;
        this.mBlStartDate = mBlStartDate;
        this.mCompanyJoinDate = mCompanyJoinDate;
        this.mLeaveTaken = mLeaveTaken;
        this.mCompanyLeaveDate = mCompanyLeaveDate;
        this.mEngineerId = mEngineerId;
    }

    public UnmarkEmplyModel(int mFalloutNumber) {
        this.mFalloutNumber = mFalloutNumber;
    }

    public String getUmarkedEmployee() {
        return umarkedEmployee;
    }

    public void setUmarkedEmployee(String umarkedEmployee) {
        this.umarkedEmployee = umarkedEmployee;
    }

    public Long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.mTimeStamp = timeStamp;
    }

    public int getUnmarkedNumber() {
        return mUnmarkedNumber;
    }

    public void setUnmarkedNumber(int unmarkedNumber) {
        this.mUnmarkedNumber = unmarkedNumber;
    }

    public int getTotalEmployee() {
        return mTotalEmployee;
    }

    public void setTotalEmployee(int totalEmployee) {
        this.mTotalEmployee = totalEmployee;
    }

    public String getmEmployeeName() {
        return mEmployeeName;
    }

    public void setmEmployeeName(String mEmployeeName) {
        this.mEmployeeName = mEmployeeName;
    }

    public String getmEmployeeStatus() {
        return mEmployeeStatus;
    }

    public void setmEmployeeStatus(String mEmployeeStatus) {
        this.mEmployeeStatus = mEmployeeStatus;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getmEmailId() {
        return mEmailId;
    }

    public void setmEmailId(String mEmailId) {
        this.mEmailId = mEmailId;
    }

    public String getmBlStartDate() {
        return mBlStartDate;
    }

    public void setmBlStartDate(String mBlStartDate) {
        this.mBlStartDate = mBlStartDate;
    }

    public String getmCompanyJoinDate() {
        return mCompanyJoinDate;
    }

    public void setmCompanyJoinDate(String mCompanyJoinDate) {
        this.mCompanyJoinDate = mCompanyJoinDate;
    }

    public String getmCompanyLeaveDate() {
        return mCompanyLeaveDate;
    }

    public void setmCompanyLeaveDate(String mCompanyLeaveDate) {
        this.mCompanyLeaveDate = mCompanyLeaveDate;
    }

    public int getmLeaveTaken() {
        return mLeaveTaken;
    }

    public void setmLeaveTaken(int mLeaveTaken) {
        this.mLeaveTaken = mLeaveTaken;
    }

    public String getmEngineerId() {
        return mEngineerId;
    }

    public void setmEngineerId(String mEngineerId) {
        this.mEngineerId = mEngineerId;
    }

    public int getmFalloutNumber() {
        return mFalloutNumber;
    }

    public void setmFalloutNumber(int mFalloutNumber) {
        this.mFalloutNumber = mFalloutNumber;
    }
}
