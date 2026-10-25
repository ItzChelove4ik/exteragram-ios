package com.google.android.gms.internal.measurement;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes4.dex */
final /* synthetic */ class zzlo implements ThreadFactory {
    static final /* synthetic */ zzlo zza = null;

    static {
        zza = new zzlo();
    }

    private /* synthetic */ zzlo() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final /* synthetic */ Thread newThread(Runnable r2) {
        int r1 = zzlk.$r8$clinit;
        return new Thread(r2, "ProcessStablePhenotypeFlag");
    }
}
