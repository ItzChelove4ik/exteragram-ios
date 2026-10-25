package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzje extends zzawy implements zzaxx {
    private static final zzje zzh = null;
    private int zzb;
    private long zzd;
    private zzaxh zze;
    private zzawi zzf;
    private float zzg;

    static {
        zzje r0 = new zzje();
        zzh = r0;
        zzawy.zzN(zzje.class, r0);
    }

    private zzje() {
        this.zze = zzawy.zzQ();
    }

    public static zzje zzf(byte[] r1, zzawl r2) {
        return (zzje) zzawy.zzR(zzh, r1, r2);
    }

    public static zzje zzg() {
        return zzh;
    }

    public static /* synthetic */ zzje zzh() {
        return zzh;
    }

    public final long zza() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawy
    public final Object zzb(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L22;
        if (r72 == 2) goto L20;
        if (r72 == 3) goto L18;
        if (r72 == 4) goto L16;
        if (r72 == 5) goto L13;
        throw null;
    L13:
        return zzh;
    L16:
        return new zzjd(null);
    L18:
        return new zzje();
    L20:
        return zzawy.zzO(zzh, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဃ\u0000\u0002\u001b\u0003ဉ\u0001\u0004ခ\u0002", new Object[]{"zzb", "zzd", "zze", zzjc.class, "zzf", "zzg"});
    L22:
        return (byte) 1;
    }

    public final List zzc() {
        return this.zze;
    }

    public final zzawi zzd() {
        zzawi r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzawi.zzc();
    }

    public final float zze() {
        return this.zzg;
    }
}
