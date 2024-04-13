package com.example.test;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "dbmanagement";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE employee (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "last_name TEXT," +
                "first_name TEXT," +
                "phone_number TEXT," +
                "email TEXT" +
                ")";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS etudiant");
        onCreate(db);
    }


    public void addEmployee(Employee emp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("last_name",emp.getLastName());
        values.put("first_name",emp.getFirstName());
        values.put("phone_number",emp.getPhoneNumber());
        values.put("email",emp.getEmail());
        db.insert("employee", null, values);
        db.close();
    }


    public Employee[] getEmployees(){
    SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM employee",null);
        if(cursor != null && cursor.moveToFirst()){
            int count=cursor.getCount();
            Employee [] emps=new Employee[count];
            int id=-1;
            byte [] photo=null;
            String lastName=null,firstName=null,phoneNumber=null,email=null;
            int idIndex = cursor.getColumnIndex("id");

            int lastNameIndex = cursor.getColumnIndex("last_name");
            int firstNameIndex = cursor.getColumnIndex("first_name");
            int phoneNumberIndex = cursor.getColumnIndex("phone_number");
            int emailIndex = cursor.getColumnIndex("email");
            for(int i=0;i<count;i++){

                 id = cursor.getInt(idIndex);

                 lastName = cursor.getString(lastNameIndex);
                 firstName = cursor.getString(firstNameIndex);
                 phoneNumber = cursor.getString(phoneNumberIndex);
                 email = cursor.getString(emailIndex);
                emps[i] = new Employee(id, lastName, firstName, phoneNumber, email);
                cursor.moveToNext();
            }
            if (id != -1 &&  lastName != null && firstName != null && phoneNumber != null && email != null) {
                cursor.close();
                db.close();
                return emps;
            } else {
                return null;
            }


        }
        else{
            return null;
        }



    }
    public Employee[] getEmployeesByFirstName(String firstNameFilter) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM employee WHERE first_name LIKE ?";
        String[] selectionArgs = new String[]{"%" + firstNameFilter + "%"};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            int count = cursor.getCount();
            Employee[] emps = new Employee[count];

            int idIndex = cursor.getColumnIndex("id");
            int lastNameIndex = cursor.getColumnIndex("last_name");
            int firstNameIndex = cursor.getColumnIndex("first_name");
            int phoneNumberIndex = cursor.getColumnIndex("phone_number");
            int emailIndex = cursor.getColumnIndex("email");

            for (int i = 0; i < count; i++) {
                int id = cursor.getInt(idIndex);
                String lastName = cursor.getString(lastNameIndex);
                String firstName = cursor.getString(firstNameIndex);
                String phoneNumber = cursor.getString(phoneNumberIndex);
                String email = cursor.getString(emailIndex);

                emps[i] = new Employee(id, lastName, firstName, phoneNumber, email);
                cursor.moveToNext();
            }

            cursor.close();
            db.close();
            return emps;
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }
    public void updateEmployee(Employee emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("last_name", emp.getLastName());
        values.put("first_name", emp.getFirstName());
        values.put("phone_number", emp.getPhoneNumber());
        values.put("email", emp.getEmail());

        // Define the WHERE clause to identify the record to update (by id)
        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(emp.getId())};

        // Perform the update operation
        int rowsAffected = db.update("employee", values, whereClause, whereArgs);

        // Close the database connection
        db.close();


    }
    public void deleteEmployeeById(int employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause to identify the record to delete (by id)
        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(employeeId)};

        // Perform the delete operation
        int rowsDeleted = db.delete("employee", whereClause, whereArgs);

        // Close the database connection
        db.close();
    }

}
