package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsHandler extends AppCompatActivity implements View.OnClickListener{
    Button btnsendsms,goback;
    EditText txtmessage,txtphonenumber;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_handler);
        phoneNumber = getIntent().getStringExtra("selectedEmployee");
        txtmessage=findViewById(R.id.editTextMessage);
        txtphonenumber=findViewById(R.id.editTextPhoneNumber);
        btnsendsms=findViewById(R.id.buttonsendsms);
        goback=findViewById(R.id.goback);

        txtphonenumber.setText(phoneNumber);

        goback.setOnClickListener(this);
        btnsendsms.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonsendsms){
            String smsMessage=txtmessage.getText().toString().trim();
            if(smsMessage.isEmpty()){
                smsMessage="Hello World !";
            }
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + phoneNumber));
            intent.putExtra("sms_body", smsMessage);
            startActivity(intent);

        }
        else if(v.getId()==R.id.goback){
            Intent intent = new Intent(SmsHandler.this, MainActivity.class);
            startActivity(intent);
        }
    }
}