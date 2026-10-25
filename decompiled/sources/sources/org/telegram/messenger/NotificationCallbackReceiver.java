package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class NotificationCallbackReceiver extends BroadcastReceiver {
    public NotificationCallbackReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r5, Intent r6) {
        if (r6 == null) goto L9;
        ApplicationLoader.postInitApplication();
        int r4 = r6.getIntExtra("currentAccount", UserConfig.selectedAccount);
        if (UserConfig.isValidAccount(r4) == true) goto L7;
        return;
    L7:
        long r0 = r6.getLongExtra("did", 777000);
        byte[] r52 = r6.getByteArrayExtra("data");
        int r62 = r6.getIntExtra("mid", 0);
        SendMessagesHelper.getInstance(r4).sendNotificationCallback(r0, r62, r52);
        return;
    }
}
