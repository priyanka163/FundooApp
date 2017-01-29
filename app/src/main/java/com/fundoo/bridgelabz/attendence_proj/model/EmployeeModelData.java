package com.fundoo.bridgelabz.attendence_proj.model;

/**
 * Created by bridgelabz on 20/12/16.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class. */

public class EmployeeModelData {
    private String employee_name;
    private String employee_profile;
    private String employee_company;
    private String employee_mob_no;
    private String employee_email;

    public EmployeeModelData() {
    }

    public EmployeeModelData(String employee_name, String employee_profile,
                             String employee_company, String employee_mob_no,
                             String employee_email) {
        this.employee_name = employee_name;
        this.employee_profile = employee_profile;
        this.employee_company = employee_company;
        this.employee_mob_no = employee_mob_no;
        this.employee_email = employee_email;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_profile() {
        return employee_profile;
    }

    public void setEmployee_profile(String employee_profile) {
        this.employee_profile = employee_profile;
    }

    public String getEmployee_company() {
        return employee_company;
    }

    public void setEmployee_company(String employee_company) {
        this.employee_company = employee_company;
    }

    public String getEmployee_mob_no() {
        return employee_mob_no;
    }

    public void setEmployee_mob_no(String employee_mob_no) {
        this.employee_mob_no = employee_mob_no;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }
}

