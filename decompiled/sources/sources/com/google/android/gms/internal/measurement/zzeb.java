package com.google.android.gms.internal.measurement;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
final class zzeb implements ThreadFactory {
    private final ThreadFactory zza;

    public zzeb(zzez r1) {
        Objects.requireNonNull(r1);
        this.zza = Executors.defaultThreadFactory();
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r1) {
        Thread r0 = this.zza.newThread(r1);
        r0.setName("ScionFrontendApi");
        return r0;
    }
}
