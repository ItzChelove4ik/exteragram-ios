package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class TwoStepVerificationSetupActivity$$ExternalSyntheticLambda43 implements Runnable {
    public final /* synthetic */ TwoStepVerificationSetupActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ byte[] f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ TL_account.passwordInputSettings f$6;

    public /* synthetic */ TwoStepVerificationSetupActivity$$ExternalSyntheticLambda43(TwoStepVerificationSetupActivity r1, TLRPC.TL_error r2, boolean r3, TLObject r4, byte[] r5, String r6, TL_account.passwordInputSettings r7) {
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
        TwoStepVerificationSetupActivity.$r8$lambda$8EAcgVXOW3DxpeKWcNc21AC2Ecg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
