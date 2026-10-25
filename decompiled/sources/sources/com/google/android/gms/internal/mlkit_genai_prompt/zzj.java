package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzj extends zzawy implements zzaxx {
    private static final zzj zzj = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private float zzg;
    private float zzh;
    private float zzi;

    static {
        zzj r0 = new zzj();
        zzj = r0;
        zzawy.zzN(zzj.class, r0);
    }

    private zzj() {
    }

    public static zzj zze() {
        return zzj;
    }

    public static /* synthetic */ zzj zzf() {
        return zzj;
    }

    public final float zza() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawy
    public final Object zzb(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L22;
        if (r112 == 2) goto L20;
        if (r112 == 3) goto L18;
        if (r112 == 4) goto L16;
        if (r112 == 5) goto L13;
        throw null;
    L13:
        return zzj;
    L16:
        return new zzi(null);
    L18:
        return new zzj();
    L20:
        return zzawy.zzO(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ခ\u0005", new Object[]{"zzb", "zzd", zzazj.zza(), "zze", zzazh.zza(), "zzf", zzazf.zza(), "zzg", "zzh", "zzi"});
    L22:
        return (byte) 1;
    }

    public final float zzc() {
        return this.zzh;
    }

    public final float zzd() {
        return this.zzi;
    }

    public final int zzg() {
        int r0 = zzazj.zzb(this.zzd);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final int zzh() {
        int r0 = zzazh.zzb(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }

    public final int zzi() {
        int r0 = zzazf.zzb(this.zzf);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }
}
