package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class AppStartReceiver extends BroadcastReceiver {
    public static /* synthetic */ void $r8$lambda$7gvKcWEaIWxxezhCcVhOtGEPMng() {
        SharedConfig.loadConfig();
        if (SharedConfig.passcodeHash.length() <= 0) goto L5;
        SharedConfig.appLocked = true;
        SharedConfig.saveConfig();
    L5:
        ApplicationLoader.startPushService();
    }

    public AppStartReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if ("android.intent.action.BOOT_COMPLETED".equals(r2.getAction()) == false) goto L8;
        AndroidUtilities.runOnUIThread(new AppStartReceiver$$ExternalSyntheticLambda0());
        return;
    }
}
