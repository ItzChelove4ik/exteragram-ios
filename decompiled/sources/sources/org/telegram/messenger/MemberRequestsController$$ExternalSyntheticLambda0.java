package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MemberRequestsController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MemberRequestsController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_chatInviteImporter f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ RequestDelegate f$6;

    public /* synthetic */ MemberRequestsController$$ExternalSyntheticLambda0(MemberRequestsController r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_chatInviteImporter r4, boolean r5, long r6, RequestDelegate r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MemberRequestsController.$r8$lambda$ZPdMXgGIptd2L8ZHunQOibnjR1U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
