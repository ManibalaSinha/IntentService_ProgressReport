package com.example.android.intentservice.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyService extends IntentService {

    public  static final String TAG="MyService";
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
