package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$PhoneConfirmationView$5$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.PhoneConfirmationView.AnonymousClass5 f$0;

    public /* synthetic */ PassportActivity$PhoneConfirmationView$5$$ExternalSyntheticLambda1(PassportActivity.PhoneConfirmationView.AnonymousClass5 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        PassportActivity.PhoneConfirmationView.AnonymousClass5.$r8$lambda$HXQAwzEcuOwEMQ7wNIda0c6VE2w(this.f$0, r1, r2);
    }
}
