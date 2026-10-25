package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class RefererReceiver extends BroadcastReceiver {
    public RefererReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        MessagesController.getInstance(UserConfig.selectedAccount).setReferer(r2.getExtras().getString("referrer"));     // Catch: Exception -> L4
        return;
    }
}
