package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzev extends zzfa {
    final /* synthetic */ zzfb zza;
    final /* synthetic */ zzew zzb;

    public zzev(zzew r1, zzfb r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzfb
    public final void zzb(int r4, ApiMetadata r5) {
        zzew.zzr().d("onRemoteDisplayEnded", new Object[0]);
        zzfb r0 = this.zza;
        if (r0 == null) goto L5;
        r0.zzb(r4, r5);
    L5:
        zzew r3 = this.zzb;
        if (r3.zzs() == null) goto L9;
        Status r52 = new Status(r4);
        r3.zzs().onRemoteDisplayEnded(r52);
        return;
    }
}
