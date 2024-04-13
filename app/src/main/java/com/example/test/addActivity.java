package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addActivity extends AppCompatActivity implements View.OnClickListener{
    private Button returnButton, addButton;
    private EditText editTextFirstName,editTextLastName,editTextPhoneNumber,editTextEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addButton=findViewById(R.id.btnadd);
        returnButton=findViewById(R.id.btnreturn);
        editTextFirstName=findViewById(R.id.editTextFirstName);
        editTextLastName=findViewById(R.id.editTextLastName);
        editTextPhoneNumber=findViewById(R.id.editTextPhoneNumber);
        editTextEmail=findViewById(R.id.editTextEmail);
        addButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
    }
    void addEmployee(){
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        if(firstName.isEmpty()){
            firstName="defaultuser";

        }
        if(lastName.isEmpty()){
            lastName="defaultuser";
        }
        if(phoneNumber.isEmpty()){
            phoneNumber="0550555000";
        }
        if(email.isEmpty()){
            email="defaultuser@gmail.com";
        }
        Employee emp=new Employee(lastName,firstName,phoneNumber,email);
        try {
            DBHandler dbHandler = new DBHandler(this);
            dbHandler.addEmployee(emp);
            Toast.makeText(this, "Employee Added!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            Toast.makeText(this, "Internal Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }
    @Override
    public void onClick(View v) {
    if(v.getId()==R.id.btnadd){
    addEmployee();
    }
    else if(v.getId()==R.id.btnreturn){
        Intent intent = new Intent(addActivity.this, MainActivity.class);
        startActivity(intent);
    }
    }
}