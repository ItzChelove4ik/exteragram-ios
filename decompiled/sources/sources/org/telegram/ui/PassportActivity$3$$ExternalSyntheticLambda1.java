package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$3$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.AnonymousClass3 f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ PassportActivity.ErrorRunnable f$2;
    public final /* synthetic */ TL_account.verifyEmail f$3;

    public /* synthetic */ PassportActivity$3$$ExternalSyntheticLambda1(PassportActivity.AnonymousClass3 r1, Runnable r2, PassportActivity.ErrorRunnable r3, TL_account.verifyEmail r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        PassportActivity.AnonymousClass3.$r8$lambda$ayoSN_ZN231RjnloosKGA9Xq4Fw(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
