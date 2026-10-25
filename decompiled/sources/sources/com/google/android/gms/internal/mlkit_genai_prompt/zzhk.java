package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final class zzhk extends zzib {
    private final String zza;
    private final String zzb;
    private final Boolean zzc;
    private final zzml zzd;
    private final zzib zze;
    private final Long zzf;
    private final Long zzg;
    private final zzml zzh;
    private final zzmo zzi;
    private final Double zzj;
    private final Double zzk;
    private final int zzl;

    public /* synthetic */ zzhk(int r1, String r2, String r3, Boolean r4, zzml r5, zzib r6, Long r7, Long r8, zzml r9, zzmo r10, Long r11, Long r12, Double r13, Double r14, Long r15, Long r16, String r17, zzml r18, zzml r19, String r20, byte[] r21) {
        this.zzl = r1;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
        this.zzj = r13;
        this.zzk = r14;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzib) == false) goto L94;
        zzib r52 = (zzib) r5;
        int r1 = this.zzl;
        if (r1 != 0) goto L13;
        if (r52.zzt() != 0) goto L94;
    L14:
        String r12 = this.zza;
        if (r12 != null) goto L20;
        if (r52.zza() != null) goto L94;
    L21:
        String r13 = this.zzb;
        if (r13 != null) goto L27;
        if (r52.zzb() != null) goto L94;
    L28:
        Boolean r14 = this.zzc;
        if (r14 != null) goto L34;
        if (r52.zzc() != null) goto L94;
    L35:
        zzml r15 = this.zzd;
        if (r15 != null) goto L41;
        if (r52.zzd() != null) goto L94;
    L42:
        zzib r16 = this.zze;
        if (r16 != null) goto L48;
        if (r52.zze() != null) goto L94;
    L49:
        Long r17 = this.zzf;
        if (r17 != null) goto L55;
        if (r52.zzf() != null) goto L94;
    L56:
        Long r18 = this.zzg;
        if (r18 != null) goto L62;
        if (r52.zzg() != null) goto L94;
    L63:
        zzml r19 = this.zzh;
        if (r19 != null) goto L69;
        if (r52.zzh() != null) goto L94;
    L70:
        zzmo r110 = this.zzi;
        if (r110 != null) goto L76;
        if (r52.zzi() != null) goto L94;
    L77:
        r52.zzj();
        r52.zzk();
        Double r111 = this.zzj;
        if (r111 != null) goto L83;
        if (r52.zzl() != null) goto L94;
    L84:
        Double r4 = this.zzk;
        if (r4 != null) goto L90;
        if (r52.zzm() != null) goto L94;
    L92:
        r52.zzn();
        r52.zzo();
        r52.zzp();
        r52.zzq();
        r52.zzr();
        r52.zzs();
        return true;
    L90:
        if (r4.equals(r52.zzm()) == true) goto L92;
    L83:
        if (r111.equals(r52.zzl()) == false) goto L94;
    L76:
        if (r110.equals(r52.zzi()) == false) goto L94;
    L69:
        if (r19.equals(r52.zzh()) == false) goto L94;
    L62:
        if (r18.equals(r52.zzg()) == false) goto L94;
    L55:
        if (r17.equals(r52.zzf()) == false) goto L94;
    L48:
        if (r16.equals(r52.zze()) == false) goto L94;
    L41:
        if (r15.equals(r52.zzd()) == false) goto L94;
    L34:
        if (r14.equals(r52.zzc()) == false) goto L94;
    L27:
        if (r13.equals(r52.zzb()) == false) goto L94;
    L20:
        if (r12.equals(r52.zza()) == false) goto L94;
    L13:
        if (r1 == r52.zzt()) goto L14;
    L94:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzl;
        int r1 = 0;
        if (r0 != 0) goto L5;
        r0 = 0;
    L5:
        String r2 = this.zza;
        if (r2 != null) goto L8;
        int r22 = 0;
    L9:
        int r02 = (((r0 ^ 1000003) * 1000003) ^ r22) * 1000003;
        String r23 = this.zzb;
        if (r23 != null) goto L12;
        int r24 = 0;
    L13:
        int r03 = (r02 ^ r24) * 1000003;
        Boolean r25 = this.zzc;
        if (r25 != null) goto L16;
        int r26 = 0;
    L17:
        int r04 = (r03 ^ r26) * 1000003;
        zzml r27 = this.zzd;
        if (r27 != null) goto L20;
        int r28 = 0;
    L21:
        int r05 = (r04 ^ r28) * 1000003;
        zzib r29 = this.zze;
        if (r29 != null) goto L24;
        int r210 = 0;
    L25:
        int r06 = (r05 ^ r210) * 1000003;
        Long r211 = this.zzf;
        if (r211 != null) goto L28;
        int r212 = 0;
    L29:
        int r07 = (r06 ^ r212) * 1000003;
        Long r213 = this.zzg;
        if (r213 != null) goto L32;
        int r214 = 0;
    L33:
        int r08 = (r07 ^ r214) * 1000003;
        zzml r215 = this.zzh;
        if (r215 != null) goto L36;
        int r216 = 0;
    L37:
        int r09 = (r08 ^ r216) * 1000003;
        zzmo r217 = this.zzi;
        if (r217 != null) goto L40;
        int r218 = 0;
    L41:
        int r010 = r09 ^ r218;
        Double r219 = this.zzj;
        if (r219 != null) goto L44;
        int r220 = 0;
    L45:
        int r011 = ((r010 * 583896283) ^ r220) * 1000003;
        Double r5 = this.zzk;
        if (r5 == null) goto L50;
        r1 = r5.hashCode();
    L50:
        return (r011 ^ r1) * 272515929;
    L44:
        r220 = r219.hashCode();
        goto L45
    L40:
        r218 = r217.hashCode();
        goto L41
    L36:
        r216 = r215.hashCode();
        goto L37
    L32:
        r214 = r213.hashCode();
        goto L33
    L28:
        r212 = r211.hashCode();
        goto L29
    L24:
        r210 = r29.hashCode();
        goto L25
    L20:
        r28 = r27.hashCode();
        goto L21
    L16:
        r26 = r25.hashCode();
        goto L17
    L12:
        r24 = r23.hashCode();
        goto L13
    L8:
        r22 = r2.hashCode();
        goto L9
    }

    public final String toString() {
        zzmo r1 = this.zzi;
        zzml r2 = this.zzh;
        zzib r3 = this.zze;
        String r4 = String.valueOf(this.zzd);
        String r32 = String.valueOf(r3);
        String r22 = String.valueOf(r2);
        String r12 = String.valueOf(r1);
        String r5 = this.zza;
        int r6 = String.valueOf(r5).length();
        String r7 = this.zzb;
        int r8 = String.valueOf(r7).length();
        Boolean r9 = this.zzc;
        int r10 = String.valueOf(r9).length();
        int r11 = r4.length();
        int r122 = r32.length();
        Long r13 = this.zzf;
        int r14 = String.valueOf(r13).length();
        Long r15 = this.zzg;
        int r16 = String.valueOf(r15).length();
        int r17 = r22.length();
        int r18 = r12.length();
        Double r62 = this.zzj;
        int r20 = String.valueOf(r62).length();
        Double r82 = this.zzk;
        int r222 = String.valueOf(r82).length();
        String r102 = zzia.zza(this.zzl);
        StringBuilder r0 = new StringBuilder(((((((((((((((((((((((r102.length() + 32) + r6) + 14) + r8) + 11) + r10) + 13) + r11) + 8) + r122) + 11) + r14) + 11) + r16) + 11) + r17) + 13) + r18) + 50) + r20) + 10) + r222) + 94);
        r0.append("LlmResponseSchema{type=");
        r0.append(r102);
        r0.append(", format=");
        r0.append(r5);
        r0.append(", description=");
        r0.append(r7);
        r0.append(", nullable=");
        r0.append(r9);
        r0.append(", enumValues=");
        r0.append(r4);
        r0.append(", items=");
        r0.append(r32);
        r0.append(", maxItems=");
        r0.append(r13);
        r0.append(", minItems=");
        r0.append(r15);
        r0.append(", required=");
        r0.append(r22);
        r0.append(", properties=");
        r0.append(r12);
        r0.append(", minProperties=null, maxProperties=null, minimum=");
        r0.append(r62);
        r0.append(", maximum=");
        r0.append(r82);
        r0.append(", minLength=null, maxLength=null, pattern=null, anyOf=null, propertyOrdering=null, title=null}");
        return r0.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final String zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Boolean zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final zzml zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final zzib zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Long zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Long zzg() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final zzml zzh() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final zzmo zzi() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Long zzj() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Long zzk() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Double zzl() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Double zzm() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Long zzn() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final Long zzo() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final String zzp() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final zzml zzq() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final zzml zzr() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final String zzs() {
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzib
    public final int zzt() {
        return this.zzl;
    }
}
