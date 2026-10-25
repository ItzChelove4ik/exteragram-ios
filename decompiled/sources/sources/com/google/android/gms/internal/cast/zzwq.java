package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zzwq extends zzwh implements ScheduledFuture, ListenableFuture {
    private final ScheduledFuture zza;

    public zzwq(ListenableFuture r1, ScheduledFuture r2) {
        super(r1);
        this.zza = r2;
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean r2) {
        boolean r0 = zzb().cancel(r2);
        if (r0 == false) goto L5;
        this.zza.cancel(r2);
    L5:
        return r0;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Delayed r1) {
        return this.zza.compareTo(r1);
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit r1) {
        return this.zza.getDelay(r1);
    }
}
