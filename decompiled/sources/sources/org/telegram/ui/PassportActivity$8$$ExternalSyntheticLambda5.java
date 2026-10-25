package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$8$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.AnonymousClass8 f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ PassportActivity$8$$ExternalSyntheticLambda5(PassportActivity.AnonymousClass8 r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PassportActivity.AnonymousClass8.$r8$lambda$_EqkzsIFPLt8G8hHD_DhSgFnVH0(this.f$0, this.f$1, r2, r3);
    }
}
