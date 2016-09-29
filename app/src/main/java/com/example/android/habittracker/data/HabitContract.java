package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Rupali on 28-09-2016.
 */
public class HabitContract {

    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns
    {
        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;
        public final static String HABIT_NAME = "habitName";
        public final static String HOW_MANY_DAYS = "habitStartDays";
    }
}
