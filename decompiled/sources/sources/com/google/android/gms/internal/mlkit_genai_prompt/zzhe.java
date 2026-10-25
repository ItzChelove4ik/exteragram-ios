package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.ParcelFileDescriptor;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzhe extends zzht {
    private zzml zza;
    private ParcelFileDescriptor zzb;

    public zzhe() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzht
    public final zzht zza(List r1) {
        this.zza = zzml.zzq(r1);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzht
    public final zzht zzb(ParcelFileDescriptor r1) {
        this.zzb = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzht
    public final zzhu zzc() {
        zzml r0 = this.zza;
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: promptPrefix");
        return null;
    L5:
        return new zzhf(r0, this.zzb, null);
    }
}
