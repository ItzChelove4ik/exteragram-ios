package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.ApiMetadata;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzel extends zzfa {
    final /* synthetic */ zzet zza;

    public zzel(zzet r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzfb
    public final void zzb(int r2, ApiMetadata r3) {
        zzet.zzb().d("onRemoteDisplayEnded", new Object[0]);
        this.zza.zza();
    }
}
