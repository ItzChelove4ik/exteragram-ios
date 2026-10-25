package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzcz implements RemoteCall {
    private final /* synthetic */ LocationSettingsRequest zza;

    public /* synthetic */ zzcz(LocationSettingsRequest r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        TaskCompletionSource r42 = (TaskCompletionSource) r4;
        zzec r32 = (zzec) r3;
        LocationSettingsRequest r2 = this.zza;
        if (r2 == null) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "locationSettingsRequest can't be null");
        ((zzu) r32.getService()).zzD(r2, new zzdd(r42), null);
        return;
    L5:
        r0 = false;
        goto L6
    }
}
