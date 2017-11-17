package com.example.android.assignment10_sqllight_taite;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;
import com.example.android.assignment10_sqllight_taite.ItemDBSchema.ItemTable;

/**
 * Created by Android on 11/7/2017.
 */

public class ItemCursorWrapper extends CursorWrapper {

    public ItemCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Item getItem()
    {
        String uuidString = getString(getColumnIndex(ItemTable.Cols.UUID));
        String title = getString(getColumnIndex(ItemTable.Cols.TITLE));
        float retailP = getFloat(getColumnIndex(ItemTable.Cols.RETAIL));
        float wholesaleP = getFloat(getColumnIndex(ItemTable.Cols.WHOLESALE));
        float saleP = getFloat(getColumnIndex(ItemTable.Cols.SALE));
        int stockCount = getInt(getColumnIndex(ItemTable.Cols.COUNT));

        Item item = new Item(UUID.fromString(uuidString));
        item.setItemName(title);
        item.setRetailPrice(retailP);
        item.setSalePrice(saleP);
        item.setWholesalePrice(wholesaleP);
        item.setStockCount(stockCount);

        return item;
    }

}
