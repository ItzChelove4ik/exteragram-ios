package com.google.android.gms.measurement.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final /* synthetic */ class zzkz implements zzll {
    private final /* synthetic */ zzlj zza;
    private final /* synthetic */ AtomicReference zzb;
    private final /* synthetic */ zzom zzc;

    public /* synthetic */ zzkz(zzlj r1, AtomicReference r2, zzom r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.measurement.internal.zzll
    public final /* synthetic */ void zza(String r7, int r8, Throwable r9, byte[] r10, Map r11) {
        zzlj r72 = this.zza;
        r72.zzg();
        zzom r102 = this.zzc;
        if (r8 != 200) goto L5;
    L9:
        if (r9 != null) goto L11;
        r72.zzu.zzaW().zzk().zzb("[sgtm] Upload succeeded for row_id", Long.valueOf(r102.zza));
        zzlr r82 = zzlr.zzb;
    L15:
        AtomicReference r6 = this.zzb;
        zznl r92 = r72.zzu.zzt();
        long r1 = r102.zza;
        r92.zzy(new zzaf(r1, r82.zza(), r102.zzf));
        r72.zzu.zzaW().zzk().zzc("[sgtm] Updated status for row_id", Long.valueOf(r1), r82);
        monitor-enter(r6);
        r6.set(r82);     // Catch: Throwable -> L20
        r6.notifyAll();     // Catch: Throwable -> L20
        monitor-exit(r6);     // Catch: Throwable -> L20
        return;
    L20:
        th = move-exception;
        throw th;
    L11:
        r72.zzu.zzaW().zze().zzd("[sgtm] Upload failed for row_id. response, exception", Long.valueOf(r102.zza), Integer.valueOf(r8), r9);
        if (Arrays.asList(((String) zzfy.zzt.zzb(null)).split(",")).contains(String.valueOf(r8)) == false) goto L14;
        r82 = zzlr.zzd;
        goto L15
    L14:
        r82 = zzlr.zzc;
        goto L15
    L5:
        if (r8 == 204) goto L9;
        if (r8 != 304) goto L11;
        r8 = 304;
        goto L9
    }
}
