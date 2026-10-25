package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$$ExternalSyntheticLambda27 implements RequestDelegate {
    public final /* synthetic */ LoginActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ LoginActivity$$ExternalSyntheticLambda27(LoginActivity r1, String r2, String r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        LoginActivity.$r8$lambda$1aXHA9EZTROJ8MihQemxxMAFQiI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
