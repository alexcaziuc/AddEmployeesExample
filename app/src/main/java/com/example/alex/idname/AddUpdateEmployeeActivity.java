package com.example.alex.idname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alex.idname.db.EmployeeOperations;

public class AddUpdateEmployeeActivity extends AppCompatActivity {

    private static final String EXTRA_EMP_ID = "com.example.alex.idname.empId";
    private static final String EXTRA_ADD_UPDATE = "com.example.alex.idname.add_update";
    private static final String DIALOG_DATE = "DialogDate";

    private EditText firstNameEditText;
    private Button addUpdateButton;
    private Employee newEmployee;
    private Employee oldEmployee;
    private String mode;
    private long empId;
    private EmployeeOperations employeeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);

        newEmployee = new Employee();
        oldEmployee = new Employee();
        firstNameEditText = (EditText) findViewById(R.id.edit_text_first_name);
        addUpdateButton = (Button) findViewById(R.id.button_add_update_employee);
        employeeData = new EmployeeOperations(this);
        employeeData.open();

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if (mode.equals("Update")) {

            addUpdateButton.setText("Update Employee");
            empId = getIntent().getLongExtra(EXTRA_EMP_ID, 0);

            initializeEmployee(empId);

        }

        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mode.equals("Add")) {
                    newEmployee.setFirstname(firstNameEditText.getText().toString());
                    employeeData.addEmployee(newEmployee);
                    Toast t = Toast.makeText(AddUpdateEmployeeActivity.this, "Employee "
                            + newEmployee.getFirstname() + "has been added successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateEmployeeActivity.this,MainActivity.class);
                    startActivity(i);
                }else {
                    oldEmployee.setFirstname(firstNameEditText.getText().toString());
                    employeeData.updateEmployee(oldEmployee);
                    Toast t = Toast.makeText(AddUpdateEmployeeActivity.this, "Employee "
                            + oldEmployee.getFirstname() + " has been updated successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateEmployeeActivity.this,MainActivity.class);
                    startActivity(i);

                }


            }
        });

    }
    private void initializeEmployee(long empId) {
        oldEmployee = employeeData.getEmployee(empId);
        firstNameEditText.setText(oldEmployee.getFirstname());

    }
}
