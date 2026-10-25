package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzyr extends zzaav {
    private final zzyp zza;
    private final zzyq zzb;

    private zzyr(zzyp r1, zzyq r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static zzyr zza(zzyp r1, zzyq r2) {
        return new zzyr(r1, r2);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzyr) == true) goto L5;
        return false;
    L5:
        zzyr r42 = (zzyr) r4;
        if (r42.zza == this.zza) goto L8;
    L11:
        return false;
    L8:
        if (r42.zzb != this.zzb) goto L11;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzyr.class, this.zza, this.zzb});
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r3 = this.zzb.toString();
        int r12 = r1 + 47;
        StringBuilder r2 = new StringBuilder((r12 + r3.length()) + 1);
        r2.append("ML-DSA Parameters (ML-DSA instance: ");
        r2.append(r0);
        r2.append(", variant: ");
        r2.append(r3);
        r2.append(")");
        return r2.toString();
    }
}
