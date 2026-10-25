package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class zzui implements zzuv {
    private final String zza;
    private final ListenableFuture zzb;
    private final zztv zzc;
    private final Executor zzd;
    private final zzru zze;
    private final Optional zzf;
    private final zzwb zzg;
    private final Object zzh;
    private final ExecutionSequencer zzi;
    private ListenableFuture zzj;

    public zzui(String r2, ListenableFuture r3, zztv r4, Executor r5, zzru r6, Optional r7, zzwb r8) {
        this.zzh = new Object();
        this.zzi = ExecutionSequencer.create();
        this.zzj = null;
        this.zza = r2;
        this.zzb = Futures.nonCancellationPropagating(r3);
        this.zzc = r4;
        this.zzd = MoreExecutors.newSequentialExecutor(r5);
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
    }

    public static zzuw zza() {
        return zztx.zzc();
    }

    private final Object zzm(Uri r6) {
        zzwb r1 = this.zzg;     // Catch: IOException -> L13 FileNotFoundException -> L15
        String r2 = this.zza;     // Catch: IOException -> L13 FileNotFoundException -> L15
        StringBuilder r4 = new StringBuilder(String.valueOf(r2).length() + 5);     // Catch: IOException -> L13 FileNotFoundException -> L15
        r4.append("Read ");     // Catch: IOException -> L13 FileNotFoundException -> L15
        r4.append(r2);     // Catch: IOException -> L13 FileNotFoundException -> L15
        zzwi r0 = r1.zza(r4.toString(), zzxd.zza);     // Catch: IOException -> L13 FileNotFoundException -> L15
        InputStream r12 = (InputStream) this.zze.zza(r6, zzst.zzb());     // Catch: Throwable -> L9
        zztv r22 = this.zzc;     // Catch: Throwable -> L17
        zzafc r23 = (zzafc) ((zzve) r22).zzb().zzcj().zza(r12, ((zzve) r22).zzc());     // Catch: Throwable -> L17
        if (r12 == null) goto L11;
        r12.close();     // Catch: Throwable -> L9
    L11:
        r0.close();     // Catch: IOException -> L13 FileNotFoundException -> L15
        return r23;
    L17:
        th = move-exception;
        if (r12 != null) goto L37;
    L23:
        throw th;     // Catch: Throwable -> L9
    L37:
        r12.close();     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L26
    L28:
        throw th;     // Catch: IOException -> L13 FileNotFoundException -> L15
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L13 FileNotFoundException -> L15
    L13:
        e = move-exception;
        throw zzux.zza(this.zze, r6, e, this.zza);
    L15:
        e = move-exception;
        if (this.zze.zzc(r6) == false) goto L32;
        throw e;     // Catch: IOException -> L13
    L32:
        return this.zzc.zza();
    }

    private final void zzn(Uri r8, Object r9) {
        Uri r0 = zzuz.zza(r8, ".tmp");
        zzwb r2 = this.zzg;     // Catch: IOException -> L16
        String r3 = this.zza;     // Catch: IOException -> L16
        StringBuilder r5 = new StringBuilder(String.valueOf(r3).length() + 6);     // Catch: IOException -> L16
        r5.append("Write ");     // Catch: IOException -> L16
        r5.append(r3);     // Catch: IOException -> L16
        zzwi r1 = r2.zza(r5.toString(), zzxd.zza);     // Catch: IOException -> L16
        zzse r22 = new zzse();     // Catch: Throwable -> L10
        zzru r32 = this.zze;     // Catch: Throwable -> L10 IOException -> L12
        zzsw r4 = zzsw.zzb();     // Catch: Throwable -> L10 IOException -> L12
        r4.zzc(new zzro[]{r22});     // Catch: Throwable -> L10 IOException -> L12
        OutputStream r33 = (OutputStream) r32.zza(r0, r4);     // Catch: Throwable -> L10 IOException -> L12
        ((zzafc) r9).zzce(r33);     // Catch: Throwable -> L18
        r22.zzc();     // Catch: Throwable -> L18
        if (r33 == null) goto L14;
        r33.close();     // Catch: Throwable -> L10 IOException -> L12
    L14:
        r1.close();     // Catch: IOException -> L16
        this.zze.zzd(r0, r8);     // Catch: IOException -> L16
        return;
    L18:
        th = move-exception;
        if (r33 != null) goto L43;
    L24:
        throw th;     // Catch: Throwable -> L10 IOException -> L12
    L43:
        r33.close();     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L10 IOException -> L12
    L12:
        e = move-exception;
        throw zzux.zza(this.zze, r8, e, this.zza);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L29
    L31:
        throw th;     // Catch: IOException -> L16
    L29:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L16
    L16:
        e = move-exception;
        zzru r7 = this.zze;
        if (r7.zzc(r0) == true) goto L48;
    L38:
        throw e;
    L48:
        r7.zzb(r0);     // Catch: IOException -> L36
    L36:
        e = move-exception;
        e.addSuppressed(e);
        goto L38
    }

    @Override // com.google.android.gms.internal.measurement.zzuv
    public final ListenableFuture zzb(AsyncFunction r4, Executor r5, zzuu r6) {
        Object r62 = this.zzh;
        monitor-enter(r62);
        ListenableFuture r0 = this.zzj;     // Catch: Throwable -> L10
        if (r0 == null) goto L15;
        if (r0.isDone() == false) goto L15;
        Futures.getDone(this.zzj);     // Catch: Throwable -> L10 ExecutionException -> L12
    L13:
        this.zzj = null;     // Catch: Throwable -> L10
    L15:
        if (this.zzj != null) goto L17;
        this.zzj = Futures.nonCancellationPropagating(this.zzi.submitAsync(zzxa.zzb(new zzub(this)), this.zzd));     // Catch: Throwable -> L10
    L17:
        ListenableFuture r02 = this.zzj;     // Catch: Throwable -> L10
        monitor-exit(r62);     // Catch: Throwable -> L10
        return this.zzi.submitAsync(zzxa.zzb(new zztz(this, r02, r4, r5)), MoreExecutors.directExecutor());
    L10:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.measurement.zzuv
    public final String zzc() {
        return this.zza;
    }

    public final /* synthetic */ ListenableFuture zzd() {
        AsyncFunction r0 = zzxa.zzc(new zzug(this));
        Executor r1 = this.zzd;
        return Futures.nonCancellationPropagating(Futures.transformAsync(this.zzb, r0, r1));
    }

    public final /* synthetic */ ListenableFuture zze(Object r1) {
        Object r12 = this.zzh;
        monitor-enter(r12);
        ListenableFuture r0 = this.zzj;     // Catch: Throwable -> L7
        monitor-exit(r12);     // Catch: Throwable -> L7
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ ListenableFuture zzf() {
        return Futures.immediateFuture(zzm((Uri) Futures.getDone(this.zzb)));
    L5:
        e = move-exception;
        zzty r1 = new zzty(this, null);
        Optional r2 = this.zzf;
        if (r2.isPresent() == true) goto L10;
        return Futures.immediateFailedFuture(e);
    L10:
        if ((e instanceof zzsg) == true) goto L16;
        if ((e.getCause() instanceof zzsg) == true) goto L16;
        return Futures.transformAsync(((zztf) r2.get()).zza(e, r1), zzxa.zzc(new zzue(this)), this.zzd);
    L16:
        return Futures.immediateFailedFuture(e);
    }

    public final /* synthetic */ ListenableFuture zzg(ListenableFuture r2, ListenableFuture r3, Object r4) {
        if (Futures.getDone(r2).equals(Futures.getDone(r3)) == true) goto L5;
        ListenableFuture r22 = Futures.transformAsync(r3, zzxa.zzc(new zzud(this, r3)), this.zzd);
        Object r1 = this.zzh;
        monitor-enter(r1);
        monitor-exit(r1);     // Catch: Throwable -> L10
        return r22;
    L10:
        th = move-exception;
        throw th;
    L5:
        return Futures.immediateFuture(r4);
    }

    public final /* synthetic */ ListenableFuture zzh(ListenableFuture r2, Object r3) {
        zzn((Uri) Futures.getDone(this.zzb), r3);
        Object r0 = this.zzh;
        monitor-enter(r0);
        this.zzj = r2;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return Futures.immediateFuture(r3);
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ ListenableFuture zzi(Void r1) {
        return Futures.immediateFuture(zzm((Uri) Futures.getDone(this.zzb)));
    }

    public final /* synthetic */ ListenableFuture zzj(Object r2) {
        zzn((Uri) Futures.getDone(this.zzb), r2);
        return Futures.immediateVoidFuture();
    }

    public final /* synthetic */ ListenableFuture zzk(Uri r3) {
        Uri r0 = zzuz.zza(r3, ".bak");
        zzru r2 = this.zze;     // Catch: IOException -> L8
        if (r2.zzc(r0) == false) goto L7;
        r2.zzd(r0, r3);     // Catch: IOException -> L8
    L7:
        return Futures.immediateVoidFuture();
    L8:
        e = move-exception;
        return Futures.immediateFailedFuture(e);
    }

    public final /* synthetic */ ListenableFuture zzl(ListenableFuture r2) {
        return Futures.transformAsync(r2, zzxa.zzc(new zzuf(this)), this.zzd);
    }
}
