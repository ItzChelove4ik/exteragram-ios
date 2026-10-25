package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzhg extends zzhv {
    private final String zza;
    private final float zzb;
    private final int zzc;
    private final zzho zzd;
    private final zzml zze;

    public zzhg(String r2, float r3, int r4, zzho r5, zzml r6) {
        if (r2 == null) goto L10;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        if (r6 == null) goto L8;
        this.zze = r6;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null functionCalls");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null text");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzhv) == false) goto L24;
        zzhv r52 = (zzhv) r5;
        if (this.zza.equals(r52.zza()) == false) goto L24;
        if (Float.floatToIntBits(this.zzb) != Float.floatToIntBits(r52.zzb())) goto L24;
        if (this.zzc != r52.zzc()) goto L24;
        zzho r1 = this.zzd;
        if (r1 != null) goto L19;
        if (r52.zzd() != null) goto L24;
    L22:
        if (this.zze.equals(r52.zze()) == false) goto L24;
        return true;
    L19:
        if (r1.equals(r52.zzd()) == true) goto L22;
    L24:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r02 = (r0 * 1000003) ^ Float.floatToIntBits(this.zzb);
        zzho r2 = this.zzd;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r03 = r02 * 1000003;
        int r04 = (((r03 ^ this.zzc) * 1000003) ^ r22) * 1000003;
        return this.zze.hashCode() ^ r04;
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public final String toString() {
        zzml r0 = this.zze;
        String r1 = String.valueOf(this.zzd);
        String r02 = r0.toString();
        float r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        int r4 = this.zzc;
        int r5 = String.valueOf(r4).length();
        int r6 = r1.length();
        int r7 = r02.length();
        String r10 = this.zza;
        StringBuilder r8 = new StringBuilder(((((((((r10.length() + 22) + r3) + 13) + r5) + 19) + r6) + 16) + r7) + 1);
        r8.append("LlmReply{text=");
        r8.append(r10);
        r8.append(", score=");
        r8.append(r2);
        r8.append(", stopReason=");
        r8.append(r4);
        r8.append(", citationMetadata=");
        r8.append(r1);
        r8.append(", functionCalls=");
        r8.append(r02);
        r8.append("}");
        return r8.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhv
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhv
    public final float zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhv
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhv
    public final zzho zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhv
    public final zzml zze() {
        return this.zze;
    }
}
