package com.google.android.gms.internal.mlkit_genai_prompt;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzmw extends zzns {
    private final Object zza;
    private boolean zzb;

    public zzmw(Object r1) {
        this.zza = r1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb == true) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.zzb == true) goto L6;
        this.zzb = true;
        return this.zza;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
