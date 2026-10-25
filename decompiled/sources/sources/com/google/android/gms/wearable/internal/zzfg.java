package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes5.dex */
final class zzfg extends zzu {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ byte[] zzc;

    public zzfg(zzfl r1, GoogleApiClient r2, String r3, String r4, byte[] r5) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        super(r2);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ Result createFailedResult(Status r2) {
        return new zzfk(r2, -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r5) {
        String r0 = this.zza;
        String r1 = this.zzb;
        byte[] r2 = this.zzc;
        ((zzfb) ((zzim) r5).getService()).zzz(new zzig(this), r0, r1, r2);
    }
}
