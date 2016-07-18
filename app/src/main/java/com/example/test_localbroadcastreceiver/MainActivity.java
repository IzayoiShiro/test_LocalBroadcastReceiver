package com.example.test_localbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter mIntentFilter;
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com/example/test_localbroadcastreceiver" +
                        ".LOCAL_BROADCAST");
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com/example/test_localbroadcastreceiver.LOCAL_BROADCAST");
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver,mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "receiver local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
