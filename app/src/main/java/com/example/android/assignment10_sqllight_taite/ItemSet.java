package com.example.android.assignment10_sqllight_taite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Android on 11/7/2017.
 */

public class ItemSet {
    private static ItemSet itemSet;

    private final SQLiteDatabase database;

    private ItemSet(Context context)
    {
        database = new ItemDBHelper(context).getWritableDatabase();
    }

    public static ItemSet getList(Context context)
    {
        if(itemSet == null)
        {
            itemSet = new ItemSet(context);
        }

        return itemSet;
    }

    public void addItem(Item item)
    {
        ContentValues values = getContentValues(item);
        database.insert(ItemDBSchema.ItemTable.NAME, null, values);
    }

    public List<Item> getItems()
    {
        List<Item> items = new ArrayList<>();
        //passing null because we want everything
        ItemCursorWrapper cursor = queryItems(null, null);

        try {
            cursor.moveToFirst();

            while(!cursor.isAfterLast())
            {
                items.add(cursor.getItem());
                cursor.moveToNext();
            }
        } finally {
            //finally always happens
            cursor.close();
        }

        return items;
    }

    public Item getItem(UUID id)
    {
        //only trying to get one row
        ItemCursorWrapper cursor = queryItems(
                //specify where clause
                ItemDBSchema.ItemTable.Cols.UUID + "=?",
                //specify id
                new String[]{id.toString()}
        );

        //see if we found that in db
        try
        {
            if(cursor.getCount() == 0) return null;
            cursor.moveToFirst();
            return cursor.getItem();
        } finally {
            cursor.close();
        }
    }

    public void updateItem(Item item)
    {
        String uuidString = item.getId().toString();
        ContentValues values = getContentValues(item);
        database.update(ItemDBSchema.ItemTable.NAME, values,
                ItemDBSchema.ItemTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public static ContentValues getContentValues(Item item)
    {
        //ties keys to values
        ContentValues values = new ContentValues();

        values.put(ItemDBSchema.ItemTable.Cols.UUID, item.getId().toString());
        values.put(ItemDBSchema.ItemTable.Cols.TITLE, item.getItemName());
        values.put(ItemDBSchema.ItemTable.Cols.RETAIL, item.getRetailPrice());
        values.put(ItemDBSchema.ItemTable.Cols.WHOLESALE, item.getWholesalePrice());
        values.put(ItemDBSchema.ItemTable.Cols.SALE, item.getSalePrice());
        values.put(ItemDBSchema.ItemTable.Cols.COUNT, item.getStockCount());
        return values;
    }

    private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs)
    {
        Cursor cursor = database.query(
                ItemDBSchema.ItemTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        //when we get query result back have a lot of rows to go through
        //cursor lets us go through them
        return new ItemCursorWrapper(cursor);
    }
}
