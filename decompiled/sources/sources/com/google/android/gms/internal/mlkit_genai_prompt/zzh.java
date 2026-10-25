package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzh extends zzawy implements zzaxx {
    private static final zzh zze = null;
    private int zzb;
    private boolean zzd;

    static {
        zzh r0 = new zzh();
        zze = r0;
        zzawy.zzN(zzh.class, r0);
    }

    private zzh() {
    }

    public static zzh zzc(byte[] r1, zzawl r2) {
        return (zzh) zzawy.zzR(zze, r1, r2);
    }

    public static zzh zzd() {
        return zze;
    }

    public static /* synthetic */ zzh zze() {
        return zze;
    }

    public final boolean zza() {
        return this.zzd;
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
        return new zzg(null);
    L18:
        return new zzh();
    L20:
        return zzawy.zzO(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zzb", "zzd"});
    L22:
        return (byte) 1;
    }
}
