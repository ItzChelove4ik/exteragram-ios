package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public enum zzarn extends Enum implements zzpt {
    public static final zzarn zza = null;
    public static final zzarn zzb = null;
    public static final zzarn zzc = null;
    private static final /* synthetic */ zzarn[] zze = null;
    private final int zzd;

    static {
        zzarn r0 = new zzarn("GENERATION_MODE_UNKNOWN", 0, 0);
        zza = r0;
        zzarn r1 = new zzarn("GENERATION_MODE_GENERATIVE", 1, 1);
        zzb = r1;
        zzarn r2 = new zzarn("GENERATION_MODE_REGENERATIVE", 2, 2);
        zzc = r2;
        zze = new zzarn[]{r0, r1, r2};
    }

    zzarn(String r1, int r2, int r3) {
        this.zzd = r3;
    }

    public static zzarn[] values() {
        return (zzarn[]) zze.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzpt
    public final int zza() {
        return this.zzd;
    }
}
