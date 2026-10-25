package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzhc extends zzhp {
    private final int zza;
    private final int zzb;
    private final String zzc;
    private final String zzd;

    public zzhc(int r1, int r2, String r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        if (r3 == null) goto L6;
        this.zzc = r3;
        this.zzd = r4;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null uri");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzhp) == false) goto L22;
        zzhp r52 = (zzhp) r5;
        if (this.zza != r52.zza()) goto L22;
        if (this.zzb != r52.zzb()) goto L22;
        if (this.zzc.equals(r52.zzc()) == false) goto L22;
        String r4 = this.zzd;
        if (r4 != null) goto L19;
        if (r52.zzd() != null) goto L22;
    L21:
        return true;
    L19:
        if (r4.equals(r52.zzd()) == true) goto L21;
    L22:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza;
        String r1 = this.zzc;
        int r02 = ((((r0 ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ r1.hashCode();
        String r4 = this.zzd;
        if (r4 != null) goto L5;
        int r42 = 0;
    L7:
        return r42 ^ (r02 * 1000003);
    L5:
        r42 = r4.hashCode();
        goto L7
    }

    public final String toString() {
        int r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zzb;
        int r3 = String.valueOf(r2).length();
        String r4 = this.zzd;
        int r5 = String.valueOf(r4).length();
        String r7 = this.zzc;
        StringBuilder r6 = new StringBuilder(((((((r1 + 37) + r3) + 6) + r7.length()) + 10) + r5) + 1);
        r6.append("CitationSource{startIndex=");
        r6.append(r0);
        r6.append(", endIndex=");
        r6.append(r2);
        r6.append(", uri=");
        r6.append(r7);
        r6.append(", license=");
        r6.append(r4);
        r6.append("}");
        return r6.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhp
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhp
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhp
    public final String zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhp
    public final String zzd() {
        return this.zzd;
    }
}
