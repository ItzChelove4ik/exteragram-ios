package com.exteragram.messenger.debug;

import com.exteragram.messenger.debug.HeapMonitor;

/* loaded from: classes4.dex */
public final /* synthetic */ class HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ HeapMonitor.LimitWatcher f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4(HeapMonitor.LimitWatcher r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HeapMonitor.LimitWatcher.m1248$r8$lambda$i0uVHpCZaV6c3J4CwvOvyrTDcQ(this.f$0, this.f$1);
    }
}
