package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzxx extends zzaav {
    private final zzxv zza;
    private final zzxt zzb;
    private final zzxu zzc;
    private final zzxw zzd;

    public /* synthetic */ zzxx(zzxv r1, zzxt r2, zzxu r3, zzxw r4, byte[] r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public static zzxs zza() {
        return new zzxs(null);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzxx) == true) goto L5;
        return false;
    L5:
        zzxx r42 = (zzxx) r4;
        if (r42.zza == this.zza) goto L8;
    L15:
        return false;
    L8:
        if (r42.zzb != this.zzb) goto L15;
        if (r42.zzc != this.zzc) goto L15;
        if (r42.zzd != this.zzd) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzxx.class, this.zza, this.zzb, this.zzc, this.zzd});
    }

    public final String toString() {
        String r0 = this.zzd.toString();
        int r1 = r0.length();
        String r2 = this.zzc.toString();
        int r3 = r2.length();
        String r4 = this.zza.toString();
        int r5 = r4.length();
        String r7 = this.zzb.toString();
        int r12 = ((((r1 + 39) + r3) + 12) + r5) + 9;
        StringBuilder r32 = new StringBuilder((r12 + r7.length()) + 1);
        r32.append("ECDSA Parameters (variant: ");
        r32.append(r0);
        r32.append(", hashType: ");
        r32.append(r2);
        r32.append(", encoding: ");
        r32.append(r4);
        r32.append(", curve: ");
        r32.append(r7);
        r32.append(")");
        return r32.toString();
    }

    public final zzxv zzb() {
        return this.zza;
    }

    public final zzxt zzc() {
        return this.zzb;
    }

    public final zzxu zzd() {
        return this.zzc;
    }

    public final zzxw zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        if (this.zzd == zzxw.zzd) goto L6;
        return true;
    L6:
        return false;
    }
}
