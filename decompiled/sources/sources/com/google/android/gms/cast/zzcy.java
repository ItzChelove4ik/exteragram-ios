package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcy implements ResultCallback {
    final /* synthetic */ zzcz zza;
    private final long zzb;

    public zzcy(zzcz r1, long r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* bridge */ /* synthetic */ void onResult(Result r4) {
        Status r42 = (Status) r4;
        if (r42.isSuccess() == true) goto L6;
        zzcz r0 = this.zza;
        long r1 = this.zzb;
        int r3 = r42.getStatusCode();
        r0.zza.zzg().zzN(r1, r3);
        return;
    }
}
