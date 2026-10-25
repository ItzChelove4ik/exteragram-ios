package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes4.dex */
public enum zzii extends Enum implements zzag {
    public static final zzii zza = null;
    public static final zzii zzb = null;
    public static final zzii zzc = null;
    public static final zzii zzd = null;
    public static final zzii zze = null;
    public static final zzii zzf = null;
    public static final zzii zzg = null;
    public static final zzii zzh = null;
    public static final zzii zzi = null;
    public static final zzii zzj = null;
    private static final /* synthetic */ zzii[] zzk = null;
    private final int zzl;

    static {
        zzii r0 = new zzii("UNKNOWN_FORMAT", 0, 0);
        zza = r0;
        zzii r1 = new zzii("NV16", 1, 1);
        zzb = r1;
        zzii r2 = new zzii("NV21", 2, 2);
        zzc = r2;
        zzii r3 = new zzii("YV12", 3, 3);
        zzd = r3;
        zzii r4 = new zzii("YUV_420_888", 4, 7);
        zze = r4;
        zzii r5 = new zzii("JPEG", 5, 8);
        zzf = r5;
        zzii r6 = new zzii("BITMAP", 6, 4);
        zzg = r6;
        zzii r7 = new zzii("CM_SAMPLE_BUFFER_REF", 7, 5);
        zzh = r7;
        zzii r8 = new zzii("UI_IMAGE", 8, 6);
        zzi = r8;
        zzii r9 = new zzii("CV_PIXEL_BUFFER_REF", 9, 9);
        zzj = r9;
        zzk = new zzii[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }

    zzii(String r1, int r2, int r3) {
        this.zzl = r3;
    }

    public static zzii[] values() {
        return (zzii[]) zzk.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzag
    public final int zza() {
        return this.zzl;
    }
}
