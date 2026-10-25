package com.google.android.gms.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes4.dex */
final class zzak extends BroadcastReceiver {
    private zzak() {
        throw null;
    }

    public /* synthetic */ zzak(byte[] r1) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r1, Intent r2) {
        if ("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT".equals(r2.getAction()) == false) goto L7;
        CastRemoteDisplayLocalService.stopService();
        return;
    L7:
        if ("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED".equals(r2.getAction()) == false) goto L10;
        CastRemoteDisplayLocalService.zzb(false);
        return;
    }
}
