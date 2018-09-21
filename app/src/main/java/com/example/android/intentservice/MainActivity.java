package com.example.android.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.intentservice.services.MyService;
import com.example.android.intentservice.utils.NetworkHelper;

public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL="http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    //step2: connection over the web
    private boolean networkOk;
    TextView output;
    //step6.
    //listening architecture
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //message received MY_SERVICE_PAYLOAD
            String message = intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);
            output.append(message + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.output);
        //step7
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBroadcastReceiver, new IntentFilter(MyService.MY_SERVICE_MESSAGE));
        // step3 connection over the web
        networkOk = NetworkHelper.hasNetworkAccess(this);
        output.append("Network ok: " + networkOk);
    }
//step 8
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mBroadcastReceiver);
    }

    public void runClickHandler(View view){
        //step3 pass the uri
        Intent intent = new Intent(this, MyService.class);
        intent.setData(Uri.parse(JSON_URL));
        startService(intent);
        startService(intent);
        startService(intent);
    }
    public void clearClickHandle(View view){
        output.setText("");
    }
}
