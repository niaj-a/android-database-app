package com.example.databasewithcomponents;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context, @Nullable String name,
                      @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS COMPONENTDATABASE(" +
                        "Name VARCHAR(255), " +
                        "RollNo VARCHAR(255), " +
                        "PhoneNo VARCHAR(255), " +
                        "Section VARCHAR(255), " +
                        "Course VARCHAR(255), " +
                        "Spinner VARCHAR(255), " +
                        "RadioBtn VARCHAR(255), " +
                        "CheckBox VARCHAR(255))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS COMPONENTDATABASE");
        onCreate(sqLiteDatabase);
    }

    public void insertValues(String name, String rollNo, String phoneNo, String section,
                             String course, String spinner, String radioBtn, String checkBox) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO COMPONENTDATABASE " +
                "(Name, RollNo, PhoneNo, Section, Course, Spinner, RadioBtn, CheckBox) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        db.execSQL(query, new String[]{name, rollNo, phoneNo, section, course, spinner, radioBtn, checkBox});
    }

    public void deleteValues(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("COMPONENTDATABASE", "Name = ?", new String[]{name});
    }

    public String displayData() {
        StringBuilder data = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM COMPONENTDATABASE", null);

        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String rollNo = cursor.getString(1);
                String phoneNo = cursor.getString(2);
                String section = cursor.getString(3);
                String course = cursor.getString(4);
                String spinner = cursor.getString(5);
                String radioBtn = cursor.getString(6);
                String checkBox = cursor.getString(7);

                data.append("Name: ").append(name).append("\n")
                        .append("RollNo: ").append(rollNo).append("\n")
                        .append("PhoneNo: ").append(phoneNo).append("\n")
                        .append("Section: ").append(section).append("\n")
                        .append("Course: ").append(course).append("\n")
                        .append("Spinner: ").append(spinner).append("\n")
                        .append("RadioBtn: ").append(radioBtn).append("\n")
                        .append("CheckBox: ").append(checkBox).append("\n\n");
            }
        } finally {
            cursor.close();
        }

        if (data.length() == 0) {
            return "No data found";
        }

        return data.toString();
    }
}

