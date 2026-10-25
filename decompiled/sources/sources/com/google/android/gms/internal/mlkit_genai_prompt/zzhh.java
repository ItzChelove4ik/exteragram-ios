package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzhh extends zzhx {
    private zzml zza;
    private float zzb;
    private int zzc;
    private float zzd;
    private zzml zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private zzik zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private zzeb zzn;
    private zzhu zzo;
    private zzib zzp;
    private boolean zzq;
    private zzja zzr;
    private short zzs;

    public zzhh() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zza(List r1) {
        this.zza = zzml.zzq(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzb(float r1) {
        this.zzb = r1;
        this.zzs = (short) (this.zzs | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzc(int r1) {
        this.zzc = r1;
        this.zzs = (short) (this.zzs | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzd(float r1) {
        this.zzd = 1.0f;
        this.zzs = (short) (this.zzs | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zze(List r1) {
        this.zze = zzml.zzq(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzf(int r1) {
        this.zzf = r1;
        this.zzs = (short) (this.zzs | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzg(int r1) {
        this.zzg = r1;
        this.zzs = (short) (this.zzs | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzh(int r1) {
        this.zzh = r1;
        this.zzs = (short) (this.zzs | 32);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzi(zzik r1) {
        this.zzi = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzj(boolean r1) {
        this.zzj = true;
        this.zzs = (short) (this.zzs | 64);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzk(int r1) {
        this.zzk = r1;
        this.zzs = (short) (this.zzs | 128);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzl(int r1) {
        this.zzl = r1;
        this.zzs = (short) (this.zzs | 256);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzm(int r1) {
        this.zzs = (short) (this.zzs | 512);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzn(int r1) {
        this.zzm = r1;
        this.zzs = (short) (this.zzs | 1024);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzo(zzeb r1) {
        if (r1 == null) goto L5;
        this.zzn = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null userProperties");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzp(int r1) {
        this.zzs = (short) (this.zzs | 2048);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzq(zzhu r1) {
        this.zzo = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzr(zzib r1) {
        this.zzp = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzs(boolean r1) {
        this.zzq = r1;
        this.zzs = (short) (this.zzs | 4096);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhx zzt(zzja r1) {
        if (r1 == null) goto L5;
        this.zzr = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null tools");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzhx
    public final zzhy zzu() {
        if (this.zzs != 8191) goto L15;
        zzml r4 = this.zza;
        if (r4 == null) goto L15;
        zzml r8 = this.zze;
        if (r8 == null) goto L15;
        zzeb r1 = this.zzn;
        if (r1 == null) goto L15;
        zzja r2 = this.zzr;
        if (r2 == null) goto L15;
        return new zzhi(r4, this.zzb, this.zzc, this.zzd, r8, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, 0, null, null, null, this.zzm, r1, 0, this.zzo, this.zzp, this.zzq, r2, null);
    L15:
        StringBuilder r12 = new StringBuilder();
        if (this.zza != null) goto L19;
        r12.append(" messages");
    L19:
        if ((this.zzs & 1) != 0) goto L22;
        r12.append(" temperature");
    L22:
        if ((this.zzs & 2) != 0) goto L25;
        r12.append(" topK");
    L25:
        if ((this.zzs & 4) != 0) goto L28;
        r12.append(" topP");
    L28:
        if (this.zze != null) goto L31;
        r12.append(" stopTokensList");
    L31:
        if ((this.zzs & 8) != 0) goto L34;
        r12.append(" targetReplyLengthMin");
    L34:
        if ((this.zzs & 16) != 0) goto L37;
        r12.append(" targetReplyLengthMax");
    L37:
        if ((this.zzs & 32) != 0) goto L40;
        r12.append(" numSamples");
    L40:
        if ((this.zzs & 64) != 0) goto L43;
        r12.append(" shouldApplySafetyFilter");
    L43:
        if ((this.zzs & 128) != 0) goto L46;
        r12.append(" preferredImageWidth");
    L46:
        if ((this.zzs & 256) != 0) goto L49;
        r12.append(" preferredImageHeight");
    L49:
        if ((this.zzs & 512) != 0) goto L52;
        r12.append(" numSoftTokens");
    L52:
        if ((this.zzs & 1024) != 0) goto L55;
        r12.append(" rngSeed");
    L55:
        if (this.zzn != null) goto L58;
        r12.append(" userProperties");
    L58:
        if ((this.zzs & 2048) != 0) goto L61;
        r12.append(" overrideRequestKind");
    L61:
        if ((this.zzs & 4096) != 0) goto L64;
        r12.append(" enableThinking");
    L64:
        if (this.zzr != null) goto L66;
        r12.append(" tools");
    L66:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r12.toString()));
        return null;
    }
}
