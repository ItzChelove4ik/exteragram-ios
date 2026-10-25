package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityPhraseView f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda12(LoginActivity.LoginActivityPhraseView r1, Bundle r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LoginActivity.LoginActivityPhraseView.m15864$r8$lambda$B5pfn987_adrMYrFGCaqk1h63I(this.f$0, this.f$1, r2, r3);
    }
}
