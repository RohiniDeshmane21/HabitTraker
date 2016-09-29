package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker.data.HabitContract.HabitEntry;
/**
 * Created by Rupali on 28-09-2016.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME =  "habitData.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table statement to string variable
        String SQL_CREATE_HABIT_TABLE ="CREATE TABLE "+ HabitEntry.TABLE_NAME + "(" +
                                        HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + HabitEntry.HABIT_NAME +" TEXT NOT NULL, "
                                        + HabitEntry.HOW_MANY_DAYS +" TEXT );";
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
