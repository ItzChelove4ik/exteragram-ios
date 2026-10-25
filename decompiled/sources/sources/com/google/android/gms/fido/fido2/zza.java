package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.internal.fido.zzp;
import com.google.android.gms.internal.fido.zzs;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final /* synthetic */ class zza implements RemoteCall {
    public final /* synthetic */ Fido2ApiClient zza;
    public final /* synthetic */ PublicKeyCredentialCreationOptions zzb;

    public /* synthetic */ zza(Fido2ApiClient r1, PublicKeyCredentialCreationOptions r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r3, Object r4) {
        Fido2ApiClient r1 = this.zza;
        zzf r0 = new zzf(r1, (TaskCompletionSource) r4);
        ((zzs) ((zzp) r3).getService()).zzc(r0, this.zzb);
    }
}
