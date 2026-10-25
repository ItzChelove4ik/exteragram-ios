package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$PhoneView$$ExternalSyntheticLambda26 implements Utilities.Callback3 {
    public final /* synthetic */ LoginActivity.PhoneView f$0;

    public /* synthetic */ LoginActivity$PhoneView$$ExternalSyntheticLambda26(LoginActivity.PhoneView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback3
    public final void run(Object r1, Object r2, Object r3) {
        LoginActivity.PhoneView.$r8$lambda$DXFtf44wD2X3aEsVUh8_3fTniK8(this.f$0, (Long) r1, (TLRPC.auth_Authorization) r2, (String) r3);
    }
}
