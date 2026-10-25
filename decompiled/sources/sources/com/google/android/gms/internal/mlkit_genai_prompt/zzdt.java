package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzdt extends zzdh {
    private final zzan zza;
    private final zzgu zzb;

    public zzdt(zzan r2, zzgu r3) {
        if (r2 == null) goto L10;
        this.zza = r2;
        if (r3 == null) goto L8;
        this.zzb = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null disconnectSignal");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null service");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzdh) == false) goto L12;
        zzdh r52 = (zzdh) r5;
        if (this.zza.equals(r52.zza()) == false) goto L12;
        if (this.zzb.equals(r52.zzb()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r2 = this.zzb.hashCode();
        return r2 ^ (r0 * 1000003);
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r3 = this.zzb.toString();
        int r12 = r1 + 42;
        StringBuilder r2 = new StringBuilder((r12 + r3.length()) + 1);
        r2.append("ServiceContext{service=");
        r2.append(r0);
        r2.append(", disconnectSignal=");
        r2.append(r3);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdh
    public final zzan zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdh
    public final zzgu zzb() {
        return this.zzb;
    }
}
