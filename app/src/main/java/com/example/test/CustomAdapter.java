package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Employee> {
    private Context context;
    private ArrayList<Employee> employees;
    public CustomAdapter(Context context, ArrayList<Employee> employees) {
        super(context, R.layout.listview_item, employees);
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
        }
        TextView txtId = convertView.findViewById(R.id.txtid);
        TextView txtFirstName = convertView.findViewById(R.id.txtfirstname);
        TextView txtLastName = convertView.findViewById(R.id.txtlastname);
        TextView txtPhoneNumber = convertView.findViewById(R.id.txtphonenumber);
        Employee employee = employees.get(position);
        txtId.setText(String.valueOf(employee.getId()));
        txtFirstName.setText(employee.getFirstName());
        txtLastName.setText(employee.getLastName());
        txtPhoneNumber.setText(employee.getPhoneNumber());

        return convertView;
    }
}
