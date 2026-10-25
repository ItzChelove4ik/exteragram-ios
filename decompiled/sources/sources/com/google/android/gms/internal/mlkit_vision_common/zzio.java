package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes4.dex */
public enum zzio extends Enum implements zzag {
    public static final zzio zza = null;
    public static final zzio zzb = null;
    public static final zzio zzc = null;
    public static final zzio zzd = null;
    public static final zzio zze = null;
    public static final zzio zzf = null;
    private static final /* synthetic */ zzio[] zzg = null;
    private final int zzh;

    static {
        zzio r0 = new zzio("SOURCE_UNKNOWN", 0, 0);
        zza = r0;
        zzio r1 = new zzio("BITMAP", 1, 1);
        zzb = r1;
        zzio r2 = new zzio("BYTEARRAY", 2, 2);
        zzc = r2;
        zzio r3 = new zzio("BYTEBUFFER", 3, 3);
        zzd = r3;
        zzio r4 = new zzio("FILEPATH", 4, 4);
        zze = r4;
        zzio r5 = new zzio("ANDROID_MEDIA_IMAGE", 5, 5);
        zzf = r5;
        zzg = new zzio[]{r0, r1, r2, r3, r4, r5};
    }

    zzio(String r1, int r2, int r3) {
        this.zzh = r3;
    }

    public static zzio[] values() {
        return (zzio[]) zzg.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzag
    public final int zza() {
        return this.zzh;
    }
}
