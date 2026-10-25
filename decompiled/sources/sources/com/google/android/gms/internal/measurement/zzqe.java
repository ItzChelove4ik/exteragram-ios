package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public final class zzqe {
    private final Context zza;
    private final Supplier zzb;
    private final Supplier zzc;
    private final Supplier zzd;
    private volatile int zze;
    private final CopyOnWriteArrayList zzf;
    private final Object zzg;
    private volatile ListenableFuture zzh;

    public zzqe(Context r2, Supplier r3, Supplier r4, Supplier r5) {
        this.zze = 0;
        this.zzf = new CopyOnWriteArrayList();
        this.zzg = new Object();
        this.zzh = null;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    public final ListenableFuture zza(zzabz r5, boolean r6, zzqc r7) {
        zzqm r0 = (zzqm) this.zzc.get();
        if (r0 != null) goto L7;
        if (r6 == true) goto L7;
        return Futures.immediateVoidFuture();
    L7:
        int r52 = r5.zza();
        Preconditions.checkArgument(true);
        int r53 = 1 << r52;
        if ((this.zze & r53) != 0) goto L20;
        CopyOnWriteArrayList r62 = this.zzf;
        monitor-enter(r62);
        int r1 = this.zze;     // Catch: Throwable -> L14
        if ((r1 & r53) != 0) goto L16;
        r62.add(r7);     // Catch: Throwable -> L14
        this.zze = r53 | r1;     // Catch: Throwable -> L14
    L16:
        monitor-exit(r62);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    L20:
        ListenableFuture r54 = this.zzh;
        if (r54 != null) goto L39;
        Object r63 = this.zzg;
        monitor-enter(r63);
        ListenableFuture r55 = this.zzh;     // Catch: Throwable -> L28
        if (r55 != null) goto L35;
        if (r0 != null) goto L30;
        r0 = zzqb.zza;     // Catch: Throwable -> L28
    L30:
        Context r56 = this.zza;     // Catch: Throwable -> L28
        if (zzky.zzb(r56) == false) goto L33;
        zzpz r72 = zzpz.zza;     // Catch: Throwable -> L28
        Supplier r12 = this.zzb;     // Catch: Throwable -> L28
        Executor r2 = (Executor) r12.get();     // Catch: Throwable -> L28
        r55 = Futures.transformAsync(zzky.zzd(r56, Executors.callable(r72, null), r2), new zzqa(this, r0), (Executor) r12.get());     // Catch: Throwable -> L28
        this.zzh = r55;     // Catch: Throwable -> L28
    L34:
        r55.addListener(new zzpy(r55), (Executor) this.zzb.get());     // Catch: Throwable -> L28
        goto L35
    L33:
        r55 = (ListenableFuture) Preconditions.checkNotNull(((zzmj) this.zzd.get()).zze(new zzqd(this, r0)));     // Catch: Throwable -> L28
        this.zzh = r55;     // Catch: Throwable -> L28
    L35:
        monitor-exit(r63);     // Catch: Throwable -> L28
        return r55;
    L28:
        th = move-exception;
        throw th;
    L39:
        return r54;
    }

    public final /* synthetic */ ListenableFuture zzb(zzqm r2, Void r3) {
        return (ListenableFuture) Preconditions.checkNotNull(((zzmj) this.zzd.get()).zze(new zzqd(this, r2)));
    }

    public final /* synthetic */ CopyOnWriteArrayList zzc() {
        return this.zzf;
    }
}
