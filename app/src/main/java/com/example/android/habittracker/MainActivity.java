package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
                Intent appInfo = new Intent(MainActivity.this, addHabit.class);
                startActivity(appInfo);
            }
        });

        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    public void displayDatabaseInfo()
    {
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String habitDataToShow = "Habit Data";

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, null, null, null,
                null, null, null);

        //  Cursor cursor = db.rawQuery("SELECT * FROM "+ HabitEntry.TABLE_NAME,null);

        try {

            TextView showmsg = (TextView)findViewById(R.id.msgTextView);
            showmsg.setText("Number of rows in database : "+ cursor.getCount());

            TextView habitData = (TextView)findViewById(R.id.HabitInfoTextView);

            int idColomIndex = cursor.getColumnIndex(HabitEntry._ID);
            int habitNameColomIndex = cursor.getColumnIndex(HabitEntry.HABIT_NAME);
            int habitTimeColomIndex = cursor.getColumnIndex(HabitEntry.HOW_MANY_DAYS);

            while (cursor.moveToNext())
            {
                int currentId = cursor.getInt(idColomIndex);
                String currentHabit = cursor.getString(habitNameColomIndex);
                String days = cursor.getString(habitTimeColomIndex);
                habitDataToShow = habitDataToShow + "\n\n" +currentId + ".  "+"Habit : "+ currentHabit +".  From Days : "+days;
            }
            habitData.setText(habitDataToShow);
        }
        finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
