package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzb implements Runnable {
    final /* synthetic */ LifecycleCallback zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzc zzc;

    public zzb(zzc r1, LifecycleCallback r2, String r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzc r0 = this.zzc;
        if (r0.zzm() <= 0) goto L10;
        LifecycleCallback r1 = this.zza;
        if (r0.zzn() == null) goto L7;
        String r2 = this.zzb;
        Bundle r22 = r0.zzn().getBundle(r2);
    L8:
        r1.onCreate(r22);
        goto L10
    L7:
        r22 = null;
    L10:
        if (r0.zzm() < 2) goto L13;
        this.zza.onStart();
    L13:
        if (r0.zzm() < 3) goto L16;
        this.zza.onResume();
    L16:
        if (r0.zzm() < 4) goto L19;
        this.zza.onStop();
    L19:
        if (r0.zzm() < 5) goto L22;
        this.zza.onDestroy();
        return;
    }
}
