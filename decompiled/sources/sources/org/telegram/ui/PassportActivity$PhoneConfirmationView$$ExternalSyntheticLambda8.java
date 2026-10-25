package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ PassportActivity.PhoneConfirmationView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.TL_auth_resendCode f$4;

    public /* synthetic */ PassportActivity$PhoneConfirmationView$$ExternalSyntheticLambda8(PassportActivity.PhoneConfirmationView r1, TLRPC.TL_error r2, Bundle r3, TLObject r4, TLRPC.TL_auth_resendCode r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.PhoneConfirmationView.$r8$lambda$Q3JWWB1UxyvFNruVD2eiN2jKxYk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
