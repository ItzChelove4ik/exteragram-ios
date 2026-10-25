package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzis extends zzix {
    private String zza;
    private zzmo zzb;

    public zzis() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzix
    public final zzix zza(String r1) {
        if (r1 == null) goto L5;
        this.zza = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null name");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzix
    public final zzix zzb(Map r1) {
        this.zzb = zzmo.zzd(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzix
    public final zziy zzc() {
        String r0 = this.zza;
        if (r0 == null) goto L9;
        zzmo r2 = this.zzb;
        if (r2 == null) goto L9;
        return new zzit(r0, r2, null);
    L9:
        StringBuilder r02 = new StringBuilder();
        if (this.zza != null) goto L13;
        r02.append(" name");
    L13:
        if (this.zzb != null) goto L15;
        r02.append(" args");
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r02.toString()));
        return null;
    }
}
