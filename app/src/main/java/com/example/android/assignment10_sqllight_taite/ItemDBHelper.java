package com.example.android.assignment10_sqllight_taite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.assignment10_sqllight_taite.ItemDBSchema.ItemTable;

/**
 * Created by Android on 11/7/2017.
 */

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "item.db";
    private static final int VERSION = 1;

    public ItemDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " +  ItemTable.NAME + "(" +
                "id integer primary key autoincrement, " +
                ItemTable.Cols.UUID + ", " +
                ItemTable.Cols.TITLE + ", " +
                ItemTable.Cols.RETAIL + ", " +
                ItemTable.Cols.WHOLESALE + ", " +
                ItemTable.Cols.SALE + ", " +
                ItemTable.Cols.COUNT + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //upgrading db
    }
}
