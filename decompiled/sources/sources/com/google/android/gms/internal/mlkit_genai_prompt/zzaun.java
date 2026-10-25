package com.google.android.gms.internal.mlkit_genai_prompt;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import com.google.mlkit.genai.common.internal.GenAiUtils;
import j$.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public abstract class zzaun implements AutoCloseable {
    private final zzdi zza;
    private final int zzb;
    private final zzaty zzc;
    private volatile zzdq zzd;
    private zzdp zze;
    private boolean zzf;

    public zzaun(ExecutorService r3, int r4, int r5, zzaty r6) {
        this.zzf = true;
        zzdk r52 = zzdn.zzh(MlKitContext.getInstance().getApplicationContext());
        r52.zzd(Duration.ofMinutes(10));
        if (r3 == null) goto L5;
        r52.zza(r3);
    L5:
        this.zza = new zzgl(r52.zzi());
        this.zzb = r4;
        this.zzc = r6;
        r6.zza();
    }

    private static int zza(zzdq r0) {
        if (r0 != null) goto L4;
        return -1;
    L4:
        return r0.zzf();
    }

    private final synchronized ListenableFuture zzb() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.zzd == null) goto L10;
        ListenableFuture r0 = zzot.zza(this.zzd);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r0;
    L10:
        zzdi r02 = this.zza;     // Catch: Throwable -> L8
        if (GenAiUtils.isAiCoreCompatible(r02.zzg()) == true) goto L15;
        ListenableFuture r03 = zzot.zzc(new GenAiException(null, -101));     // Catch: Throwable -> L8
        monitor-exit(this);
        return r03;
    L15:
        ListenableFuture r04 = zzot.zzg(r02.zza(this.zzb, -1), new zzauk(this), r02.zze());     // Catch: Throwable -> L8
        monitor-exit(this);
        return r04;
    }

    @SuppressLint({"WrongConstant"})
    private final ListenableFuture zzc(ListenableFuture r2, zzaua r3) {
        return zzot.zze(r2, Throwable.class, new zzauc(r3), this.zza.zze());
    }

    private final /* synthetic */ ListenableFuture zzg(Object r2, zzdp r3) {
        return r3.zzc(zze(r2, null), true);
    L5:
        e = move-exception;
        return zzot.zzc(e);
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.zza.close();
        this.zzf = true;
        this.zzc.zzc();
    }

    public abstract zzdp zzd(zzdi r1, zzdq r2);

    public abstract Object zze(Object r1, StreamingCallback r2);

    public abstract Object zzf(Object r1);

    public final ListenableFuture zzi() {
        return zzc(zzot.zzf(zzb(), zzaum.zza, this.zza.zze()), zzaua.zzb);
    }

    public final ListenableFuture zzj() {
        zzdi r0 = this.zza;
        if (GenAiUtils.isAiCoreCompatible(r0.zzg()) == true) goto L7;
        return zzot.zza(0);
    L7:
        return zzc(zzot.zzf(zzb(), new zzaub(this), r0.zze()), zzaua.zzb);
    }

    public final ListenableFuture zzk(DownloadCallback r3) {
        return zzc(zzot.zzf(zzb(), new zzaud(this, r3), this.zza.zze()), zzaua.zzb);
    }

    public final ListenableFuture zzl() {
        return zzc(zzot.zzf(zzo(), zzatx.zza, this.zza.zze()), zzaua.zzb);
    }

    public final ListenableFuture zzm(Object r7) {
        long r0 = SystemClock.elapsedRealtime();
        ListenableFuture r2 = zzo();
        zzaue r3 = new zzaue(this, r7);
        zzdi r4 = this.zza;
        return zzc(zzot.zzg(zzot.zzf(r2, r3, r4.zze()), new zzauf(this, r0, r7), r4.zze()), new zzaug(this, r7));
    }

    public final ListenableFuture zzn(Object r8, StreamingCallback r9) {
        long r3 = SystemClock.elapsedRealtime();
        AtomicLong r2 = new AtomicLong();
        if (r9 == null) goto L5;
        zzaun r1 = this;
        zzatw r0 = new zzatw(r1, r2, r3, r9);
    L6:
        ListenableFuture r7 = r1.zzo();
        zzauh r92 = new zzauh(r1, r8, r0);
        zzdi r6 = r1.zza;
        return r1.zzc(zzot.zzg(zzot.zzf(r7, r92, r6.zze()), new zzaui(r1, r2, r3, r8), r6.zze()), new zzauj(r1, r8));
    L5:
        r1 = this;
        r0 = null;
        goto L6
    }

    public final ListenableFuture zzo() {
        zzdp r0 = this.zze;
        if (r0 == null) goto L7;
        return zzot.zza(r0);
    L7:
        return zzot.zzg(zzb(), new zzaul(this), this.zza.zze());
    }

    public final ExecutorService zzp() {
        return this.zza.zze();
    }

    public final /* synthetic */ ListenableFuture zzq(zzdq r1) {
        if (r1 == null) goto L6;
        return this.zza.zzb(r1);
    L6:
        return zzot.zza(0);
    }

    public final /* synthetic */ ListenableFuture zzr(DownloadCallback r2, zzdq r3) {
        zzatv r0 = new zzatv(this, r2);
        return this.zza.zzc(r3, r0);
    }

    public final /* synthetic */ ListenableFuture zzs(Object r1, zzdp r2) {
        return zzg(r1, r2);
    }

    public final /* synthetic */ zzdz zzt(long r3, Object r5, zzdz r6) {
        long r0 = SystemClock.elapsedRealtime() - r3;
        zzars r32 = new zzars();
        r32.zzb(zzant.zza);
        r32.zza(Integer.valueOf(zza(this.zzd)));
        r32.zzc(Boolean.valueOf(this.zzf));
        r32.zzf(Long.valueOf(r0));
        this.zzf = false;
        this.zzc.zzi(r32, r5, r6.zza(), Duration.ofMillis(r0));
        return r6;
    }

    public final /* synthetic */ void zzu(Object r2, GenAiException r3) {
        int r0 = zza(this.zzd);
        this.zzc.zzj(r2, r0, r3.getErrorCode());
    }

    public final /* synthetic */ Object zzv(AtomicLong r4, long r5, Object r7, Object r8) {
        zzars r0 = new zzars();
        r0.zzb(zzant.zza);
        r0.zza(Integer.valueOf(zza(this.zzd)));
        r0.zzc(Boolean.valueOf(this.zzf));
        r0.zzg(Long.valueOf(r4.get()));
        r0.zzf(Long.valueOf(SystemClock.elapsedRealtime() - r5));
        this.zzf = false;
        Object r42 = zzf(r8);
        this.zzc.zzd(r0, r7, r42);
        return r42;
    }

    public final /* synthetic */ void zzw(Object r2, GenAiException r3) {
        int r0 = zza(this.zzd);
        this.zzc.zzh(r2, r0, r3.getErrorCode());
    }

    public final /* synthetic */ zzdq zzx(zzdq r1) {
        this.zzd = r1;
        return r1;
    }

    public final /* synthetic */ zzdp zzy(zzdq r2) {
        zzdp r22 = zzd(this.zza, r2);
        this.zze = r22;
        return r22;
    }
}
