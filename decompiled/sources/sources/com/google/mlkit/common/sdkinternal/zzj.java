package com.google.mlkit.common.sdkinternal;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzj implements ThreadFactory {
    public final /* synthetic */ ThreadFactory zza;

    public /* synthetic */ zzj(ThreadFactory r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r2) {
        zzk r0 = new zzk(r2);
        return this.zza.newThread(r0);
    }
}
