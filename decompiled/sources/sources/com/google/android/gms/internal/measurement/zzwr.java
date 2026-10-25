package com.google.android.gms.internal.measurement;

import android.os.Trace;

/* loaded from: classes4.dex */
abstract class zzwr {
    public static void zza(zzws r1, zzws r2) {
        if (r1 == null) goto L17;
        if (r2 != null) goto L5;
    L16:
        zzc(r1);
        goto L17
    L5:
        if (r1.zzb() != r2) goto L11;
        if (zzd(r1) == true) goto L11;
        Trace.endSection();
        return;
    L11:
        if (r1 != r2.zzb()) goto L16;
        if (zzd(r2) == true) goto L16;
        zze(r2);
        return;
    L17:
        if (r2 == null) goto L20;
        zzb(r2);
        return;
    }

    public static void zzb(zzws r1) {
        if (zzd(r1) == false) goto L5;
    L9:
        Trace.beginSection(r1.zzd());
        zze(r1);
        return;
    L5:
        if (r1.zzb() == null) goto L9;
        zzb(r1.zzb());
        zze(r1);
    }

    public static void zzc(zzws r1) {
        if (zzd(r1) == false) goto L5;
    L9:
        Trace.endSection();
        Trace.endSection();
        return;
    L5:
        if (r1.zzb() == null) goto L9;
        Trace.endSection();
        zzc(r1.zzb());
    }

    private static boolean zzd(zzws r1) {
        if (r1.zza() == Thread.currentThread()) goto L6;
        return true;
    L6:
        return false;
    }

    private static void zze(zzws r2) {
        String r22 = r2.zze();
        zzrg r0 = zzvy.zza;
        if (r22.length() <= 127) goto L5;
        r22 = r22.substring(0, 127);
    L5:
        Trace.beginSection(r22);
    }
}
