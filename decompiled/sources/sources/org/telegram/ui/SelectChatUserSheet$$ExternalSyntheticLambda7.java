package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TwoStepVerificationActivity f$3;
    public final /* synthetic */ TLRPC.User f$4;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda7(SelectChatUserSheet r1, TLRPC.TL_error r2, TLObject r3, TwoStepVerificationActivity r4, TLRPC.User r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SelectChatUserSheet.m18767$r8$lambda$YpTTXV2Zgf0XUWx1OnbyQFQlQc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
