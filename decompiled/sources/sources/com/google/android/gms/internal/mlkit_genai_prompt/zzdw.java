package com.google.android.gms.internal.mlkit_genai_prompt;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzdw extends zzea {
    private byte zza;

    public zzdw() {
    }

    public final zzea zza(boolean r1) {
        this.zza = 1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzea
    public final zzeb zzb() {
        if (this.zza == 1) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: isMinorUser");
        return null;
    L5:
        return new zzdx(false, null);
    }
}
