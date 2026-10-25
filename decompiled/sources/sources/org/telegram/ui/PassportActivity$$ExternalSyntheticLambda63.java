package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda63 implements Runnable {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ PassportActivity.PassportActivityDelegate f$3;
    public final /* synthetic */ TLObject f$4;
    public final /* synthetic */ TL_account.sendVerifyPhoneCode f$5;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda63(PassportActivity r1, TLRPC.TL_error r2, String r3, PassportActivity.PassportActivityDelegate r4, TLObject r5, TL_account.sendVerifyPhoneCode r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.$r8$lambda$CTmxbaIVebCEEpcvEeAV3vNmklY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
