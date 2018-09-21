package com.example.android.intentservice.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


public class MyService extends IntentService {

    public  static final String TAG="MyService";
    //one for listening and other for message itself
    public static final String MY_SERVICE_MESSAGE= "myServiceMessage";
    public static final String MY_SERVICE_PAYLOAD= "myServicePayload";

    //step2 constructor method
    public MyService() {
        //satisfy the rquirement of class by passing the name of class
        super("MyService");
    }

    //step1 Implement method
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//step4// method returns the Uri object that i passed in from Activity
        Uri uri = intent.getData();
        Log.i(TAG,"onHandleIntent: " + uri.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        //step5
        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_PAYLOAD, "Service all done!");
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
