package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzhm extends zzii {
    private zzdi zza;
    private zzdy zzb;
    private zzdq zzc;

    public zzhm() {
    }

    public final zzii zza(zzdi r1) {
        this.zza = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzii
    public final zzii zzb(zzdy r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzii
    public final zzii zzc(zzdq r1) {
        if (r1 == null) goto L5;
        this.zzc = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null feature");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzii
    public final zzij zzd() {
        zzdi r0 = this.zza;
        if (r0 == null) goto L11;
        zzdy r2 = this.zzb;
        if (r2 == null) goto L11;
        zzdq r3 = this.zzc;
        if (r3 == null) goto L11;
        return new zzhn(r0, r2, r3, null);
    L11:
        StringBuilder r02 = new StringBuilder();
        if (this.zza != null) goto L15;
        r02.append(" aiCoreClient");
    L15:
        if (this.zzb != null) goto L18;
        r02.append(" downloadCallback");
    L18:
        if (this.zzc != null) goto L20;
        r02.append(" feature");
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r02.toString()));
        return null;
    }
}
