package com.google.android.gms.internal.measurement;

import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
enum zzabd extends Enum {
    public static final zzabd zza = null;
    public static final zzabd zzb = null;
    public static final zzabd zzc = null;
    public static final zzabd zzd = null;
    private static final /* synthetic */ zzabd[] zze = null;

    static {
        zzabd r0 = new zzabd("BOOLEAN", 0);
        zza = r0;
        zzabd r1 = new zzabd("STRING", 1);
        zzb = r1;
        zzabd r2 = new zzabd("LONG", 2);
        zzc = r2;
        zzabd r3 = new zzabd("DOUBLE", 3);
        zzd = r3;
        zze = new zzabd[]{r0, r1, r2, r3};
    }

    zzabd(String r1, int r2) {
    }

    public static zzabd[] values() {
        return (zzabd[]) zze.clone();
    }

    public static /* synthetic */ zzabd zza(Object r1) {
        if ((r1 instanceof String) == false) goto L7;
        return zzb;
    L7:
        if ((r1 instanceof Boolean) == false) goto L11;
        return zza;
    L11:
        if ((r1 instanceof Long) == false) goto L15;
        return zzc;
    L15:
        if ((r1 instanceof Double) == true) goto L17;
        Buffer$$ExternalSyntheticBUOutline2.m("invalid tag type: ".concat(String.valueOf(r1.getClass())));
        return null;
    L17:
        return zzd;
    }
}
