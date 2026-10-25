package com.google.android.gms.internal.mlkit_genai_prompt;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes4.dex */
abstract class zzayh {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzayq zzb = null;

    static {
        int r0 = zzavq.$r8$clinit;
        zzb = new zzays();
    }

    public static zzayq zza() {
        return zzb;
    }

    public static boolean zzb(Object r2, Object r3) {
        if (r2 != r3) goto L5;
        return true;
    L5:
        if (r2 != null) goto L7;
    L9:
        return false;
    L7:
        if (r2.equals(r3) == false) goto L9;
        return true;
    }

    public static void zzc(zzawm r0, Object r1, Object r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    public static void zzd(zzayq r1, Object r2, Object r3) {
        zzawy r22 = (zzawy) r2;
        zzayr r12 = r22.zzc;
        zzayr r32 = ((zzawy) r3).zzc;
        if (zzayr.zza().equals(r32) == false) goto L5;
    L8:
        r22.zzc = r12;
        return;
    L5:
        if (zzayr.zza().equals(r12) == false) goto L7;
        r12 = zzayr.zzc(r12, r32);
        goto L8
    L7:
        r12.zzh(r32);
        goto L8
    }

    public static Object zze(Object r2, int r3, int r4, Object r5, zzayq r6) {
        if (r5 != null) goto L6;
        zzawy r22 = (zzawy) r2;
        zzayr r52 = r22.zzc;
        Object r53 = r52;
        if (r52 != zzayr.zza()) goto L6;
        zzayr r54 = zzayr.zzb();
        r22.zzc = r54;
        r53 = r54;
    L6:
        ((zzayr) r53).zzg(r3 << 3, Long.valueOf(r4));
        return r53;
    }
}
