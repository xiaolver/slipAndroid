package com.sunday.autoslip0201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("TAG","start");
                int timeToStart = Integer.parseInt(((EditText)findViewById(R.id.timeToStartSlip)).getText().toString());
                moveTaskToBack(true);
                try {
                    Thread.sleep(timeToStart* 1000L);
                    Log.d("TAG","AccessService Start");
                    if (!OpenAccessibilitySettingHelper.isAccessibilitySettingsOn(MainActivity.this,
                            AccessibilitySampleService.class.getName())){// 判断服务是否开启
                        OpenAccessibilitySettingHelper.jumpToSettingPage(MainActivity.this);// 跳转到开启页面
                    }else {
                        Toast.makeText(MainActivity.this, "服务已开启", Toast.LENGTH_SHORT).show();
                    }
                    Intent serviceIntent = new Intent(MainActivity.this, AccessibilitySampleService.class);
                    startService(serviceIntent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
               // Code here executes on main thread after user presses button
            }
        });

    }

}