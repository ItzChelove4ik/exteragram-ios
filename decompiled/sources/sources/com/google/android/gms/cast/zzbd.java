package com.google.android.gms.cast;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbd implements RemoteCall {
    private final /* synthetic */ zzbm zza;

    public /* synthetic */ zzbd(zzbm r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        com.google.android.gms.cast.internal.zzy r32 = (com.google.android.gms.cast.internal.zzy) r3;
        com.google.android.gms.cast.internal.zzah r0 = (com.google.android.gms.cast.internal.zzah) r32.getService();
        ApiMetadata r1 = zzff.zza(r32.getContext());
        r0.zzq(this.zza.zza, r1);
        ((com.google.android.gms.cast.internal.zzah) r32.getService()).zzp(zzff.zza(r32.getContext()));
        ((TaskCompletionSource) r4).setResult(null);
    }
}
