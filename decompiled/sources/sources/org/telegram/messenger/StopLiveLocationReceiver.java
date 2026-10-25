package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class StopLiveLocationReceiver extends BroadcastReceiver {
    public StopLiveLocationReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        LocationController.getInstance(r0).removeAllLocationSharings();
        r0 = r0 + 1;
        goto L4
    }
}
