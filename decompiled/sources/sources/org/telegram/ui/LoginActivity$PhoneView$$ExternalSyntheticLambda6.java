package org.telegram.ui;

import java.util.HashMap;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$PhoneView$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.PhoneView f$0;
    public final /* synthetic */ HashMap f$1;

    public /* synthetic */ LoginActivity$PhoneView$$ExternalSyntheticLambda6(LoginActivity.PhoneView r1, HashMap r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LoginActivity.PhoneView.m15988$r8$lambda$00mOskyq2GPdBpM7Ki_JKxAogw(this.f$0, this.f$1, r2, r3);
    }
}
