package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.habittracker.data.HabitContract;
import com.example.android.habittracker.data.HabitDbHelper;

public class addHabit extends AppCompatActivity {

    private HabitDbHelper DbHelper;
    EditText habitName,days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        DbHelper = new HabitDbHelper(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHabit();
                Intent appInfo = new Intent(addHabit.this, MainActivity.class);
                startActivity(appInfo);
            }
        });
    }

    private void insertHabit()
    {
        SQLiteDatabase db = DbHelper.getWritableDatabase();

        habitName = (EditText)findViewById(R.id.NameEditText);
        days = (EditText)findViewById(R.id.DaysEditText);

        ContentValues values = new ContentValues();

        values.put(HabitContract.HabitEntry.HABIT_NAME,habitName.getText().toString());
        values.put(HabitContract.HabitEntry.HOW_MANY_DAYS,days.getText().toString());

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME,null,values);

        Log.v("addHabit","new row number  : "+newRowId);

    }

}