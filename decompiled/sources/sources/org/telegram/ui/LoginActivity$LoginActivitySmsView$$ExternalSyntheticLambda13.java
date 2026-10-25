package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivitySmsView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TL_account.confirmPhone f$2;

    public /* synthetic */ LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda13(LoginActivity.LoginActivitySmsView r1, TLRPC.TL_error r2, TL_account.confirmPhone r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivitySmsView.m15933$r8$lambda$ZjHaXiS8yHRSqqUnt08RjvMPFs(this.f$0, this.f$1, this.f$2);
    }
}
