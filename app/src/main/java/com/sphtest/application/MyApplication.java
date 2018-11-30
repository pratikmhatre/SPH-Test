package com.sphtest.application;

import android.app.Application;

import com.sphtest.Utils;
import com.sphtest.data.db.helper.DbOpenHelper;
import com.sphtest.data.db.tables.DaoMaster;
import com.sphtest.data.db.tables.DaoSession;

public class MyApplication extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster(new DbOpenHelper(this, Utils.BASE_URL).getWritableDatabase()).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
