package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatEditActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditActivity$1$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ChatEditActivity.AnonymousClass1 f$0;

    public /* synthetic */ ChatEditActivity$1$$ExternalSyntheticLambda0(ChatEditActivity.AnonymousClass1 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ChatEditActivity.AnonymousClass1.m8290$r8$lambda$uwwtglORI853ZBmkCiNPzsIRFw(this.f$0, r1, r2);
    }
}
