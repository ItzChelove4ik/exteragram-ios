package com.google.android.gms.internal.vision;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public enum zzmo extends Enum<zzmo> {
    public static final zzmo zza = null;
    public static final zzmo zzb = null;
    public static final zzmo zzc = null;
    public static final zzmo zzd = null;
    public static final zzmo zze = null;
    public static final zzmo zzf = null;
    public static final zzmo zzg = null;
    public static final zzmo zzh = null;
    public static final zzmo zzi = null;
    private static final /* synthetic */ zzmo[] zzk = null;
    private final Object zzj;

    static {
        zzmo r0 = new zzmo("INT", 0, 0);
        zza = r0;
        zzmo r1 = new zzmo("LONG", 1, 0L);
        zzb = r1;
        zzmo r2 = new zzmo("FLOAT", 2, Float.valueOf(0.0f));
        zzc = r2;
        zzmo r3 = new zzmo("DOUBLE", 3, Double.valueOf(0.0d));
        zzd = r3;
        zzmo r4 = new zzmo("BOOLEAN", 4, Boolean.FALSE);
        zze = r4;
        zzmo r5 = new zzmo("STRING", 5, _UrlKt.FRAGMENT_ENCODE_SET);
        zzf = r5;
        zzmo r6 = new zzmo("BYTE_STRING", 6, zzht.zza);
        zzg = r6;
        zzmo r7 = new zzmo("ENUM", 7, null);
        zzh = r7;
        zzmo r8 = new zzmo("MESSAGE", 8, null);
        zzi = r8;
        zzk = new zzmo[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
    }

    zzmo(String r1, int r2, Object r3) {
        this.zzj = r3;
    }

    public static zzmo[] values() {
        return (zzmo[]) zzk.clone();
    }
}
