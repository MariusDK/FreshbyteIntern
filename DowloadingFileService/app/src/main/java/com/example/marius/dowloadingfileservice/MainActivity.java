package com.example.marius.dowloadingfileservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.status);
        Button start = (Button)findViewById(R.id.button1);
        start.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(DownloadService.NOTIFICATION));
    }
    public void onClick(View view)
    {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.FILENAME, "index.html");
        intent.putExtra(DownloadService.URL,"https://cdn-websites.imaginelearning.com/corporate/sites/default/files/2018-01/Imagine%20Math%20Facts%20Logo.png");
        startService(intent);
        textView.setText("Service started");
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle!=null)
            {
                String string = bundle.getString(DownloadService.FILEPATH);
                int resultCode = bundle.getInt(DownloadService.RESULT);
                if (resultCode == RESULT_OK)
                {
                    Toast.makeText(MainActivity.this,"Download complete. Download URI: "+ string, Toast.LENGTH_LONG).show();
                    textView.setText("Download done");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Download failed", Toast.LENGTH_LONG).show();
                    textView.setText("Download failed");
                }
            }
        }
    };
}
