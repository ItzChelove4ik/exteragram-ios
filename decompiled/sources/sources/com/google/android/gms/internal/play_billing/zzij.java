package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
public final class zzij extends zzfi implements zzgm {
    private static final zzij zzb = null;

    static {
        zzij r0 = new zzij();
        zzb = r0;
        zzfi.zzw(zzij.class, r0);
    }

    private zzij() {
    }

    public static /* bridge */ /* synthetic */ zzij zzc() {
        return zzb;
    }

    public static zzij zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzih(null);
    L18:
        return new zzij();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0000", null);
    L22:
        return (byte) 1;
    }
}
