package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class addActivity extends AppCompatActivity implements View.OnClickListener{
    private Button returnButton, addButton,btnpickimg;
    private EditText editTextFirstName,editTextLastName,editTextPhoneNumber,editTextEmail;
    ImageView imgv;
    private byte[] selectedImageData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addButton=findViewById(R.id.btnadd);
        imgv=findViewById(R.id.imgv);
        returnButton=findViewById(R.id.btnreturn);
        btnpickimg=findViewById(R.id.btnpickimg);
        editTextFirstName=findViewById(R.id.editTextFirstName);
        editTextLastName=findViewById(R.id.editTextLastName);
        editTextPhoneNumber=findViewById(R.id.editTextPhoneNumber);
        editTextEmail=findViewById(R.id.editTextEmail);
        addButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        btnpickimg.setOnClickListener(this);
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

        Employee emp=new Employee(lastName,firstName,phoneNumber,email,selectedImageData );
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
    else if(v.getId()==R.id.btnpickimg){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,3);

    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null){
            Uri selectedimg=data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Set the loaded Bitmap to the ImageView
            imgv.setImageBitmap(bitmap);
            // Convert the Bitmap to byte array
            selectedImageData = convertBitmapToByteArray(bitmap);
        }
    }
    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
}