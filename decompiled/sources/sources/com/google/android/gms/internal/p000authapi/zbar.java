package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zbar implements RemoteCall {
    private final /* synthetic */ zbat zba;
    private final /* synthetic */ GetPhoneNumberHintIntentRequest zbb;

    public /* synthetic */ zbar(zbat r1, GetPhoneNumberHintIntentRequest r2) {
        this.zba = r1;
        this.zbb = r2;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zbat r0 = this.zba;
        GetPhoneNumberHintIntentRequest r1 = this.zbb;
        zbau r22 = (zbau) r2;
        r0.zbb(r1, r22, (TaskCompletionSource) r3);
    }
}
