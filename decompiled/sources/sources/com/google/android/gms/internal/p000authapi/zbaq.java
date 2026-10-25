package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zbaq implements RemoteCall {
    private final /* synthetic */ zbat zba;
    private final /* synthetic */ GetSignInIntentRequest zbb;

    public /* synthetic */ zbaq(zbat r1, GetSignInIntentRequest r2) {
        this.zba = r1;
        this.zbb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        zbau r32 = (zbau) r3;
        zban r1 = new zban(this.zba, (TaskCompletionSource) r4);
        ((zbv) r32.getService()).zbe(r1, (GetSignInIntentRequest) Preconditions.checkNotNull(this.zbb), zbba.zba(r32.getContext()));
    }
}
