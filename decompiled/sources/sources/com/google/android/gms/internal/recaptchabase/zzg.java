package com.google.android.gms.internal.recaptchabase;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzg implements RemoteCall {
    public final /* synthetic */ ExecuteRequest zza;

    public /* synthetic */ zzg(ExecuteRequest r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r2, Object r3) {
        int r0 = zzl.$r8$clinit;
        ExecuteRequest r1 = this.zza;
        zzj r02 = new zzj((TaskCompletionSource) r3);
        ((zzf) ((zzm) r2).getService()).zzc(r02, r1);
    }
}
