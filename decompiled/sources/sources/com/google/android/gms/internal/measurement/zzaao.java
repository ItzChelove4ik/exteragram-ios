package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import kotlin.text.Typography;

/* loaded from: classes4.dex */
final class zzaao extends zzaag {
    static final boolean zza = false;
    static final boolean zzb = false;
    static final boolean zzc = false;
    private static final AtomicReference zzd = null;
    private static final AtomicLong zzf = null;
    private static final ConcurrentLinkedQueue zzg = null;
    private volatile zzzf zze;

    static {
        String r0 = Build.FINGERPRINT;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L6:
        boolean r02 = true;
    L8:
        zza = r02;
        String r03 = Build.HARDWARE;
        if ("goldfish".equals(r03) == false) goto L11;
    L12:
        boolean r04 = true;
    L14:
        zzb = r04;
        String r05 = Build.TYPE;
        if ("eng".equals(r05) == false) goto L17;
    L18:
        r1 = true;
    L19:
        zzc = r1;
        zzd = new AtomicReference();
        zzf = new AtomicLong();
        zzg = new ConcurrentLinkedQueue();
        return;
    L17:
        if ("userdebug".equals(r05) == false) goto L19;
    L11:
        if ("ranchu".equals(r03) == true) goto L12;
        r04 = false;
        goto L14
    L5:
        if ("robolectric".equals(r0) == true) goto L6;
        r02 = false;
        goto L8
    }

    private zzaao(String r2) {
        super(r2);
        if (zza == false) goto L5;
    L13:
        this.zze = new zzaah().zza(zza());
        return;
    L5:
        if (zzb == true) goto L13;
        if (zzc == false) goto L11;
        this.zze = zzaas.zze().zzb(false).zza(zza());
        return;
    L11:
        this.zze = null;
    }

    public static zzzf zze(String r6) {
        AtomicReference r0 = zzd;
        if (r0.get() != null) goto L5;
        int r2 = r6.length();
    L7:
        r2 = r2 - 1;
        if (r2 < 0) goto L14;
        char r3 = r6.charAt(r2);
        if (r3 == '$') goto L11;
        if (r3 == '.') goto L14;
    L11:
        r6 = r6.replace(Typography.dollar, '.');
    L14:
        zzaao r1 = new zzaao(r6);
        ConcurrentLinkedQueue r62 = zzaam.zza;
        r62.offer(r1);
        if (r0.get() == null) goto L20;
    L16:
        zzaao r22 = (zzaao) r62.poll();
        if (r22 == null) goto L19;
        r22.zze = ((zzaai) r0.get()).zza(r22.zza());
        goto L16
    L19:
        zzf();
    L20:
        return r1;
    L5:
        return ((zzaai) r0.get()).zza(r6);
    }

    private static void zzf() {
    L2:
        zzaan r0 = (zzaan) zzg.poll();
        if (r0 == null) goto L9;
        zzf.getAndDecrement();
        zzzf r1 = r0.zza();
        zzzd r02 = r0.zzb();
        if (r02.zzk() == true) goto L8;
        if (r1.zzb(r02.zze()) == false) goto L2;
    L8:
        r1.zzc(r02);
        goto L2
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public final boolean zzb(Level r2) {
        if (this.zze != null) goto L5;
        return true;
    L5:
        if (this.zze.zzb(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    @SuppressLint({"LongLogTag"})
    public final void zzc(zzzd r5) {
        if (this.zze == null) goto L7;
        this.zze.zzc(r5);
        return;
    L7:
        if (zzf.incrementAndGet() <= 20) goto L9;
        zzg.poll();
        Log.w("ProxyAndroidLoggerBackend", "Too many Flogger logs received before configuration. Dropping old logs.");
    L9:
        zzg.offer(new zzaan(this, r5));
        if (this.zze == null) goto L13;
        zzf();
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzaag, com.google.android.gms.internal.measurement.zzzf
    public final void zzd(RuntimeException r2, zzzd r3) {
        if (this.zze == null) goto L6;
        this.zze.zzd(r2, r3);
        return;
    L6:
        Log.e("ProxyAndroidLoggerBackend", "Internal logging error before configuration", r2);
    }
}
