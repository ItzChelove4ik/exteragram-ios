package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzabb extends zzaav {
    private final zzaay zza;
    private final zzaaz zzb;
    private final zzaba zzc;

    private zzabb(zzaay r1, int r2, zzaaz r3, zzaba r4) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
    }

    public static zzabb zza(zzaba r4) {
        return new zzabb(zzaay.zza, 64, zzaaz.zza, r4);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzabb) == true) goto L5;
        return false;
    L5:
        zzabb r42 = (zzabb) r4;
        if (r42.zza == this.zza) goto L8;
    L13:
        return false;
    L8:
        if (r42.zzb != this.zzb) goto L13;
        if (r42.zzc != this.zzc) goto L13;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzabb.class, this.zza, 64, this.zzb, this.zzc});
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r2 = this.zzb.toString();
        int r3 = r2.length();
        String r5 = this.zzc.toString();
        int r12 = ((r1 + 12) + r3) + 20;
        StringBuilder r32 = new StringBuilder(r12 + r5.length());
        r32.append("SLH-DSA-");
        r32.append(r0);
        r32.append("-128");
        r32.append(r2);
        r32.append(" instance, variant: ");
        r32.append(r5);
        return r32.toString();
    }
}
