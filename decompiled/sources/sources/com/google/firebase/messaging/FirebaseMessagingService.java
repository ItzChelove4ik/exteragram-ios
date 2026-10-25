package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.Rpc;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class FirebaseMessagingService extends EnhancedIntentService {
    public static final String ACTION_DIRECT_BOOT_REMOTE_INTENT = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
    static final String ACTION_FCM_REGISTERED = "com.google.firebase.messaging.FCM_REGISTERED";
    static final String ACTION_FCM_UNREGISTERED = "com.google.firebase.messaging.FCM_UNREGISTERED";
    static final String ACTION_NEW_TOKEN = "com.google.firebase.messaging.NEW_TOKEN";
    static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
    static final String EXTRA_TOKEN = "token";
    private static final int RECENTLY_RECEIVED_MESSAGE_IDS_MAX_SIZE = 10;
    private static final Queue<String> recentlyReceivedMessageIds = null;
    private Rpc rpc;

    static {
        recentlyReceivedMessageIds = new ArrayDeque(10);
    }

    public FirebaseMessagingService() {
    }

    private boolean alreadyReceivedMessage(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return false;
    L5:
        Queue<String> r3 = recentlyReceivedMessageIds;
        if (r3.contains(r4) == false) goto L13;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L17;
        Log.d("FirebaseMessaging", "Received duplicate message: " + r4);
        return true;
    L17:
        return true;
    L13:
        if (r3.size() < 10) goto L15;
        r3.remove();
    L15:
        r3.add(r4);
        return false;
    }

    private void dispatchMessage(Intent r5) {
        Bundle r0 = r5.getExtras();
        if (r0 != null) goto L5;
        r0 = new Bundle();
    L5:
        r0.remove("androidx.content.wakelockid");
        if (NotificationParams.isNotification(r0) == false) goto L18;
        NotificationParams r1 = new NotificationParams(r0);
        ExecutorService r2 = FcmExecutors.newNetworkIOExecutor();
        if (new DisplayNotification(this, r1, r2).handleNotification() == false) goto L12;
        r2.shutdown();
        return;
    L12:
        r2.shutdown();
        if (MessagingAnalytics.shouldUploadScionMetrics(r5) == false) goto L18;
        MessagingAnalytics.logNotificationForeground(r5);
    L15:
        th = move-exception;
        r2.shutdown();
        throw th;
    L18:
        onMessageReceived(new RemoteMessage(r0));
    }

    private String getMessageId(Intent r1) {
        String r0 = r1.getStringExtra("google.message_id");
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1.getStringExtra("message_id");
    }

    private Rpc getRpc(Context r2) {
        if (this.rpc != null) goto L6;
        this.rpc = new Rpc(r2.getApplicationContext());
    L6:
        return this.rpc;
    }

    private void handleMessageIntent(Intent r2) {
        if (alreadyReceivedMessage(r2.getStringExtra("google.message_id")) == true) goto L5;
        passMessageIntentToSdk(r2);
    L5:
        getRpc(this).messageHandled(new CloudMessage(r2));
    }

    private void passMessageIntentToSdk(Intent r5) {
        String r0 = r5.getStringExtra("message_type");
        if (r0 != null) goto L5;
        r0 = "gcm";
    L5:
        char r3 = 65535;
        switch(r0.hashCode()) {
            case -2062414158: goto L21;
            case 102161: goto L17;
            case 814694033: goto L13;
            case 814800675: goto L9;
            default: goto L24;
        };
    L24:
        switch(r3) {
            case 0: goto L33;
            case 1: goto L31;
            case 2: goto L29;
            case 3: goto L27;
            default: goto L25;
        };
    L25:
        Log.w("FirebaseMessaging", "Received message with unknown type: ".concat(r0));
        return;
    L27:
        onMessageSent(r5.getStringExtra("google.message_id"));
        return;
    L29:
        onSendError(getMessageId(r5), new SendException(r5.getStringExtra("error")));
        return;
    L31:
        MessagingAnalytics.logNotificationReceived(r5);
        dispatchMessage(r5);
        return;
    L33:
        onDeletedMessages();
        return;
    L9:
        if (r0.equals("send_event") == false) goto L24;
        r3 = 3;
        goto L24
    L13:
        if (r0.equals("send_error") == false) goto L24;
        r3 = 2;
        goto L24
    L17:
        if (r0.equals("gcm") == false) goto L24;
        r3 = 1;
        goto L24
    L21:
        if (r0.equals("deleted_messages") == false) goto L24;
        r3 = 0;
        goto L24
    }

    public static void resetForTesting() {
        recentlyReceivedMessageIds.clear();
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public Intent getStartCommandIntent(Intent r1) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public void handleIntent(Intent r4) {
        String r0 = r4.getAction();
        if (ACTION_REMOTE_INTENT.equals(r0) == false) goto L5;
    L21:
        handleMessageIntent(r4);
        return;
    L5:
        if (ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(r0) == true) goto L21;
        if (ACTION_NEW_TOKEN.equals(r0) == false) goto L12;
        onNewToken(r4.getStringExtra(EXTRA_TOKEN));
        return;
    L12:
        if (ACTION_FCM_REGISTERED.equals(r0) == false) goto L16;
        onRegistered(r4.getStringExtra(EXTRA_TOKEN));
        return;
    L16:
        if (ACTION_FCM_UNREGISTERED.equals(r0) == false) goto L19;
        onUnregistered(r4.getStringExtra(EXTRA_TOKEN));
        return;
    L19:
        Log.d("FirebaseMessaging", "Unknown intent action: " + r4.getAction());
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage r1) {
    }

    @Deprecated
    public void onMessageSent(String r1) {
    }

    @Deprecated
    public void onNewToken(String r1) {
    }

    public void onRegistered(String r1) {
    }

    @Deprecated
    public void onSendError(String r1, Exception r2) {
    }

    public void onUnregistered(String r1) {
    }

    public void setRpcForTesting(Rpc r1) {
        this.rpc = r1;
    }
}
