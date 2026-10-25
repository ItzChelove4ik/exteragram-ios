package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzaah implements zzaai {
    private final String zza;
    private final int zzb;

    public zzaah() {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzb = 2;
    }

    @Override // com.google.android.gms.internal.measurement.zzaai
    public final zzzf zza(String r8) {
        return new zzaar(this.zza, r8, true, 2, true, true);
    }
}
