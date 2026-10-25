package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzgx extends zzgz {
    private String zza;

    public zzgx() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzgz
    public final zzgz zza(String r1) {
        if (r1 == null) goto L5;
        this.zza = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null modelVersion");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzgz
    public final zzha zzb() {
        String r2 = this.zza;
        if (r2 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: modelVersion");
        return null;
    L5:
        return new zzgy(r2, null);
    }
}
