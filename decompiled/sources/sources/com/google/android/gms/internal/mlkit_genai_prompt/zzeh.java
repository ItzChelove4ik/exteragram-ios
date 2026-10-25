package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzeh extends zzep {
    private final zzml zza;
    private final zzbt zzb;
    private final int zzc;

    public zzeh(zzml r1, zzbt r2, int r3) {
        if (r1 == null) goto L6;
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null results");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzep) == false) goto L20;
        zzep r52 = (zzep) r5;
        if (this.zza.equals(r52.zza()) == false) goto L20;
        zzbt r1 = this.zzb;
        if (r1 != null) goto L15;
        if (r52.zzb() != null) goto L20;
    L18:
        if (this.zzc != r52.zzc()) goto L20;
        return true;
    L15:
        if (r1 == r52.zzb()) goto L18;
    L20:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        zzbt r2 = this.zzb;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r3 = this.zzc;
        return r3 ^ (((r0 * 1000003) ^ r22) * 1000003);
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r2 = String.valueOf(this.zzb);
        int r3 = r2.length();
        int r5 = this.zzc;
        int r12 = ((r1 + 50) + r3) + 14;
        StringBuilder r32 = new StringBuilder((r12 + String.valueOf(r5).length()) + 1);
        r32.append("CortanaResult{results=");
        r32.append(r0);
        r32.append(", inferenceEventTraceResult=");
        r32.append(r2);
        r32.append(", tokenOffset=");
        r32.append(r5);
        r32.append("}");
        return r32.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzep
    public final zzml zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzep
    public final zzbt zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzep
    public final int zzc() {
        return this.zzc;
    }
}
