package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbd implements Callable {
    public final /* synthetic */ zzbf zza;

    public /* synthetic */ zzbd(zzbf r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        zzbf.zza(this.zza);
        return null;
    }
}
