package com.felkertech.n.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Julian on 26/05/2016.
 */
public class NotificacionReceiver extends BroadcastReceiver {

    public static final String EXTRA_MSG="mensaje";
    public static final String ACTION="com.felkertech.n.NOTIFICAR";

    public interface OnNotificationListener{
        void onNotification(String msg);
    }

    OnNotificationListener onNotificationListener;

    public NotificacionReceiver(OnNotificationListener onNotificationListener) {
        this.onNotificationListener = onNotificationListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(EXTRA_MSG);
        onNotificationListener.onNotification(msg);
    }
}
