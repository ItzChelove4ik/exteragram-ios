package com.google.android.recaptcha.internal;

import java.util.Objects;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzahj extends zzael {
    final zzahl zza;
    zzaen zzb;
    final /* synthetic */ zzahm zzc;

    public zzahj(zzahm r3) {
        Objects.requireNonNull(r3);
        this.zzc = r3;
        this.zza = new zzahl(r3, null);
        this.zzb = zzb();
    }

    private final zzaen zzb() {
        zzahl r1 = this.zza;
        if (r1.hasNext() == true) goto L5;
        return null;
    L5:
        return r1.zza().zzq();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zzb == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaen
    public final byte zza() {
        zzaen r0 = this.zzb;
        if (r0 == null) goto L8;
        byte r02 = r0.zza();
        if (this.zzb.hasNext() == true) goto L7;
        this.zzb = zzb();
    L7:
        return r02;
    L8:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
