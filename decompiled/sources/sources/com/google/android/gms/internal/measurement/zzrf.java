package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.io.IOException;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzrf {
    private static final Object zza = null;
    private static final Object zzb = null;
    private final Context zzc;
    private final Supplier zzd;
    private final Supplier zze;
    private final Supplier zzf;
    private final Supplier zzg;
    private final Supplier zzh;
    private final Uri zzi;
    private volatile zzni zzj;
    private final Uri zzk;
    private volatile zznk zzl;

    static {
        zza = new Object();
        zzb = new Object();
    }

    public zzrf(Context r2, Supplier r3, Supplier r4, Supplier r5) {
        this.zzc = r2;
        this.zze = r3;
        this.zzd = r5;
        this.zzf = r4;
        zzrz r42 = zzsa.zza(r2);
        r42.zzb("phenotype_storage_info");
        r42.zzc("storage-info.pb");
        this.zzi = r42.zzd();
        zzrz r22 = zzsa.zza(r2);
        r22.zzb("phenotype_storage_info");
        r22.zzc("device-encrypted-storage-info.pb");
        if (zzky.zza() == false) goto L5;
        r22.zza();
    L5:
        this.zzk = r22.zzd();
        this.zzg = Suppliers.memoize(new zzre(this));
        this.zzh = Suppliers.memoize(new zzqy(r3));
    }

    private final zzni zzg() {
        zzni r0 = this.zzj;
        if (r0 != null) goto L24;
        Object r1 = zza;
        monitor-enter(r1);
        zzni r02 = this.zzj;     // Catch: Throwable -> L13
        if (r02 != null) goto L20;
        r02 = zzni.zzp();     // Catch: Throwable -> L13
        if (zzky.zzc(this.zzc) == false) goto L20;
        zzss r2 = zzss.zzb(r02);     // Catch: Throwable -> L13
        StrictMode.ThreadPolicy r3 = StrictMode.getThreadPolicy();     // Catch: Throwable -> L13
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(r3).permitDiskReads().build());     // Catch: Throwable -> L13
        zzni r22 = (zzni) ((zzru) this.zzf.get()).zza(this.zzi, r2);     // Catch: Throwable -> L15 IOException -> L18
        StrictMode.setThreadPolicy(r3);     // Catch: Throwable -> L13
        r02 = r22;
    L19:
        this.zzj = r02;     // Catch: Throwable -> L13
        goto L20
    L15:
        th = move-exception;
        StrictMode.setThreadPolicy(r3);     // Catch: Throwable -> L13
        throw th;     // Catch: Throwable -> L13
    L18:
        StrictMode.setThreadPolicy(r3);     // Catch: Throwable -> L13
    L20:
        monitor-exit(r1);     // Catch: Throwable -> L13
        return r02;
    L13:
        th = move-exception;
        throw th;
    L24:
        return r0;
    }

    public final ListenableFuture zza() {
        if (zzky.zzb(this.zzc) == true) goto L10;
        if ((zzg().zze() + DurationKt.MILLIS_IN_DAY) >= System.currentTimeMillis()) goto L10;
        ListeningScheduledExecutorService r0 = (ListeningScheduledExecutorService) Preconditions.checkNotNull((ListeningScheduledExecutorService) this.zze.get());
        return FluentFuture.from(Futures.nonCancellationPropagating((ListenableFuture) this.zzh.get())).transformAsync(new zzrc(this), r0);
    L10:
        return Futures.immediateVoidFuture();
    }

    public final boolean zzb(boolean r1, zzabz r2) {
        zzni r0 = zzg();
        if (r0.zzc() == true) goto L5;
        return false;
    L5:
        if (r0.zzi().contains(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final zzqn zzc(boolean r13) {
        zzni r12 = zzg();
        boolean r1 = r12.zzc();
        ImmutableList r2 = ImmutableList.copyOf(r12.zzi());
        zzacr r3 = r12.zzb();
        String r4 = r12.zzd();
        ImmutableList r6 = ImmutableList.copyOf(r12.zzf());
        ImmutableList r7 = ImmutableList.copyOf(r12.zzh());
        if (r12.zzj() == true) goto L5;
    L8:
        String r132 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        return new zzqn(r1, r2, r3, r4, r132, r6, r7, r12.zza(), r12.zzm(), r12.zzl(), r12.zzn());
    L5:
        if (r12.zzk().zzb() != Build.VERSION.SDK_INT) goto L8;
        r132 = r12.zzk().zza();
        goto L10
    }

    public final /* synthetic */ ListenableFuture zzd() {
        ListeningScheduledExecutorService r0 = (ListeningScheduledExecutorService) Preconditions.checkNotNull((ListeningScheduledExecutorService) this.zze.get());
        FluentFuture r4 = FluentFuture.from(((zzmj) Preconditions.checkNotNull((zzmj) this.zzd.get())).zzd()).catching(zzmk.class, zzqz.zza, r0).transform(new zzra(this), r0);
        r4.addListener(new zzrb(r4), r0);
        return r4;
    }

    public final /* synthetic */ Void zze(zzno r10) {
        zzse r0 = new zzse();
        StrictMode.ThreadPolicy r1 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(r1).permitDiskWrites().build());
        Object r2 = zza;     // Catch: Throwable -> L16 IOException -> L18
        monitor-enter(r2);     // Catch: Throwable -> L16 IOException -> L18
        zzru r3 = (zzru) this.zzf.get();     // Catch: Throwable -> L20
        Uri r4 = this.zzi;     // Catch: Throwable -> L20
        zzsv r5 = zzsv.zzb(r10.zza());     // Catch: Throwable -> L20
        r5.zzc(new zzro[]{r0});     // Catch: Throwable -> L20
        r3.zza(r4, r5);     // Catch: Throwable -> L20
        this.zzj = r10.zza();     // Catch: Throwable -> L20
        monitor-exit(r2);     // Catch: Throwable -> L20
        Object r22 = zzb;     // Catch: Throwable -> L16 IOException -> L18
        monitor-enter(r22);     // Catch: Throwable -> L16 IOException -> L18
        zzru r32 = (zzru) this.zzf.get();     // Catch: Throwable -> L13
        Uri r42 = this.zzk;     // Catch: Throwable -> L13
        zzsv r52 = zzsv.zzb(r10.zzb());     // Catch: Throwable -> L13
        r52.zzc(new zzro[]{r0});     // Catch: Throwable -> L13
        r32.zza(r42, r52);     // Catch: Throwable -> L13
        this.zzl = r10.zzb();     // Catch: Throwable -> L13
        monitor-exit(r22);     // Catch: Throwable -> L13
        StrictMode.setThreadPolicy(r1);
        return null;
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16 IOException -> L18
    L20:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16 IOException -> L18
    L18:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        StrictMode.setThreadPolicy(r1);
        throw th;
    }

    public final /* synthetic */ ListenableFuture zzf(Void r1) {
        return Futures.nonCancellationPropagating((ListenableFuture) this.zzg.get());
    }
}
