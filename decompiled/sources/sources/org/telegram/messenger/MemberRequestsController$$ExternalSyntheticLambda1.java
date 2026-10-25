package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MemberRequestsController$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ MemberRequestsController f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteImporter f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ RequestDelegate f$4;

    public /* synthetic */ MemberRequestsController$$ExternalSyntheticLambda1(MemberRequestsController r1, TLRPC.TL_chatInviteImporter r2, boolean r3, long r4, RequestDelegate r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MemberRequestsController.$r8$lambda$yU0iYAag51Ic1Xd0gczUH4WQUFg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
