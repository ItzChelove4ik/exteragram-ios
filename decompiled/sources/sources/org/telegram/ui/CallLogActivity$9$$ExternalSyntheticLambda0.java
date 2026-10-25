package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.CallLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$9$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ CallLogActivity.AnonymousClass9 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ CallLogActivity$9$$ExternalSyntheticLambda0(CallLogActivity.AnonymousClass9 r1, int r2, TLRPC.User r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        CallLogActivity.AnonymousClass9.$r8$lambda$O0LH8HKDgZ7dP2FTjz5ZKVY5TeM(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
