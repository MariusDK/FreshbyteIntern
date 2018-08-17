package com.example.marius.servicesbasic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService2 extends Service {
    int mStartMode;
    IBinder mBinder;
    boolean mAllowRebind;

    @Override
    public void onCreate()
    {}
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return mStartMode;
    }
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }
    public boolean onUnbind(Intent intent)
    {
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public void onDestroy() {

    }
}
