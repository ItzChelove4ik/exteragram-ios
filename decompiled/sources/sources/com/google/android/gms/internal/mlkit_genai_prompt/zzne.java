package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.Serializable;

/* loaded from: classes4.dex */
public final class zzne implements Serializable {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzne zzd = null;
    final zzmb zza;
    final zzmb zzb;

    static {
        zzd = new zzne(zzma.zzd(), zzly.zzd());
    }

    private zzne(zzmb r1, zzmb r2) {
        this.zza = r1;
        this.zzb = r2;
        if (r1.zzc(r2) <= 0) goto L5;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Invalid range: ".concat(zze(r1, r2)));
        throw null;
    L5:
        if (r1 == zzly.zzd()) goto L9;
        if (r2 == zzma.zzd()) goto L9;
    }

    public static zzne zza() {
        return zzd;
    }

    private static String zze(zzmb r2, zzmb r3) {
        StringBuilder r0 = new StringBuilder(16);
        r2.zza(r0);
        r0.append("..");
        r3.zzb(r0);
        return r0.toString();
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzne) == false) goto L10;
        zzne r42 = (zzne) r4;
        if (this.zza.equals(r42.zza) == false) goto L10;
        if (this.zzb.equals(r42.zzb) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        return (this.zza.hashCode() * 31) + this.zzb.hashCode();
    }

    public final String toString() {
        return zze(this.zza, this.zzb);
    }

    public final boolean zzb() {
        return this.zza.equals(this.zzb);
    }

    public final zzne zzc(zzne r7) {
        zzmb r0 = this.zza;
        zzmb r1 = r7.zza;
        int r2 = r0.zzc(r1);
        zzmb r3 = this.zzb;
        zzmb r4 = r7.zzb;
        int r5 = r3.zzc(r4);
        if (r2 < 0) goto L7;
        if (r5 > 0) goto L7;
        return this;
    L7:
        if (r2 > 0) goto L11;
        if (r5 < 0) goto L11;
        return r7;
    L11:
        if (r2 >= 0) goto L13;
        r0 = r1;
    L13:
        if (r5 <= 0) goto L17;
        r3 = r4;
    L17:
        if (r0.zzc(r3) > 0) goto L19;
        boolean r12 = true;
    L20:
        zzla.zzb(r12, "intersection is undefined for disconnected ranges %s and %s", this, r7);
        return new zzne(r0, r3);
    L19:
        r12 = false;
        goto L20
    }

    public final zzne zzd(zzne r7) {
        zzmb r0 = this.zza;
        zzmb r1 = r7.zza;
        int r2 = r0.zzc(r1);
        zzmb r3 = this.zzb;
        zzmb r4 = r7.zzb;
        int r5 = r3.zzc(r4);
        if (r2 > 0) goto L7;
        if (r5 < 0) goto L7;
        return this;
    L7:
        if (r2 < 0) goto L11;
        if (r5 > 0) goto L11;
        return r7;
    L11:
        if (r2 <= 0) goto L14;
        r0 = r1;
    L14:
        if (r5 >= 0) goto L18;
        r3 = r4;
    L18:
        return new zzne(r0, r3);
    }
}
