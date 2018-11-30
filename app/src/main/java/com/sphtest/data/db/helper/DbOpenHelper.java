package com.sphtest.data.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sphtest.data.db.tables.DaoMaster;

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}

