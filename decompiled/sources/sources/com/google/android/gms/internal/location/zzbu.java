package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbu implements RemoteCall {
    static final /* synthetic */ zzbu zza = null;

    static {
        zza = new zzbu();
    }

    private /* synthetic */ zzbu() {
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r1, Object r2) {
        zzec r12 = (zzec) r1;
        Api r0 = zzcg.zzb;
        LastLocationRequest r02 = new LastLocationRequest.Builder().build();
        r12.zzq(r02, (TaskCompletionSource) r2);
    }
}
