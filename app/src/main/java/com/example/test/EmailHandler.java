package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailHandler extends AppCompatActivity implements View.OnClickListener{
    Button btnsendemail,goback;
    EditText editTextHeader,editTextBody;
    TextView editTextEmailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_handler);
        editTextHeader=findViewById(R.id.editTextHeader);
        editTextBody=findViewById(R.id.editTextBody);
        editTextEmailAddress=findViewById(R.id.editxtemail);
        btnsendemail=findViewById(R.id.buttonsendemail);
        goback=findViewById(R.id.goback);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonsendemail){
            Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show();

        }
        else if(v.getId()==R.id.goback){
            Intent intent = new Intent(EmailHandler.this, MainActivity.class);
            startActivity(intent);
        }
    }
}