package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzvr {
    private final Class zza;
    private final Class zzb;

    public /* synthetic */ zzvr(Class r1, Class r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzvr) == true) goto L5;
        return false;
    L5:
        zzvr r42 = (zzvr) r4;
        if (r42.zza.equals(this.zza) == true) goto L8;
    L11:
        return false;
    L8:
        if (r42.zzb.equals(this.zzb) == false) goto L11;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        Class r0 = this.zzb;
        String r4 = this.zza.getSimpleName();
        String r02 = r0.getSimpleName();
        int r1 = r4.length();
        int r12 = r1 + 22;
        StringBuilder r3 = new StringBuilder(r12 + r02.length());
        r3.append(r4);
        r3.append(" with primitive type: ");
        r3.append(r02);
        return r3.toString();
    }
}
