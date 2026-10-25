package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$20$1$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.AnonymousClass20.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_secureValue f$1;

    public /* synthetic */ PassportActivity$20$1$$ExternalSyntheticLambda2(PassportActivity.AnonymousClass20.AnonymousClass1 r1, TLRPC.TL_secureValue r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PassportActivity.AnonymousClass20.AnonymousClass1.$r8$lambda$G0ajPvRBgGVZdXS6QR9lQQ_9_rw(this.f$0, this.f$1, r2, r3);
    }
}
