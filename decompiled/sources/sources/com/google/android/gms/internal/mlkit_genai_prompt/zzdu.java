package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzdu extends zzdq {
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final int zzf;
    private final int zzg;
    private final String zzh;

    public zzdu(String r2, String r3, int r4, int r5, int r6, int r7, int r8, String r9) {
        if (r2 == null) goto L10;
        this.zza = r2;
        if (r3 == null) goto L8;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null modelName");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null name");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzdq) == false) goto L24;
        zzdq r52 = (zzdq) r5;
        if (this.zza.equals(r52.zza()) == false) goto L24;
        if (this.zzb.equals(r52.zzb()) == false) goto L24;
        if (this.zzc != r52.zzc()) goto L24;
        if (this.zzd != r52.zzd()) goto L24;
        if (this.zze != r52.zze()) goto L24;
        if (this.zzf != r52.zzf()) goto L24;
        if (this.zzg != r52.zzg()) goto L24;
        if (this.zzh.equals(r52.zzh()) == false) goto L24;
        return true;
    L24:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r02 = (r0 * 1000003) ^ this.zzb.hashCode();
        String r2 = this.zzh;
        int r03 = ((((((((r02 * 1000003) ^ this.zzc) * 1000003) ^ this.zzd) * 1000003) ^ this.zze) * 1000003) ^ this.zzf) * 1000003;
        return ((this.zzg ^ r03) * 1000003) ^ r2.hashCode();
    }

    public final String toString() {
        int r0 = this.zzc;
        int r1 = String.valueOf(r0).length();
        int r2 = this.zzd;
        int r3 = String.valueOf(r2).length();
        int r4 = this.zze;
        int r5 = String.valueOf(r4).length();
        int r6 = this.zzf;
        int r7 = String.valueOf(r6).length();
        int r8 = this.zzg;
        int r9 = String.valueOf(r8).length();
        String r11 = this.zza;
        int r12 = r11.length();
        String r13 = this.zzb;
        int r122 = (((((((((((r12 + 27) + r13.length()) + 7) + r1) + 10) + r3) + 5) + r5) + 10) + r7) + 13) + r9;
        String r15 = this.zzh;
        StringBuilder r10 = new StringBuilder(((r122 + 14) + r15.length()) + 1);
        r10.append("AiFeature{name=");
        r10.append(r11);
        r10.append(", modelName=");
        r10.append(r13);
        r10.append(", type=");
        r10.append(r0);
        r10.append(", variant=");
        r10.append(r2);
        r10.append(", id=");
        r10.append(r4);
        r10.append(", version=");
        r10.append(r6);
        r10.append(", customerId=");
        r10.append(r8);
        r10.append(", description=");
        r10.append(r15);
        r10.append("}");
        return r10.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final int zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final int zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final int zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final int zzg() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdq
    public final String zzh() {
        return this.zzh;
    }
}
