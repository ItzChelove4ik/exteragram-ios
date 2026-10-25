package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzf extends zzawy implements zzaxx {
    private static final zzf zze = null;
    private int zzb;
    private Object zzd;

    static {
        zzf r0 = new zzf();
        zze = r0;
        zzawy.zzN(zzf.class, r0);
    }

    private zzf() {
        this.zzb = 0;
    }

    public static zzf zzd(byte[] r1, zzawl r2) {
        return (zzf) zzawy.zzR(zze, r1, r2);
    }

    public static zzf zze() {
        return zze;
    }

    public static /* synthetic */ zzf zzf() {
        return zze;
    }

    public final boolean zza() {
        if (this.zzb != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawy
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zze;
    L16:
        return new zze(null);
    L18:
        return new zzf();
    L20:
        return zzawy.zzO(zze, "\u0001\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zzd", "zzb", zzj.class, zzb.class});
    L22:
        return (byte) 1;
    }

    public final zzj zzc() {
        if (this.zzb != 1) goto L7;
        return (zzj) this.zzd;
    L7:
        return zzj.zze();
    }
}
