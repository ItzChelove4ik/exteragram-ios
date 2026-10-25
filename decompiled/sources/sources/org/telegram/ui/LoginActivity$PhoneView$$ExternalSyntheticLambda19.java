package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$PhoneView$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ LoginActivity.PhoneView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Bundle f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ LoginActivity.PhoneInputData f$5;
    public final /* synthetic */ TLObject f$6;

    public /* synthetic */ LoginActivity$PhoneView$$ExternalSyntheticLambda19(LoginActivity.PhoneView r1, TLRPC.TL_error r2, TLObject r3, Bundle r4, String r5, LoginActivity.PhoneInputData r6, TLObject r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.PhoneView.$r8$lambda$fnwtZZeYBkwTOb3XbFHwvoTrVC0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
