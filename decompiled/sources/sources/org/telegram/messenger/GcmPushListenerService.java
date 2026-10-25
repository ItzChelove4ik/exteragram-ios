package org.telegram.messenger;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

/* loaded from: classes.dex */
public class GcmPushListenerService extends FirebaseMessagingService {
    public static /* synthetic */ void $r8$lambda$yff5x9Kir9GwH0krOiFRPGGXb_U(String r2) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("Refreshed FCM token: " + r2);
    L5:
        ApplicationLoader.postInitApplication();
        PushListenerController.sendRegistrationToServer(2, r2);
    }

    public GcmPushListenerService() {
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage r5) {
        String r4 = r5.getFrom();
        Map<String, String> r0 = r5.getData();
        long r1 = r5.getSentTime();
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("FCM received data: " + r0 + " from: " + r4);
    L5:
        PushListenerController.processRemoteMessage(2, r0.get("p"), r1);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String r1) {
        AndroidUtilities.runOnUIThread(new GcmPushListenerService$$ExternalSyntheticLambda0(r1));
    }
}
