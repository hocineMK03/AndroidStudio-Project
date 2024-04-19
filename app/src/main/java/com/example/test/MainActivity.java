package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtsearch;
    Employee[] employees;
    private Button searchButton, addButton,btnlayout;
    private ListView listView;
    private DBHandler dbHandler;
    private GridView gridView ;
    private static boolean isTwoColumnLayout = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    listView=findViewById(R.id.listView);
    gridView = findViewById(R.id.gridView);
    dbHandler=new DBHandler(this);
    txtsearch=findViewById(R.id.edt1);
    searchButton=findViewById(R.id.btn1);
    addButton=findViewById(R.id.btnAdd);
    btnlayout=findViewById(R.id.btnlayout);

    btnlayout.setOnClickListener(this);
    searchButton.setOnClickListener(this);
    addButton.setOnClickListener(this);

    setAdapter(null);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position >= 0 && position < employees.length) {
                Employee clickedEmployee = employees[position];
                Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
                intent.putExtra("selectedEmployee", clickedEmployee);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Invalid employee selection", Toast.LENGTH_SHORT).show();

            }
        }
    });
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position >= 0 && position < employees.length) {
                Employee clickedEmployee = employees[position];
                Intent intent = new Intent(MainActivity.this, EmployeeDetails.class);
                intent.putExtra("selectedEmployee", clickedEmployee);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Invalid employee selection", Toast.LENGTH_SHORT).show();

            }
        }
    });
    }


    void setAdapter(String filtering){

        if (filtering == null) {

            employees = dbHandler.getEmployees();

            if (employees != null && employees.length > 0) {
                // Convert Employee[] to ArrayList<Employee>
                ArrayList<Employee> employeeList = new ArrayList<>(Arrays.asList(employees));


                CustomAdapter listAdapter = new CustomAdapter(this, employeeList);
                CustomAdapter gridAdapter = new CustomAdapter(this, employeeList);

                // Set adapters to ListView and GridView
                ListView listView = findViewById(R.id.listView);

                listView.setAdapter(listAdapter);
                gridView.setAdapter(gridAdapter);

            }
        }
        else{
            employees = dbHandler.getEmployeesByFirstName(filtering);

            if (employees != null && employees.length > 0) {
                // Convert Employee[] to ArrayList<Employee>
                ArrayList<Employee> employeeList = new ArrayList<>(Arrays.asList(employees));
                CustomAdapter adapter = new CustomAdapter(this, employeeList);
                listView.setAdapter(adapter);
            }
        }
    }
    @Override
    public void onClick(View v) {
        String searchtext=txtsearch.getText().toString().trim();
        if(v.getId()==R.id.btnAdd){
            Intent intent = new Intent(MainActivity.this, addActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.btn1){

            if (!searchtext.isEmpty()) {
                String searchTerm = txtsearch.getText().toString().trim();
                setAdapter(searchTerm);
                Toast.makeText(this, "Performing search for: " + txtsearch, Toast.LENGTH_SHORT).show();
            } else {
               setAdapter(null);
            }
        }
        else if(v.getId()==R.id.btnlayout){
            isTwoColumnLayout=!isTwoColumnLayout;
            toggleLayout();
        }
        else{

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        dbHandler.close();
    }
    private void toggleLayout() {
        ListView listView = findViewById(R.id.listView);
        GridView gridView = findViewById(R.id.gridView);

        if (listView.getVisibility() == View.VISIBLE) {
            // Switch to GridView
            listView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        } else {
            // Switch to ListView
            gridView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }


}


