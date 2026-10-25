package com.google.android.gms.identitycredentials.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.identitycredentials.GetCredentialRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzj implements RemoteCall {
    private final /* synthetic */ GetCredentialRequest zza;

    public /* synthetic */ zzj(GetCredentialRequest r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r1, Object r2) {
        GetCredentialRequest r0 = this.zza;
        IdentityCredentialClientImpl r12 = (IdentityCredentialClientImpl) r1;
        InternalIdentityCredentialClient.zza(r0, r12, (TaskCompletionSource) r2);
    }
}
