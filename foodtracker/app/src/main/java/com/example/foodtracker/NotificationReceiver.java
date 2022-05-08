package com.example.foodtracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "com.example.foodtracker.notification.notify_001";
    private static final String CHANNEL_NAME = "My notification channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get id & message from intent
        Integer notificationId = intent.getIntExtra("notificationId", 0);
        String foodName = intent.getStringExtra("foodName");
        Integer daysLeft = intent.getIntExtra("daysLeft", 1);

        // When notification is tapped, call MainActivity
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.putExtra("intFragment",1 );

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        NotificationManager myNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Prepare notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            myNotificationManager.createNotificationChannel(channel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Your food is going bad!")
                .setContentText("The " + foodName + " has " + String.valueOf(daysLeft) + "days left")
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Notify
        myNotificationManager.notify(notificationId, builder.build());

    }
}
