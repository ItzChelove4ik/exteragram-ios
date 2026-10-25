package com.google.android.gms.internal.measurement;

import java.util.WeakHashMap;

/* loaded from: classes4.dex */
final class zzvx extends ThreadLocal {
    public zzvx() {
    }

    @Override // java.lang.ThreadLocal
    public final /* bridge */ /* synthetic */ Object initialValue() {
        zzwq r3 = new zzwq(zzrn.zza(Thread.currentThread()));
        Thread r0 = Thread.currentThread();
        WeakHashMap r1 = zzvy.zze();
        monitor-enter(r1);
        zzvy.zze().put(r0, r3);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return r3;
    L7:
        th = move-exception;
        throw th;
    }
}
