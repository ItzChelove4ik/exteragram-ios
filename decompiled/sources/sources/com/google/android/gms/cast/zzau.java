package com.google.android.gms.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzau implements RemoteCall {
    static final /* synthetic */ zzau zza = null;

    static {
        zza = new zzau();
    }

    private /* synthetic */ zzau() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r1, Object r2) {
        com.google.android.gms.cast.internal.zzy r12 = (com.google.android.gms.cast.internal.zzy) r1;
        int r0 = zzbm.zzf;
        ((com.google.android.gms.cast.internal.zzah) r12.getService()).zze(zzff.zza(r12.getContext()));
        ((TaskCompletionSource) r2).setResult(null);
    }
}
