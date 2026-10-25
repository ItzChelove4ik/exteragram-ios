package com.google.android.gms.internal.mlkit_genai_prompt;

import java.io.File;

/* loaded from: classes4.dex */
final class zzhi extends zzhy {
    private final zzml zza;
    private final float zzb;
    private final int zzc;
    private final float zzd;
    private final zzml zze;
    private final int zzf;
    private final int zzg;
    private final int zzh;
    private final zzik zzi;
    private final boolean zzj;
    private final int zzk;
    private final int zzl;
    private final int zzm;
    private final zzeb zzn;
    private final zzhu zzo;
    private final zzib zzp;
    private final boolean zzq;
    private final zzja zzr;

    public /* synthetic */ zzhi(zzml r1, float r2, int r3, float r4, zzml r5, int r6, int r7, int r8, zzik r9, boolean r10, int r11, int r12, int r13, File r14, File r15, File r16, int r17, zzeb r18, int r19, zzhu r20, zzib r21, boolean r22, zzja r23, byte[] r24) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = r12;
        this.zzm = r17;
        this.zzn = r18;
        this.zzo = r20;
        this.zzp = r21;
        this.zzq = r22;
        this.zzr = r23;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzhy) == false) goto L60;
        zzhy r52 = (zzhy) r5;
        if (this.zza.equals(r52.zza()) == false) goto L60;
        if (Float.floatToIntBits(this.zzb) != Float.floatToIntBits(r52.zzb())) goto L60;
        if (this.zzc != r52.zzc()) goto L60;
        if (Float.floatToIntBits(this.zzd) != Float.floatToIntBits(r52.zzd())) goto L60;
        if (this.zze.equals(r52.zze()) == false) goto L60;
        if (this.zzf != r52.zzf()) goto L60;
        if (this.zzg != r52.zzg()) goto L60;
        if (this.zzh != r52.zzh()) goto L60;
        zzik r1 = this.zzi;
        if (r1 != null) goto L29;
        if (r52.zzi() != null) goto L60;
    L31:
        if (this.zzj != r52.zzj()) goto L60;
        if (this.zzk != r52.zzk()) goto L60;
        if (this.zzl != r52.zzl()) goto L60;
        r52.zzm();
        r52.zzn();
        r52.zzo();
        r52.zzp();
        if (this.zzm != r52.zzq()) goto L60;
        if (this.zzn.equals(r52.zzr()) == false) goto L60;
        r52.zzs();
        zzhu r12 = this.zzo;
        if (r12 != null) goto L46;
        if (r52.zzt() != null) goto L60;
    L47:
        zzib r13 = this.zzp;
        if (r13 != null) goto L53;
        if (r52.zzu() != null) goto L60;
    L56:
        if (this.zzq != r52.zzv()) goto L60;
        if (this.zzr.equals(r52.zzw()) == false) goto L60;
        return true;
    L53:
        if (r13.equals(r52.zzu()) == true) goto L56;
    L46:
        if (r12.equals(r52.zzt()) == false) goto L60;
    L29:
        if (r1.equals(r52.zzi()) == true) goto L31;
    L60:
        return false;
    }

    public final int hashCode() {
        int r0 = ((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003) ^ this.zzc) * 1000003) ^ Float.floatToIntBits(this.zzd)) * 1000003) ^ this.zze.hashCode();
        zzik r2 = this.zzi;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = ((((((((r0 * 1000003) ^ this.zzf) * 1000003) ^ this.zzg) * 1000003) ^ this.zzh) * 1000003) ^ r22) * 1000003;
        int r4 = 1231;
        if (true == this.zzj) goto L9;
        int r23 = 1237;
    L10:
        int r03 = (((((((r02 ^ r23) * 1000003) ^ this.zzk) * 1000003) ^ this.zzl) * (-429739981)) ^ this.zzm) * 1000003;
        zzhu r24 = this.zzo;
        if (r24 != null) goto L13;
        int r25 = 0;
    L14:
        int r04 = (((r03 ^ 1001110) * (-721379959)) ^ r25) * 1000003;
        zzib r26 = this.zzp;
        if (r26 == null) goto L18;
        r3 = r26.hashCode();
    L18:
        int r05 = (r04 ^ r3) * 1000003;
        if (true == this.zzq) goto L22;
        r4 = 1237;
    L22:
        return this.zzr.hashCode() ^ ((r05 ^ r4) * 1000003);
    L13:
        r25 = r24.hashCode();
        goto L14
    L9:
        r23 = 1231;
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public final String toString() {
        String r1 = this.zza.toString();
        int r2 = r1.length();
        float r3 = this.zzb;
        String r4 = this.zze.toString();
        String r5 = String.valueOf(this.zzi);
        String r6 = String.valueOf(this.zzo);
        String r7 = String.valueOf(this.zzp);
        String r8 = this.zzr.toString();
        int r9 = String.valueOf(r3).length();
        int r10 = this.zzc;
        int r11 = String.valueOf(r10).length();
        float r12 = this.zzd;
        int r13 = String.valueOf(r12).length();
        int r14 = r4.length();
        int r15 = this.zzf;
        int r16 = String.valueOf(r15).length();
        int r22 = this.zzg;
        int r18 = String.valueOf(r22).length();
        int r92 = this.zzh;
        int r20 = String.valueOf(r92).length();
        int r21 = r5.length();
        boolean r112 = this.zzj;
        int r23 = String.valueOf(r112).length();
        int r132 = this.zzk;
        int r25 = String.valueOf(r132).length();
        int r142 = this.zzl;
        int r27 = String.valueOf(r142).length();
        int r82 = this.zzm;
        int r29 = String.valueOf(r82).length();
        int r30 = r6.length();
        int r31 = r7.length();
        boolean r0 = this.zzq;
        int r32 = String.valueOf(r0).length();
        int r17 = ((((((((((((((((((((((((((((((r2 + 34) + r9) + 7) + r11) + 7) + r13) + 17) + r14) + 23) + r16) + 23) + r18) + 13) + r20) + 20) + r21) + 26) + r23) + 22) + r25) + 23) + r27) + 87) + r29) + 90) + r30) + 20) + r31) + 17) + r32) + 8;
        StringBuilder r02 = new StringBuilder((r17 + r8.length()) + 1);
        r02.append("LlmRequest{messages=");
        r02.append(r1);
        r02.append(", temperature=");
        r02.append(r3);
        r02.append(", topK=");
        r02.append(r10);
        r02.append(", topP=");
        r02.append(r12);
        r02.append(", stopTokensList=");
        r02.append(r4);
        r02.append(", targetReplyLengthMin=");
        r02.append(r15);
        r02.append(", targetReplyLengthMax=");
        r02.append(r22);
        r02.append(", numSamples=");
        r02.append(r92);
        r02.append(", streamingCallback=");
        r02.append(r5);
        r02.append(", shouldApplySafetyFilter=");
        r02.append(r112);
        r02.append(", preferredImageWidth=");
        r02.append(r132);
        r02.append(", preferredImageHeight=");
        r02.append(r142);
        r02.append(", numSoftTokens=0, loraFile=null, drafterLoraFile=null, sessionStateFile=null, rngSeed=");
        r02.append(r82);
        r02.append(", userProperties=UserProperties{isMinorUser=false}, overrideRequestKind=0, llmPrefixParam=");
        r02.append(r6);
        r02.append(", llmResponseSchema=");
        r02.append(r7);
        r02.append(", enableThinking=");
        r02.append(r0);
        r02.append(", tools=");
        r02.append(r8);
        r02.append("}");
        return r02.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzml zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final float zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final float zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzml zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzg() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzh() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzik zzi() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final boolean zzj() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzk() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzl() {
        return this.zzl;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzm() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final File zzn() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final File zzo() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final File zzp() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzq() {
        return this.zzm;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzeb zzr() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final int zzs() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzhu zzt() {
        return this.zzo;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzib zzu() {
        return this.zzp;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final boolean zzv() {
        return this.zzq;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhy
    public final zzja zzw() {
        return this.zzr;
    }
}
