package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.CloudMessagingReceiver;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.FcmBroadcastProcessor;
import com.google.firebase.messaging.MessagingAnalytics;

/* loaded from: classes.dex */
public final class FirebaseInstanceIdReceiver extends CloudMessagingReceiver {
    public FirebaseInstanceIdReceiver() {
    }

    private static Intent createServiceIntent(Context r0, String r1, Bundle r2) {
        return new Intent(r1).putExtras(r2);
    }

    @Override // com.google.android.gms.cloudmessaging.CloudMessagingReceiver
    public int onMessageReceive(Context r1, CloudMessage r2) {
        return ((Integer) Tasks.await(new FcmBroadcastProcessor(r1).process(r2.getIntent()))).intValue();
    L4:
        e = move-exception;
        Log.e("FirebaseMessaging", "Failed to send message to service.", e);
        return MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
    }

    @Override // com.google.android.gms.cloudmessaging.CloudMessagingReceiver
    public void onNotificationDismissed(Context r1, Bundle r2) {
        Intent r0 = createServiceIntent(r1, "com.google.firebase.messaging.NOTIFICATION_DISMISS", r2);
        if (MessagingAnalytics.shouldUploadScionMetrics(r0) == false) goto L6;
        MessagingAnalytics.logNotificationDismiss(r0);
        return;
    }
}
