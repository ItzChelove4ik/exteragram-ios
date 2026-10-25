package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$PhoneView$$ExternalSyntheticLambda30 implements Runnable {
    public final /* synthetic */ LoginActivity.PhoneView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ LoginActivity$PhoneView$$ExternalSyntheticLambda30(LoginActivity.PhoneView r1, TLRPC.TL_error r2, TLObject r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.PhoneView.$r8$lambda$gifPuoJX9UdUwMw_knLjyCzVDUw(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
