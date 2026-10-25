package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzeq extends zzeo {
    final /* synthetic */ zzer zza;

    public zzeq(zzer r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzey
    public final void zzd(int r2, ApiMetadata r3) {
        int r32 = zzet.$r8$clinit;
        Object[] r22 = {Integer.valueOf(r2)};
        zzet.zzb().d("onError: %d", r22);
        zzer r1 = this.zza;
        r1.zzc.zza();
        r1.setResult(new zzes(Status.RESULT_INTERNAL_ERROR));
    }

    @Override // com.google.android.gms.internal.cast.zzeo, com.google.android.gms.internal.cast.zzey
    public final void zzf(ApiMetadata r3) {
        zzet.zzb().d("onDisconnected", new Object[0]);
        zzer r2 = this.zza;
        r2.zzc.zza();
        r2.setResult(new zzes(Status.RESULT_SUCCESS));
    }
}
