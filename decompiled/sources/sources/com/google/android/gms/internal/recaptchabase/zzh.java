package com.google.android.gms.internal.recaptchabase;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.recaptchabase.InitRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzh implements RemoteCall {
    public final /* synthetic */ InitRequest zza;

    public /* synthetic */ zzh(InitRequest r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r2, Object r3) {
        int r0 = zzl.$r8$clinit;
        InitRequest r1 = this.zza;
        zzk r02 = new zzk((TaskCompletionSource) r3);
        ((zzf) ((zzm) r2).getService()).zzd(r02, r1);
    }
}
