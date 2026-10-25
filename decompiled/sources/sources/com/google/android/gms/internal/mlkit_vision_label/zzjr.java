package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public enum zzjr extends Enum implements zzck {
    public static final zzjr zza = null;
    public static final zzjr zzb = null;
    public static final zzjr zzc = null;
    public static final zzjr zzd = null;
    public static final zzjr zze = null;
    public static final zzjr zzf = null;
    public static final zzjr zzg = null;
    public static final zzjr zzh = null;
    public static final zzjr zzi = null;
    public static final zzjr zzj = null;
    private static final /* synthetic */ zzjr[] zzk = null;
    private final int zzl;

    static {
        zzjr r0 = new zzjr("UNKNOWN_FORMAT", 0, 0);
        zza = r0;
        zzjr r1 = new zzjr("NV16", 1, 1);
        zzb = r1;
        zzjr r2 = new zzjr("NV21", 2, 2);
        zzc = r2;
        zzjr r3 = new zzjr("YV12", 3, 3);
        zzd = r3;
        zzjr r4 = new zzjr("YUV_420_888", 4, 7);
        zze = r4;
        zzjr r5 = new zzjr("JPEG", 5, 8);
        zzf = r5;
        zzjr r6 = new zzjr("BITMAP", 6, 4);
        zzg = r6;
        zzjr r7 = new zzjr("CM_SAMPLE_BUFFER_REF", 7, 5);
        zzh = r7;
        zzjr r8 = new zzjr("UI_IMAGE", 8, 6);
        zzi = r8;
        zzjr r9 = new zzjr("CV_PIXEL_BUFFER_REF", 9, 9);
        zzj = r9;
        zzk = new zzjr[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }

    zzjr(String r1, int r2, int r3) {
        this.zzl = r3;
    }

    public static zzjr[] values() {
        return (zzjr[]) zzk.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzck
    public final int zza() {
        return this.zzl;
    }
}
