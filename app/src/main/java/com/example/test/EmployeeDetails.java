package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetails extends AppCompatActivity implements View.OnClickListener{
    private TextView editTextFirstName, editTextLastName, editTextPhoneNumber, editTextEmail;
    private ImageView imageViewEmployee;
    Button gobackbtn,updatebtn,deletebtn,buttonSend;
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

        deletebtn=findViewById(R.id.buttondeleterow);
         selectedEmployee = (Employee) getIntent().getSerializableExtra("selectedEmployee");
        if (selectedEmployee != null) {
            editTextFirstName.setText(selectedEmployee.getFirstName());
            editTextLastName.setText(selectedEmployee.getLastName());
            editTextPhoneNumber.setText(selectedEmployee.getPhoneNumber());
            editTextEmail.setText(selectedEmployee.getEmail());
            byte[] photoBytes =selectedEmployee.getPhoto();

            if (photoBytes != null && photoBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
                if (bitmap != null) {
                    imageViewEmployee.setImageBitmap(bitmap);
                    Toast.makeText(this, "Image loaded successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to decode image data!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No image data found!", Toast.LENGTH_SHORT).show();
            }

        }
        gobackbtn.setOnClickListener(this);
        updatebtn.setOnClickListener(this);

        deletebtn.setOnClickListener(this);
        buttonSend = findViewById(R.id.buttonSend);

        buttonSend.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu popupMenu = new PopupMenu(EmployeeDetails.this, buttonSend);
                popupMenu.getMenuInflater().inflate(R.menu.send_options_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.menu_sms){
                            sendSMS();
                            return true;
                        }
                        else if(item.getItemId()==R.id.menu_email){
                            sendEmail();
                            return true;
                        }
                        else if(item.getItemId()==R.id.menu_call){
                            sendCall();
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                });

                popupMenu.show();
                return true;
            }
        });
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

    private void sendSMS() {
        String phoneNumber1 = editTextPhoneNumber.getText().toString().trim();
        Uri smsUri = Uri.parse("smsto:" + phoneNumber1);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsUri);
        startActivity(intent);
    }

    private void sendEmail() {
        String emailaddress=editTextEmail.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + Uri.encode(emailaddress)));
        startActivity(intent);
    }

    private void sendCall(){
        String phoneNumber1 = editTextPhoneNumber.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber1));
        startActivity(intent);
    }

}