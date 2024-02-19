package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Random rand = new Random();
        //MyAlarmReceiver.argb = Color.argb(rand.nextInt(256), rand.nextInt(256),
        //        rand.nextInt(256), rand.nextInt(256));

        Intent intent = new Intent(MainActivity.this, MyAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    public void BtnStartPush(View view) {
        context = this;
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2000, pendingIntent);
    }

    public void BtnStopPush(View view) {
        alarmManager.cancel(pendingIntent);
    }
}
