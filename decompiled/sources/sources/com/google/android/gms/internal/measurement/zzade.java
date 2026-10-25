package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzade {
    private final Object zza;
    private final int zzb;

    public zzade(Object r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzade) == true) goto L5;
        return false;
    L5:
        zzade r42 = (zzade) r4;
        if (this.zza == r42.zza) goto L8;
    L11:
        return false;
    L8:
        if (this.zzb != r42.zzb) goto L11;
        return true;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
