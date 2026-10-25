package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzyh extends zzaav {
    private final zzyg zza;

    private zzyh(zzyg r1) {
        this.zza = r1;
    }

    public static zzyh zza(zzyg r1) {
        return new zzyh(r1);
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof zzyh) == true) goto L6;
        return false;
    L6:
        if (((zzyh) r3).zza != this.zza) goto L9;
        return true;
    L9:
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzyh.class, this.zza});
    }

    public final String toString() {
        String r2 = this.zza.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 30);
        r1.append("Ed25519 Parameters (variant: ");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }

    public final zzyg zzb() {
        return this.zza;
    }
}
