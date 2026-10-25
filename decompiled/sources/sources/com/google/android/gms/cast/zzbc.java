package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbc implements RemoteCall {
    private final /* synthetic */ zzbm zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Cast.MessageReceivedCallback zzc;

    public /* synthetic */ zzbc(zzbm r1, String r2, Cast.MessageReceivedCallback r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        zzbm r0 = this.zza;
        String r1 = this.zzb;
        Cast.MessageReceivedCallback r2 = this.zzc;
        com.google.android.gms.cast.internal.zzy r32 = (com.google.android.gms.cast.internal.zzy) r3;
        r0.zzA(r1, r2, r32, (TaskCompletionSource) r4);
    }
}
