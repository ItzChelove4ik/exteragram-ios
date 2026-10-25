package com.google.android.gms.identitycredentials.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.identitycredentials.SignalCredentialStateRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzi implements RemoteCall {
    private final /* synthetic */ SignalCredentialStateRequest zza;

    public /* synthetic */ zzi(SignalCredentialStateRequest r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r1, Object r2) {
        SignalCredentialStateRequest r0 = this.zza;
        IdentityCredentialClientImpl r12 = (IdentityCredentialClientImpl) r1;
        InternalIdentityCredentialClient.zzj(r0, r12, (TaskCompletionSource) r2);
    }
}
