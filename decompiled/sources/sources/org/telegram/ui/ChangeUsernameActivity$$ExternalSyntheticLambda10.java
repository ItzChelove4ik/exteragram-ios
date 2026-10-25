package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ ChangeUsernameActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TL_account.checkUsername f$2;

    public /* synthetic */ ChangeUsernameActivity$$ExternalSyntheticLambda10(ChangeUsernameActivity r1, String r2, TL_account.checkUsername r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChangeUsernameActivity.m7143$r8$lambda$_ydbcXAUkhu5C0az44zHUqe_nE(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
