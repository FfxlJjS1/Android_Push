package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class MyAlarmReceiver extends BroadcastReceiver {

    private String CHANNEL_ID = "APP_ID";
    static int count=1, argb = 0;
    static Bitmap btmf = BitmapFactory.decodeResource(MainActivity.context.getResources(), R.drawable.relax);

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String contentTitle = "Отдыхаем)",
                contentText = "Отдых - доволно важная часть жизни. " +
                        "Позволяющая восполнить энергию как психическую, такк и физическую. " +
                        "Это очень важный процесс, ведь без него человек будет вялым и опустошенным, что очень плохо скажется на свех сферах жизни человека. ";

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        createNotificationChannel("Notification_channel", "Channel description", notificationManager);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setLargeIcon(btmf)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText))
                        .setShowWhen(true)
                        .setColor(argb)
                        .setContentIntent(resultPendingIntent);

        NotificationCompat.Builder builder1 =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setLargeIcon(btmf)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(btmf))
                        .setShowWhen(true)
                        .setColor(argb)
                        .setContentIntent(resultPendingIntent);

        notificationManager.notify(count,builder.build());
        notificationManager.notify(count+1,builder1.build());

        Intent intent1 = new Intent(context, MyAlarmReceiver.class);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent1, 0);

        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND,10);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    // Получение канала уведомления
    private  void createNotificationChannel (String channelName, String channelDescription, NotificationManagerCompat nm){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            nm.createNotificationChannel(channel);
        }
    }
}
