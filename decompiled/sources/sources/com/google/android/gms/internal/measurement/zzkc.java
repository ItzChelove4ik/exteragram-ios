package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzkc implements RemoteCall {
    private final /* synthetic */ String zza;

    public /* synthetic */ zzkc(String r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        int r0 = zzkk.$r8$clinit;
        zzkj r02 = new zzkj((TaskCompletionSource) r4, null);
        ((zzkt) ((zzku) r3).getService()).zzg(r02, this.zza);
    }
}
