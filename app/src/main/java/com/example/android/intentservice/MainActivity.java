package com.example.android.intentservice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.intentservice.services.MyService;

public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL="http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.output);
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
