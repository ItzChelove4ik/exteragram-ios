package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ PassportActivity.PhoneConfirmationView f$0;
    public final /* synthetic */ Bundle f$1;
    public final /* synthetic */ TLRPC.TL_auth_resendCode f$2;

    public /* synthetic */ PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda7(PassportActivity.PhoneConfirmationView r1, Bundle r2, TLRPC.TL_auth_resendCode r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        PassportActivity.PhoneConfirmationView.$r8$lambda$8aLptDHmXzWfmAqxQEK9O6RtNxo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
