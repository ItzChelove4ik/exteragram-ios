package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzee extends zzek {
    private final String zza;
    private final float zzb;
    private final int zzc;

    public zzee(String r1, float r2, int r3) {
        if (r1 == null) goto L6;
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null text");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzek) == false) goto L14;
        zzek r52 = (zzek) r5;
        if (this.zza.equals(r52.zza()) == false) goto L14;
        if (Float.floatToIntBits(this.zzb) != Float.floatToIntBits(r52.zzb())) goto L14;
        if (this.zzc != r52.zzc()) goto L14;
        return true;
    L14:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r02 = ((r0 * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003;
        return this.zzc ^ r02;
    }

    public final String toString() {
        float r0 = this.zzb;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zzc;
        int r3 = String.valueOf(r2).length();
        String r6 = this.zza;
        StringBuilder r4 = new StringBuilder(((((r6.length() + 26) + r1) + 13) + r3) + 1);
        r4.append("CortanaReply{text=");
        r4.append(r6);
        r4.append(", score=");
        r4.append(r0);
        r4.append(", stopReason=");
        r4.append(r2);
        r4.append("}");
        return r4.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzek
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzek
    public final float zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzek
    public final int zzc() {
        return this.zzc;
    }
}
