package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzawi extends zzawy implements zzaxx {
    private static final zzawi zze = null;
    private long zzb;
    private int zzd;

    static {
        zzawi r0 = new zzawi();
        zze = r0;
        zzawy.zzN(zzawi.class, r0);
    }

    private zzawi() {
    }

    public static zzawi zzc() {
        return zze;
    }

    public static /* synthetic */ zzawi zzd() {
        return zze;
    }

    public final long zza() {
        return this.zzb;
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
        return new zzawh(null);
    L18:
        return new zzawi();
    L20:
        return new zzayf(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzb", "zzd"});
    L22:
        return (byte) 1;
    }
}
