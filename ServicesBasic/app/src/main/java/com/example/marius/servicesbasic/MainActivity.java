package com.example.marius.servicesbasic;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int RSS_JOB_ID = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.putExtra("downloar_url",true);
        MyIntentService myIntentService = new MyIntentService();
        JobIntentService jobIntentService;
        DownloadStateReceiver mDownloadStateReceiver = new DownloadStateReceiver();
        IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mDownloadStateReceiver,statusIntentFilter);
        


    }
}
