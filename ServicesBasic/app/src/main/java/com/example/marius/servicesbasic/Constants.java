package com.example.marius.servicesbasic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.content.LocalBroadcastManager;

public final class Constants {
    public static final String BROADCAST_ACTION = "com.example.android.threadsample.BROADCAST";
    public static final String EXTENDED_DATA_STATUS = "com.example.android.threadsample.STATUS";

}
public class RSSPullService extends JobIntentService
{
    Intent localIntent = new Intent(Constants.BROADCAST_ACTION).putExtra(Constants.EXTENDED_DATA_STATUS,"ok");
    //LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

    }
}
