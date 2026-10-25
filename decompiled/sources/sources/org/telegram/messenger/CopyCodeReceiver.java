package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class CopyCodeReceiver extends BroadcastReceiver {
    public CopyCodeReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        AndroidUtilities.addToClipboard(r2.getStringExtra("text"));
    }
}
