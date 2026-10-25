package com.google.android.gms.internal.clearcut;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzbv extends zzbu<Object> {
    public zzbv() {
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final int zza(Map.Entry<?, ?> r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getKey());
        throw null;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final zzby<Object> zza(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final void zza(zzfr r1, Map.Entry<?, ?> r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.getKey());
        int[] r0 = zzbw.zzgq;
        throw null;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final void zza(Object r1, zzby<Object> r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final zzby<Object> zzb(Object r3) {
        zzby<Object> r0 = zza(r3);
        if (r0.isImmutable() == false) goto L6;
        zzby<Object> r02 = (zzby) r0.clone();
        zza(r3, r02);
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final void zzc(Object r1) {
        zza(r1).zzv();
    }

    @Override // com.google.android.gms.internal.clearcut.zzbu
    public final boolean zze(zzdo r1) {
        return false;
    }
}
