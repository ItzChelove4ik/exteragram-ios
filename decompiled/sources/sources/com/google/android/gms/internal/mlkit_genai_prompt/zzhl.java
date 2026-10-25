package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzhl extends zzic {
    private final zzml zza;
    private final zzbt zzb;
    private final zzha zzc;
    private final zzml zzd;

    public zzhl(zzml r1, zzbt r2, zzha r3, zzml r4) {
        if (r1 == null) goto L6;
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null results");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzic) == false) goto L33;
        zzic r52 = (zzic) r5;
        if (this.zza.equals(r52.zza()) == false) goto L33;
        zzbt r1 = this.zzb;
        if (r1 != null) goto L15;
        if (r52.zzb() != null) goto L33;
    L17:
        zzha r12 = this.zzc;
        if (r12 != null) goto L23;
        if (r52.zzc() != null) goto L33;
    L24:
        zzml r4 = this.zzd;
        if (r4 != null) goto L30;
        if (r52.zzd() != null) goto L33;
    L32:
        return true;
    L30:
        if (r4.equals(r52.zzd()) == true) goto L32;
    L23:
        if (r12.equals(r52.zzc()) == false) goto L33;
    L15:
        if (r1 == r52.zzb()) goto L17;
    L33:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        zzbt r2 = this.zzb;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = ((r0 * 1000003) ^ r22) * 1000003;
        zzha r23 = this.zzc;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r03 = (r02 ^ r24) * 1000003;
        zzml r4 = this.zzd;
        if (r4 == null) goto L15;
        r3 = r4.hashCode();
    L15:
        return r03 ^ r3;
    L9:
        r24 = r23.hashCode();
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        zzml r2 = this.zzd;
        zzha r3 = this.zzc;
        String r7 = String.valueOf(this.zzb);
        String r32 = String.valueOf(r3);
        String r22 = String.valueOf(r2);
        int r4 = r7.length();
        int r5 = r32.length();
        int r12 = ((((r1 + 46) + r4) + 23) + r5) + 17;
        StringBuilder r42 = new StringBuilder((r12 + r22.length()) + 1);
        r42.append("LlmResult{results=");
        r42.append(r0);
        r42.append(", inferenceEventTraceResult=");
        r42.append(r7);
        r42.append(", legionResultMetadata=");
        r42.append(r32);
        r42.append(", thoughtProcess=");
        r42.append(r22);
        r42.append("}");
        return r42.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzic
    public final zzml zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzic
    public final zzbt zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzic
    public final zzha zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzic
    public final zzml zzd() {
        return this.zzd;
    }
}
