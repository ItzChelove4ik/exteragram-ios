package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationSetupActivity$$ExternalSyntheticLambda40 implements RequestDelegate {
    public final /* synthetic */ TwoStepVerificationSetupActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ byte[] f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TL_account.passwordInputSettings f$4;

    public /* synthetic */ TwoStepVerificationSetupActivity$$ExternalSyntheticLambda40(TwoStepVerificationSetupActivity r1, boolean r2, byte[] r3, String r4, TL_account.passwordInputSettings r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        TwoStepVerificationSetupActivity.$r8$lambda$FFsnSFCYF71M6HG02KSszMQdLAk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
