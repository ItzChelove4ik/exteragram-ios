package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzk implements RemoteCall {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ String[] zzb;

    public /* synthetic */ zzk(zzn r1, String[] r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        zzo r32 = (zzo) r3;
        zzh r1 = new zzh(this.zza, (TaskCompletionSource) r4);
        zzak r42 = (zzak) r32.getService();
        ApiMetadata r33 = zzff.zza(r32.getContext());
        r42.zzg(r1, this.zzb, r33);
    }
}
