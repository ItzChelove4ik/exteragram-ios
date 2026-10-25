package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
abstract class zzcy {
    private static final zzcy zzlt = null;
    private static final zzcy zzlu = null;

    static {
        zzlt = new zzda(null);
        zzlu = new zzdb(null);
    }

    private zzcy() {
    }

    public /* synthetic */ zzcy(zzcz r1) {
        this();
    }

    public static zzcy zzbv() {
        return zzlt;
    }

    public static zzcy zzbw() {
        return zzlu;
    }

    public abstract void zza(Object r1, long r2);

    public abstract <L> void zza(Object r1, Object r2, long r3);
}
