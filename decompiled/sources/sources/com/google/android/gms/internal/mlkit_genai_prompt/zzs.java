package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzs extends zzae {
    private zzml zza;
    private int zzb;
    private int zzc;
    private int zzd;
    private zzbb zze;
    private zzml zzf;
    private String zzg;
    private String zzh;
    private int zzi;
    private int zzj;
    private short zzk;

    public zzs() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zza(List r1) {
        this.zza = zzml.zzq(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzb(float r1) {
        this.zzk = (short) (this.zzk | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzc(int r1) {
        this.zzb = r1;
        this.zzk = (short) (this.zzk | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzd(int r1) {
        this.zzc = r1;
        this.zzk = (short) (this.zzk | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zze(int r1) {
        this.zzd = r1;
        this.zzk = (short) (this.zzk | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzf(zzbb r1) {
        this.zze = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzg(zzml r1) {
        if (r1 == null) goto L5;
        this.zzf = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null cortanaStateFileDescriptorsToRead");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzh(int r1) {
        this.zzk = (short) (this.zzk | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzi(int r1) {
        this.zzk = (short) (this.zzk | 32);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzj(String r1) {
        this.zzg = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzk(String r1) {
        this.zzh = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzl(int r1) {
        this.zzi = r1;
        this.zzk = (short) (this.zzk | 64);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzm(int r1) {
        this.zzk = (short) (this.zzk | 128);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzae zzn(int r1) {
        this.zzj = 1;
        this.zzk = (short) (this.zzk | 256);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzae
    public final zzaf zzo() {
        if (this.zzk == 511) goto L5;
    L13:
        StringBuilder r1 = new StringBuilder();
        if (this.zza != null) goto L17;
        r1.append(" messages");
    L17:
        if ((this.zzk & 1) != 0) goto L20;
        r1.append(" temperature");
    L20:
        if ((this.zzk & 2) != 0) goto L23;
        r1.append(" topK");
    L23:
        if ((this.zzk & 4) != 0) goto L26;
        r1.append(" targetReplyLengthMax");
    L26:
        if ((this.zzk & 8) != 0) goto L29;
        r1.append(" numSamples");
    L29:
        if (this.zzf != null) goto L32;
        r1.append(" cortanaStateFileDescriptorsToRead");
    L32:
        if ((this.zzk & 16) != 0) goto L35;
        r1.append(" preferredImageWidth");
    L35:
        if ((this.zzk & 32) != 0) goto L38;
        r1.append(" preferredImageHeight");
    L38:
        if (this.zzg != null) goto L41;
        r1.append(" cortanaType");
    L41:
        if ((this.zzk & 64) != 0) goto L44;
        r1.append(" tokenOffset");
    L44:
        if ((this.zzk & 128) != 0) goto L47;
        r1.append(" numSoftTokens");
    L47:
        if ((this.zzk & 256) != 0) goto L49;
        r1.append(" safetyClassificationMode");
    L49:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r1.toString()));
        return null;
    L5:
        if (this.zza == null) goto L13;
        if (this.zzf == null) goto L13;
        if (this.zzg == null) goto L13;
        return new zzaf(this.zza, 0.0f, this.zzb, this.zzc, null, this.zzd, this.zze, this.zzf, 0, 0, null, this.zzg, this.zzh, null, this.zzi, 0, this.zzj);
    }
}
