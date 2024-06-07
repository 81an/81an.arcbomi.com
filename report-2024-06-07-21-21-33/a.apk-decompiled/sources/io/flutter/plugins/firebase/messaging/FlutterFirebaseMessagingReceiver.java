package io.flutter.plugins.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.messaging.RemoteMessage;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class FlutterFirebaseMessagingReceiver extends BroadcastReceiver {
    private static final String TAG = "FLTFireMsgReceiver";
    static HashMap<String, RemoteMessage> notifications = new HashMap<>();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "broadcast received for message");
        if (ContextHolder.getApplicationContext() == null) {
            ContextHolder.setApplicationContext(context.getApplicationContext());
        }
        if (intent.getExtras() == null) {
            Log.d(TAG, "broadcast received but intent contained no extras to process RemoteMessage. Operation cancelled.");
            return;
        }
        RemoteMessage remoteMessage = new RemoteMessage(intent.getExtras());
        if (remoteMessage.getNotification() != null) {
            notifications.put(remoteMessage.getMessageId(), remoteMessage);
            FlutterFirebaseMessagingStore.getInstance().storeFirebaseMessage(remoteMessage);
        }
        if (FlutterFirebaseMessagingUtils.isApplicationForeground(context)) {
            Intent intent2 = new Intent("io.flutter.plugins.firebase.messaging.NOTIFICATION");
            intent2.putExtra("notification", remoteMessage);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
        } else {
            Intent intent3 = new Intent(context, (Class<?>) FlutterFirebaseMessagingBackgroundService.class);
            intent3.putExtra("notification", remoteMessage);
            FlutterFirebaseMessagingBackgroundService.enqueueMessageProcessing(context, intent3);
        }
    }
}
