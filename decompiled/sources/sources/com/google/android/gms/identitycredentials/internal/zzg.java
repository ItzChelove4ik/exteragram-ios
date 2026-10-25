package com.google.android.gms.identitycredentials.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.identitycredentials.CreateCredentialRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzg implements RemoteCall {
    private final /* synthetic */ CreateCredentialRequest zza;

    public /* synthetic */ zzg(CreateCredentialRequest r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r1, Object r2) {
        CreateCredentialRequest r0 = this.zza;
        IdentityCredentialClientImpl r12 = (IdentityCredentialClientImpl) r1;
        InternalIdentityCredentialClient.zzh(r0, r12, (TaskCompletionSource) r2);
    }
}
