package com.felkertech.n;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.felkertech.n.recievers.NotificacionReceiver;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Julian on 25/05/2016.
 */
public class NotificationsListenerService extends FirebaseMessagingService {

    private String TAG = "Firebase:Notifications";

    public NotificationsListenerService(String TAG) {
        this.TAG = TAG;
    }

    public NotificationsListenerService() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        Intent notificar = new Intent(NotificacionReceiver.ACTION);
        notificar.putExtra(NotificacionReceiver.EXTRA_MSG, remoteMessage.getNotification().getBody());
        sendBroadcast(notificar);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }
}
