package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.PhoneConfirmationView f$0;
    public final /* synthetic */ TL_account.verifyPhone f$1;

    public /* synthetic */ PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda0(PassportActivity.PhoneConfirmationView r1, TL_account.verifyPhone r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PassportActivity.PhoneConfirmationView.m16546$r8$lambda$eC3Xpafa9YA7wGWrbdQXPGPB6A(this.f$0, this.f$1, r2, r3);
    }
}
