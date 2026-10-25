package com.google.android.gms.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.cast.zzeu;
import com.google.android.gms.internal.cast.zzez;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzz implements RemoteCall {
    private final /* synthetic */ CastRemoteDisplayClient zza;

    public /* synthetic */ zzz(CastRemoteDisplayClient r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zzeu r22 = (zzeu) r2;
        zzx r0 = new zzx(this.zza, (TaskCompletionSource) r3);
        ((zzez) r22.getService()).zzi(r0, zzff.zza(r22.getContext()));
    }
}
