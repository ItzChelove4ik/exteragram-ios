package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public enum zzara extends Enum implements zzpt {
    public static final zzara zza = null;
    public static final zzara zzb = null;
    public static final zzara zzc = null;
    private static final /* synthetic */ zzara[] zze = null;
    private final int zzd;

    static {
        zzara r0 = new zzara("MODALITY_TYPE_UNKNOWN", 0, 0);
        zza = r0;
        zzara r1 = new zzara("MODALITY_TYPE_TEXT", 1, 1);
        zzb = r1;
        zzara r2 = new zzara("MODALITY_TYPE_IMAGE", 2, 2);
        zzc = r2;
        zze = new zzara[]{r0, r1, r2};
    }

    zzara(String r1, int r2, int r3) {
        this.zzd = r3;
    }

    public static zzara[] values() {
        return (zzara[]) zze.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpt
    public final int zza() {
        return this.zzd;
    }
}
