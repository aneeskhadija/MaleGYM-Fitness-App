package com.NewAppZone.home_workout.fitness_bodybuilding.Reminder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationManager.notify(1000, new NotificationCompat.Builder(context).setSmallIcon(R.drawable.icon).setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.get_strength_and_definition)).setSound(RingtoneManager.getDefaultUri(2)).setAutoCancel(true)
                .setWhen(when).setContentIntent(PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build());
    }
}
