package org.telegram.ui.Delegates;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class MemberRequestsDelegate$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ MemberRequestsDelegate f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MemberRequestsDelegate$$ExternalSyntheticLambda4(MemberRequestsDelegate r1, boolean r2, Runnable r3, String r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MemberRequestsDelegate.$r8$lambda$x300BY5jOAleWfV_yPyG9IVmGog(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
