package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.telegram.tgnet.ConnectionsManager;

/* loaded from: classes.dex */
public class ScreenReceiver extends BroadcastReceiver {
    public ScreenReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r2, Intent r3) {
        if (r3.getAction().equals("android.intent.action.SCREEN_OFF") == false) goto L9;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("screen off");
    L7:
        ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(true, true);
        ApplicationLoader.isScreenOn = false;
    L14:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.screenStateChanged, new Object[0]);
        return;
    L9:
        if (r3.getAction().equals("android.intent.action.SCREEN_ON") == false) goto L14;
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.d("screen on");
    L13:
        ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(false, true);
        ApplicationLoader.isScreenOn = true;
        goto L14
    }
}
