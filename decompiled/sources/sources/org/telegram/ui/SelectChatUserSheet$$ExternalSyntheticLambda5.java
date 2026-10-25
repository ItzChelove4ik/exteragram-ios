package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda5(SelectChatUserSheet r1, TwoStepVerificationActivity r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SelectChatUserSheet.m18766$r8$lambda$UvpLW9KmI6yjEtTyzufkIzci0o(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
