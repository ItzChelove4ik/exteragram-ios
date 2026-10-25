package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;

/* loaded from: classes4.dex */
final class zzeg extends zzeo {
    private final ParcelFileDescriptor zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final String zze;
    private final int zzf;
    private final zzwh zzg;

    public /* synthetic */ zzeg(zzml r1, ParcelFileDescriptor r2, zzml r3, float r4, int r5, int r6, int r7, zzev r8, int r9, int r10, int r11, int r12, String r13, String r14, int r15, zzwh r16, byte[] r17) {
        this.zza = r2;
        this.zzb = r5;
        this.zzc = r6;
        this.zzd = r7;
        this.zzf = r12;
        this.zze = r13;
        this.zzg = r16;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzeo) == false) goto L35;
        zzeo r52 = (zzeo) r5;
        r52.zza();
        if (this.zza.equals(r52.zzb()) == false) goto L35;
        r52.zzc();
        int r3 = Float.floatToIntBits(0.0f);
        r52.zzd();
        if (r3 != Float.floatToIntBits(0.0f)) goto L35;
        if (this.zzb != r52.zze()) goto L35;
        if (this.zzc != r52.zzf()) goto L35;
        if (this.zzd != r52.zzg()) goto L35;
        r52.zzh();
        r52.zzi();
        r52.zzj();
        r52.zzk();
        if (this.zzf != r52.zzo()) goto L35;
        String r1 = this.zze;
        if (r1 != null) goto L25;
        if (r52.zzl() != null) goto L35;
    L26:
        r52.zzm();
        r52.zzn();
        zzwh r4 = this.zzg;
        if (r4 != null) goto L32;
        if (r52.zzp() != null) goto L35;
    L34:
        return true;
    L32:
        if (r4 == r52.zzp()) goto L34;
    L25:
        if (r1.equals(r52.zzl()) == true) goto L26;
    L35:
        return false;
    }

    public final int hashCode() {
        int r0 = ((this.zza.hashCode() ^ (-721379959)) * (-721379959)) ^ Float.floatToIntBits(0.0f);
        String r1 = this.zze;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = r0 * 1000003;
        int r4 = this.zzb;
        int r5 = this.zzc;
        int r6 = this.zzd;
        int r7 = this.zzf;
        zzwh r8 = this.zzg;
        if (r8 == null) goto L11;
        r2 = r8.hashCode();
    L11:
        return ((((((((((r02 ^ r4) * 1000003) ^ r5) * 1000003) ^ r6) * (-429739981)) ^ r7) * 1000003) ^ r12) * 583896283) ^ r2;
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r2 = String.valueOf(this.zzg);
        int r3 = String.valueOf(0.0f).length();
        int r4 = this.zzb;
        int r5 = String.valueOf(r4).length();
        int r6 = this.zzc;
        int r7 = String.valueOf(r6).length();
        int r8 = this.zzd;
        int r9 = String.valueOf(r8).length();
        String r10 = this.zze;
        int r11 = String.valueOf(r10).length();
        int r12 = r2.length();
        String r13 = zzen.zza(this.zzf);
        StringBuilder r32 = new StringBuilder(((((((((((((((r1 + 118) + r3) + 7) + r5) + 23) + r7) + 13) + r9) + 102) + r13.length()) + 15) + r11) + 66) + r12) + 1);
        r32.append("CortanaRequest{messages=null, cortanaStateFileDescriptorToWrite=");
        r32.append(r0);
        r32.append(", cortanaStateFileDescriptorsToRead=null, temperature=0.0, topK=");
        r32.append(r4);
        r32.append(", targetReplyLengthMax=");
        r32.append(r6);
        r32.append(", numSamples=");
        r32.append(r8);
        r32.append(", streamingCallback=null, preferredImageWidth=0, preferredImageHeight=0, numSoftTokens=0, cortanaType=");
        r32.append(r13);
        r32.append(", systemPrompt=");
        r32.append(r10);
        r32.append(", textToMemory=null, tokenOffset=0, inferenceStateCachingCallback=");
        r32.append(r2);
        r32.append("}");
        return r32.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final zzml zza() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final ParcelFileDescriptor zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final zzml zzc() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final float zzd() {
        return 0.0f;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzf() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzg() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final zzev zzh() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzi() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzj() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzk() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final String zzl() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    @Deprecated
    public final String zzm() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzn() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final int zzo() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzeo
    public final zzwh zzp() {
        return this.zzg;
    }
}
