package com.example.alex.idname.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alex.idname.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 03-Mar-18.
 */

public class EmployeeOperations {

    public static final String LOGTAG = "EMP_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            EmployeeDBHandler.COLUMN_ID,
            EmployeeDBHandler.COLUMN_FIRST_NAME
    };

    public EmployeeOperations(Context context){
        dbhandler = new EmployeeDBHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }

    public Employee addEmployee(Employee Employee){

        ContentValues values  = new ContentValues();
        values.put(EmployeeDBHandler.COLUMN_FIRST_NAME,Employee.getFirstname());

        long insertid = database.insert(EmployeeDBHandler.TABLE_EMPLOYEES,null,values);
        Employee.setEmpId(insertid);

        return Employee;

    }

    // Getting single Employee
    public Employee getEmployee(long id) {

        Cursor cursor = database.query(EmployeeDBHandler.TABLE_EMPLOYEES,allColumns,
                EmployeeDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},
                null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee e = new Employee(Long.parseLong(cursor.getString(0)));
        // return Employee
        return e;
    }

    // get the whole list
    public List<Employee> getAllEmployees() {

        Cursor cursor = database.query(EmployeeDBHandler.TABLE_EMPLOYEES,allColumns
                ,null,null,null, null, null);

        List<Employee> employees = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){

                Employee employee = new Employee();
                employee.setEmpId(cursor.getLong(cursor.getColumnIndex(EmployeeDBHandler.COLUMN_ID)));
                employee.setFirstname(cursor.getString(cursor.getColumnIndex(EmployeeDBHandler.COLUMN_FIRST_NAME)));
                employees.add(employee);
            }
        }
        // return All Employees
        return employees;
    }

    // Updating Employee
    public int updateEmployee(Employee employee) {

        ContentValues values = new ContentValues();
        values.put(EmployeeDBHandler.COLUMN_FIRST_NAME, employee.getFirstname());

        // updating row
        return database.update(EmployeeDBHandler.TABLE_EMPLOYEES, values,
                EmployeeDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(employee.getEmpId())});
    }

    // Deleting Employee
    public void removeEmployee(Employee employee) {

        database.delete(EmployeeDBHandler.TABLE_EMPLOYEES, EmployeeDBHandler.COLUMN_ID +
                "=" + employee.getEmpId(), null);
    }

}
