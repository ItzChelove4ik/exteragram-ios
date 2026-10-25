package com.google.android.gms.internal.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzec extends BroadcastReceiver {
    final /* synthetic */ zzee zza;

    public /* synthetic */ zzec(zzee r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r1, Intent r2) {
        String r12 = r2.getAction();
        if (r12 != null) goto L6;
        return;
    L6:
        if (r12.equals("android.intent.action.SCREEN_ON") == false) goto L10;
        this.zza.zzd();
        return;
    L10:
        if (r12.equals("android.intent.action.SCREEN_OFF") == false) goto L13;
        this.zza.zzc();
        return;
    }
}
