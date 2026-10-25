package com.google.android.gms.internal.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzei extends BroadcastReceiver {
    final /* synthetic */ zzek zza;

    public zzei(zzek r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r1, Intent r2) {
        this.zza.zzf();
    }
}
