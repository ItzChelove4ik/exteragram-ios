package com.google.android.recaptcha.internal;

import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzaek extends zzael {
    final /* synthetic */ zzaer zza;
    private int zzb;
    private final int zzc;

    public zzaek(zzaer r2) {
        Objects.requireNonNull(r2);
        this.zza = r2;
        this.zzb = 0;
        this.zzc = r2.zzb();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb >= this.zzc) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaen
    public final byte zza() {
        int r0 = this.zzb;
        if (r0 >= this.zzc) goto L6;
        this.zzb = r0 + 1;
        return this.zza.zza(r0);
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
