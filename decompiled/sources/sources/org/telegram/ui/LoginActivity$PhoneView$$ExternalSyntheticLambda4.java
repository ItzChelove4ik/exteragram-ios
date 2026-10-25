package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$PhoneView$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.PhoneView f$0;
    public final /* synthetic */ Bundle f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ LoginActivity.PhoneInputData f$3;
    public final /* synthetic */ TLObject f$4;

    public /* synthetic */ LoginActivity$PhoneView$$ExternalSyntheticLambda4(LoginActivity.PhoneView r1, Bundle r2, String r3, LoginActivity.PhoneInputData r4, TLObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        LoginActivity.PhoneView.m15989$r8$lambda$20AKXKAQsUZSeL8CFaznuf2D6U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
