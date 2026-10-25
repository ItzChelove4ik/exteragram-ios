package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ PassportActivity.PhoneConfirmationView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TL_account.verifyPhone f$2;

    public /* synthetic */ PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda6(PassportActivity.PhoneConfirmationView r1, TLRPC.TL_error r2, TL_account.verifyPhone r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.PhoneConfirmationView.$r8$lambda$FicRrCYzJYKw_2aGYdV7uFyhROU(this.f$0, this.f$1, this.f$2);
    }
}
