package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ ChangeUsernameActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TL_account.checkUsername f$4;

    public /* synthetic */ ChangeUsernameActivity$$ExternalSyntheticLambda11(ChangeUsernameActivity r1, String r2, TLRPC.TL_error r3, TLObject r4, TL_account.checkUsername r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChangeUsernameActivity.m7144$r8$lambda$g8M7qAD5CWY_PT1c0lFVdfkMpU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
