package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda17 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivityPhraseView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ TLObject f$3;

    public /* synthetic */ LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda17(LoginActivity.LoginActivityPhraseView r1, TLRPC.TL_error r2, Bundle r3, TLObject r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivityPhraseView.$r8$lambda$CbbnlKrlH9D9Y4YZrozbB8PYzLg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
