package com.example.a16004118.cghversion10.NotificationService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.a16004118.cghversion10.Activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    //Show notification when there is a notification from firebase

    private static final String TAG = "MyFirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"From " + remoteMessage.getFrom());

        //check msg contains data payload.
        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/*Long running Job?*/true){
                scheduleJob();
            }else{
                handleNow();
            }
            
        }

        //Check msg contains notification payload.
        if (remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Body: "
                    + remoteMessage.getNotification().getBody());
        }

        //requires API 19 and above
        showNotification(remoteMessage.getNotification());

    }

    private void handleNow() {
    }

    private void scheduleJob() {
    }

    private void showNotification(RemoteMessage.Notification notification) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}
