package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzyu implements zzyd {
    private final zzyd zza;
    private final Object zzb;

    private zzyu(zzyd r2, Object r3) {
        zzabr.zza(r2, "log site key");
        this.zza = r2;
        zzabr.zza(r3, "log site qualifier");
        this.zzb = r3;
    }

    public static zzyd zza(zzyd r1, Object r2) {
        return new zzyu(r1, r2);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzyu) == true) goto L5;
        return false;
    L5:
        zzyu r42 = (zzyu) r4;
        if (this.zza.equals(r42.zza) == true) goto L8;
    L11:
        return false;
    L8:
        if (this.zzb.equals(r42.zzb) == false) goto L11;
        return true;
    }

    public final int hashCode() {
        Object r0 = this.zzb;
        return this.zza.hashCode() ^ r0.hashCode();
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r3 = this.zzb.toString();
        int r12 = r1 + 47;
        StringBuilder r2 = new StringBuilder((r12 + r3.length()) + 3);
        r2.append("SpecializedLogSiteKey{ delegate='");
        r2.append(r0);
        r2.append("', qualifier='");
        r2.append(r3);
        r2.append("' }");
        return r2.toString();
    }
}
