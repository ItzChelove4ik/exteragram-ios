package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzafg {
    private final Object zza;
    private final int zzb;

    public zzafg(Object r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzafg) == true) goto L5;
        return false;
    L5:
        zzafg r42 = (zzafg) r4;
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
