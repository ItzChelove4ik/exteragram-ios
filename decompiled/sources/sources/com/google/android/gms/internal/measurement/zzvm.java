package com.google.android.gms.internal.measurement;

import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public final class zzvm {
    private final zzvg zza;
    private final AtomicLong zzb;
    private final AtomicReference zzc;
    private final AtomicReference zzd;
    private final Executor zze;
    private final SettableFuture zzf;

    public zzvm(AsyncCallable r4, Executor r5) {
        this.zzb = new AtomicLong(zzi(Integer.MIN_VALUE, Integer.MIN_VALUE));
        this.zzc = new AtomicReference(null);
        this.zzd = new AtomicReference(null);
        this.zze = MoreExecutors.newSequentialExecutor(MoreExecutors.directExecutor());
        SettableFuture r0 = SettableFuture.create();
        this.zzf = r0;
        zzvg r1 = new zzvg(r4, r5);
        this.zza = r1;
        r0.addListener(r1, MoreExecutors.directExecutor());
    }

    private final ListenableFuture zzh(int r7) {
        AtomicLong r0 = this.zzb;
        if (((int) (r0.get() >>> 32)) > r7) goto L5;
        zzvl r1 = new zzvl(r7);
    L7:
        AtomicReference r2 = this.zzc;
        zzvl r4 = (zzvl) r2.get();
        if (r4 == null) goto L15;
        if (r4.zza() <= r7) goto L15;
        return Futures.immediateCancelledFuture();
    L15:
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r2, r4, r1) == false) goto L7;
        if (((int) (r0.get() >>> 32)) <= r7) goto L20;
        r1.cancel(true);
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r2, r1, null);
        return r1;
    L20:
        zzvg r72 = this.zza;
        AsyncCallable r02 = r72.zza();
        if (r02 == null) goto L27;
        Executor r73 = r72.zzb();
        if (r73 == null) goto L27;
        r1.setFuture(Futures.submitAsync(zzxa.zzb(r02), r73));
        return r1;
    L27:
        r1.setFuture(this.zzf);
        return r1;
    L5:
        return Futures.immediateCancelledFuture();
    }

    private static long zzi(int r4, int r5) {
        long r0 = r4;
        return (r5 & 4294967295L) | (r0 << 32);
    }

    public final ListenableFuture zza() {
        SettableFuture r0 = this.zzf;
        if (r0.isDone() == true) goto L14;
    L4:
        AtomicLong r02 = this.zzb;
        long r1 = r02.get();
        int r3 = (int) (r1 >>> 32);
        if (r02.compareAndSet(r1, zzi(r3, ((int) r1) + 1)) == false) goto L4;
        AtomicReference r03 = this.zzd;
        SettableFuture r12 = SettableFuture.create();
        ListenableFuture r04 = (ListenableFuture) r03.getAndSet(r12);
        if (r04 != null) goto L9;
        ListenableFuture r05 = Futures.submitAsync(zzxa.zzb(new zzvi(this, r3)), MoreExecutors.directExecutor());
    L10:
        r12.setFuture(r05);
        zzvk r06 = new zzvk(this, r3, null);
        r12.addListener(new zzvj(this, r12, r06), MoreExecutors.directExecutor());
        return r06;
    L9:
        r05 = Futures.catchingAsync(r04, Throwable.class, zzxa.zzc(new zzvh(this, r3)), this.zze);
        goto L10
    L14:
        return r0;
    }

    public final /* synthetic */ void zzb(SettableFuture r1, zzvk r2) {
        Object r12 = Futures.getDone(r1);     // Catch: Throwable -> L5
        SettableFuture r0 = this.zzf;
        r0.set(r12);
        r2.setFuture(r0);
        return;
    L5:
        r2.setFuture(r1);
    }

    public final /* synthetic */ ListenableFuture zzc(int r1, Throwable r2) {
        return zzh(r1);
    }

    public final /* synthetic */ ListenableFuture zzd(int r1) {
        return zzh(r1);
    }

    public final /* synthetic */ boolean zze() {
    L2:
        AtomicLong r0 = this.zzb;
        long r1 = r0.get();
        int r3 = (int) r1;
        long r4 = r1 >>> 32;
        if (r3 == Integer.MIN_VALUE) goto L13;
        int r42 = (int) r4;
        if (r3 != (-2147483647)) goto L7;
        boolean r5 = true;
    L8:
        if (r5 == false) goto L11;
        r42 = r42 + 1;
    L11:
        if (r0.compareAndSet(r1, zzi(r42, r3 - 1)) == false) goto L2;
        return r5;
    L7:
        r5 = false;
        goto L8
    L13:
        StringBuilder r32 = new StringBuilder(String.valueOf(r1).length() + 13);
        r32.append("Refcount is: ");
        r32.append(r1);
        throw new AssertionError(r32.toString());
    }

    public final /* synthetic */ zzvg zzf() {
        return this.zza;
    }

    public final /* synthetic */ AtomicReference zzg() {
        return this.zzc;
    }
}
