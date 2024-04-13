package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetails extends AppCompatActivity implements View.OnClickListener{
    private TextView editTextFirstName, editTextLastName, editTextPhoneNumber, editTextEmail;
    private ImageView imageViewEmployee;
    Button gobackbtn,updatebtn,smsbtn,emailbtn,deletebtn;
    Employee selectedEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        imageViewEmployee = findViewById(R.id.imageViewEmployee);
        gobackbtn=findViewById(R.id.buttonGoBack);
        updatebtn=findViewById(R.id.buttonUpdate);
        smsbtn=findViewById(R.id.buttonSendSMS);
        emailbtn=findViewById(R.id.buttonSendEmail);
        deletebtn=findViewById(R.id.buttondeleterow);
         selectedEmployee = (Employee) getIntent().getSerializableExtra("selectedEmployee");
        if (selectedEmployee != null) {
            editTextFirstName.setText(selectedEmployee.getFirstName());
            editTextLastName.setText(selectedEmployee.getLastName());
            editTextPhoneNumber.setText(selectedEmployee.getPhoneNumber());
            editTextEmail.setText(selectedEmployee.getEmail());


        }
        gobackbtn.setOnClickListener(this);
        updatebtn.setOnClickListener(this);
        smsbtn.setOnClickListener(this);
        emailbtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonGoBack){
            Intent intent = new Intent(EmployeeDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.buttonUpdate){
            String firstName = editTextFirstName.getText().toString().trim();
            String lastName = editTextLastName.getText().toString().trim();
            String phoneNumber = editTextPhoneNumber.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            if(!firstName.isEmpty()){
                selectedEmployee.setFirstName(firstName);
            }
            if(!lastName.isEmpty()){
                selectedEmployee.setLastName(lastName);
            }
            if(!phoneNumber.isEmpty()){
                selectedEmployee.setPhoneNumber(phoneNumber);
            }
            if(!email.isEmpty()){
                selectedEmployee.setEmail(email);
            }
            DBHandler dbHandler=new DBHandler(this);
            dbHandler.updateEmployee(selectedEmployee);
            Toast.makeText(this, "Employee Updated!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.buttonSendSMS){
            Intent intent = new Intent(EmployeeDetails.this, SmsHandler.class);
            String phoneNumber1 = editTextPhoneNumber.getText().toString().trim();
            intent.putExtra("EmployeePhoneNumber", phoneNumber1);
            startActivity(intent);
        }
        else if(v.getId()==R.id.buttonSendEmail){

        }
        else if(v.getId()==R.id.buttondeleterow){
            DBHandler dbHandler=new DBHandler(this);
            dbHandler.deleteEmployeeById(selectedEmployee.getId());
            Toast.makeText(this, "Employee Deleted!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EmployeeDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else{

        }
    }
}