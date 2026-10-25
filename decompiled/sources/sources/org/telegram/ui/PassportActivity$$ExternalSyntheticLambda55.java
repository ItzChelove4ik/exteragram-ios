package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda55 implements RequestDelegate {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ PassportActivity.PassportActivityDelegate f$2;
    public final /* synthetic */ TL_account.sendVerifyPhoneCode f$3;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda55(PassportActivity r1, String r2, PassportActivity.PassportActivityDelegate r3, TL_account.sendVerifyPhoneCode r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        PassportActivity.$r8$lambda$RhNoKxT_kzn_D9qnQcR3eSWKz9w(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
