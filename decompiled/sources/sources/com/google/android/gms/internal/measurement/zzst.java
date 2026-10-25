package com.google.android.gms.internal.measurement;

import java.io.InputStream;

/* loaded from: classes4.dex */
public final class zzst implements zzrt {
    private zzst() {
    }

    public static zzst zzb() {
        return new zzst();
    }

    public static final InputStream zzc(zzrs r2) {
        return (InputStream) r2.zzc(r2.zza().zzd(r2.zzb())).get(0);
    }

    @Override // com.google.android.gms.internal.measurement.zzrt
    public final /* bridge */ /* synthetic */ Object zza(zzrs r1) {
        return zzc(r1);
    }
}
