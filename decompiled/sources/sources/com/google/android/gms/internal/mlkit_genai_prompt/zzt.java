package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzt extends zzcf {
    private zzml zza;
    private float zzb;
    private int zzc;
    private zzml zzd;
    private int zze;
    private int zzf;
    private zzbl zzg;
    private boolean zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private Bundle zzl;
    private int zzm;
    private zzcw zzn;
    private zzcb zzo;
    private float zzp;
    private zzcp zzq;
    private boolean zzr;
    private zzdf zzs;
    private short zzt;

    public zzt() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zza(List r1) {
        this.zza = zzml.zzq(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzb(float r1) {
        this.zzb = r1;
        this.zzt = (short) (this.zzt | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzc(int r1) {
        this.zzc = r1;
        this.zzt = (short) (this.zzt | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzd(List r1) {
        this.zzd = zzml.zzq(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zze(int r1) {
        this.zze = r1;
        this.zzt = (short) (this.zzt | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzf(int r1) {
        this.zzf = r1;
        this.zzt = (short) (this.zzt | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzg(zzbl r1) {
        this.zzg = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzh(boolean r1) {
        this.zzh = r1;
        this.zzt = (short) (this.zzt | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzi(int r1) {
        this.zzi = r1;
        this.zzt = (short) (this.zzt | 32);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzj(int r1) {
        this.zzj = r1;
        this.zzt = (short) (this.zzt | 64);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzk(int r1) {
        this.zzk = r1;
        this.zzt = (short) (this.zzt | 128);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzl(Bundle r1) {
        this.zzl = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzm(int r1) {
        this.zzm = r1;
        this.zzt = (short) (this.zzt | 256);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzn(zzcw r1) {
        this.zzn = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzo(int r1) {
        this.zzt = (short) (this.zzt | 512);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzp(zzcb r1) {
        this.zzo = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzq(int r1) {
        this.zzt = (short) (this.zzt | 1024);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzr(float r1) {
        this.zzp = r1;
        this.zzt = (short) (this.zzt | 2048);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzs(zzcp r1) {
        this.zzq = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzt(boolean r1) {
        this.zzr = r1;
        this.zzt = (short) (this.zzt | 4096);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcf zzu(zzdf r1) {
        this.zzs = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzcf
    public final zzcg zzv() {
        if (this.zzt == 8191) goto L5;
    L11:
        StringBuilder r1 = new StringBuilder();
        if (this.zza != null) goto L15;
        r1.append(" messages");
    L15:
        if ((this.zzt & 1) != 0) goto L18;
        r1.append(" temperature");
    L18:
        if ((this.zzt & 2) != 0) goto L21;
        r1.append(" topK");
    L21:
        if (this.zzd != null) goto L24;
        r1.append(" stopTokensList");
    L24:
        if ((this.zzt & 4) != 0) goto L27;
        r1.append(" targetReplyLengthMin");
    L27:
        if ((this.zzt & 8) != 0) goto L30;
        r1.append(" targetReplyLengthMax");
    L30:
        if ((this.zzt & 16) != 0) goto L33;
        r1.append(" applySafetyFilter");
    L33:
        if ((this.zzt & 32) != 0) goto L36;
        r1.append(" numSamples");
    L36:
        if ((this.zzt & 64) != 0) goto L39;
        r1.append(" preferredImageWidth");
    L39:
        if ((this.zzt & 128) != 0) goto L42;
        r1.append(" preferredImageHeight");
    L42:
        if ((this.zzt & 256) != 0) goto L45;
        r1.append(" rngSeed");
    L45:
        if ((this.zzt & 512) != 0) goto L48;
        r1.append(" overrideRequestKind");
    L48:
        if ((this.zzt & 1024) != 0) goto L51;
        r1.append(" numSoftTokens");
    L51:
        if ((this.zzt & 2048) != 0) goto L54;
        r1.append(" topP");
    L54:
        if ((this.zzt & 4096) != 0) goto L56;
        r1.append(" enableThinking");
    L56:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r1.toString()));
        return null;
    L5:
        if (this.zza == null) goto L11;
        if (this.zzd == null) goto L11;
        return new zzcg(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, null, this.zzj, this.zzk, null, this.zzl, this.zzm, this.zzn, 0, this.zzo, 0, this.zzp, this.zzq, this.zzr, this.zzs);
    }
}
