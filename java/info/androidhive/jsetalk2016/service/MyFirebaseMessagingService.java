package info.androidhive.jsetalk2016.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import info.androidhive.jsetalk2016.R;
import info.androidhive.jsetalk2016.activity.SplashActivity;


/**
 * Created by Administrator on 11/1/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    int notification_id = 0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        String title = "";
        String noti_icon = "";
        String content_link = "";
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            title = remoteMessage.getData().get("title");
            noti_icon = remoteMessage.getData().get("noti_icon");
            if (noti_icon.equals("yes"))
                content_link = remoteMessage.getData().get("content_link");
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            String messageBody = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Message Notification Body: " + messageBody);
            sendNotification(messageBody,title,noti_icon,content_link);
        }
    }

    private void sendNotification(String message,String title,String noti_icon,String content_link) {

        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.jse);

        //choose small icon
        int icon_id = R.drawable.ic_chat_white_24dp;
        if (noti_icon.equals("yes"))
            icon_id = R.drawable.ic_notifications_active_white_24dp;

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(icon_id)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setLargeIcon(image)/*Notification icon image*/
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notification_id /* ID of notification */, notificationBuilder.build());

    }

}