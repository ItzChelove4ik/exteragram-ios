package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public enum zznh extends Enum implements zzcb {
    public static final zznh zza = null;
    public static final zznh zzb = null;
    public static final zznh zzc = null;
    public static final zznh zzd = null;
    public static final zznh zze = null;
    public static final zznh zzf = null;
    public static final zznh zzg = null;
    public static final zznh zzh = null;
    public static final zznh zzi = null;
    public static final zznh zzj = null;
    private static final /* synthetic */ zznh[] zzk = null;
    private final int zzl;

    static {
        zznh r0 = new zznh("UNKNOWN_FORMAT", 0, 0);
        zza = r0;
        zznh r1 = new zznh("NV16", 1, 1);
        zzb = r1;
        zznh r2 = new zznh("NV21", 2, 2);
        zzc = r2;
        zznh r3 = new zznh("YV12", 3, 3);
        zzd = r3;
        zznh r4 = new zznh("YUV_420_888", 4, 7);
        zze = r4;
        zznh r5 = new zznh("JPEG", 5, 8);
        zzf = r5;
        zznh r6 = new zznh("BITMAP", 6, 4);
        zzg = r6;
        zznh r7 = new zznh("CM_SAMPLE_BUFFER_REF", 7, 5);
        zzh = r7;
        zznh r8 = new zznh("UI_IMAGE", 8, 6);
        zzi = r8;
        zznh r9 = new zznh("CV_PIXEL_BUFFER_REF", 9, 9);
        zzj = r9;
        zzk = new zznh[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }

    zznh(String r1, int r2, int r3) {
        this.zzl = r3;
    }

    public static zznh[] values() {
        return (zznh[]) zzk.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzcb
    public final int zza() {
        return this.zzl;
    }
}
