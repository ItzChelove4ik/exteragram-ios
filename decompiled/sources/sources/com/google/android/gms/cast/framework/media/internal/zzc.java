package com.google.android.gms.cast.framework.media.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzc extends zzh {
    final /* synthetic */ zzd zza;

    public /* synthetic */ zzc(zzd r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzi
    public final void zzb(long r1, long r3) {
        Long[] r12 = {Long.valueOf(r1), Long.valueOf(r3)};
        zzd.zza(this.zza, r12);
    }
}
