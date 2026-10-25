package com.google.android.gms.auth.blockstore.restorecredential.internal;

import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final /* synthetic */ class InternalRestoreCredentialClient$$ExternalSyntheticLambda2 implements RemoteCall {
    public /* synthetic */ GetRestoreCredentialRequest f$0;

    public /* synthetic */ InternalRestoreCredentialClient$$ExternalSyntheticLambda2(GetRestoreCredentialRequest r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r1, Object r2) {
        InternalRestoreCredentialClient.$r8$lambda$6nzcDLrXx618XzL1jxRATH2b6h0(this.f$0, (RestoreCredentialClientImpl) r1, (TaskCompletionSource) r2);
    }
}
