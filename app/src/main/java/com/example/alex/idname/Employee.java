package com.example.alex.idname;

/**
 * Created by Alex on 03-Mar-18.
 */

public class Employee {

    private long empId;
    private String firstname;

    public Employee(long empId, String firstname) {
        this.empId = empId;
        this.firstname = firstname;
    }

    public Employee(long empId) {
        this.empId = empId;
    }

    public Employee() {

    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
