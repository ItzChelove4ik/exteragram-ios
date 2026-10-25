package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatRightsEditActivity$$ExternalSyntheticLambda31 implements RequestDelegate {
    public final /* synthetic */ ChatRightsEditActivity f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;

    public /* synthetic */ ChatRightsEditActivity$$ExternalSyntheticLambda31(ChatRightsEditActivity r1, TwoStepVerificationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatRightsEditActivity.m8400$r8$lambda$eqDPLYl0psKwBEaDG6Fixa_TB0(this.f$0, this.f$1, r2, r3);
    }
}
