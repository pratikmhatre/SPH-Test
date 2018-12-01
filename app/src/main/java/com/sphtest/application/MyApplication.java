package com.sphtest.application;

import android.app.Application;

import com.sphtest.Utils;
import com.sphtest.data.db.helper.DbOpenHelper;
import com.sphtest.data.db.tables.DaoMaster;
import com.sphtest.data.db.tables.DaoSession;
import com.sphtest.di.components.AppComponent;
import com.sphtest.di.components.DaggerAppComponent;
import com.sphtest.di.modules.AppModule;

public class MyApplication extends Application {
    private DaoSession daoSession;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        daoSession = new DaoMaster(new DbOpenHelper(this, Utils.DB_NAME).getWritableDatabase()).newSession();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
