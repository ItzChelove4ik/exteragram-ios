package com.google.android.gms.internal.clearcut;

import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

/* loaded from: classes4.dex */
final class zzh extends BaseImplementation$ApiMethodImpl<Status, zzj> {
    private final com.google.android.gms.clearcut.zze zzao;

    public zzh(com.google.android.gms.clearcut.zze r2, GoogleApiClient r3) {
        super(ClearcutLogger.API, r3);
        this.zzao = r2;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status r1) {
        return r1;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient r7) {
        zzj r72 = (zzj) r7;
        zzi r0 = new zzi(this);
        com.google.android.gms.clearcut.zze r1 = this.zzao;     // Catch: RuntimeException -> L6
        r1.getClass();     // Catch: RuntimeException -> L6
        zzha r2 = r1.zzaa;     // Catch: RuntimeException -> L6
        int r3 = r2.zzas();     // Catch: RuntimeException -> L6
        byte[] r4 = new byte[r3];     // Catch: RuntimeException -> L6
        zzfz.zza(r2, r4, 0, r3);     // Catch: RuntimeException -> L6
        r1.zzah = r4;     // Catch: RuntimeException -> L6
        ((zzn) r72.getService()).zza(r0, this.zzao);
        return;
    L6:
        e = move-exception;
        Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
        setFailedResult(new Status(10, "MessageProducer"));
    }
}
