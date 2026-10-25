package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/* loaded from: classes4.dex */
public final class zzd extends zzz {
    private BaseGmsClient zza;
    private final int zzb;

    public zzd(BaseGmsClient r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int r3, IBinder r4, Bundle r5) {
        Preconditions.checkNotNull(this.zza, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zza.onPostInitHandler(r3, r4, r5, this.zzb);
        this.zza = null;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzb(int r1, Bundle r2) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzc(int r3, IBinder r4, zzj r5) {
        BaseGmsClient r0 = this.zza;
        Preconditions.checkNotNull(r0, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
        Preconditions.checkNotNull(r5);
        r0.zzc(r5);
        onPostInitComplete(r3, r4, r5.zza);
    }
}
