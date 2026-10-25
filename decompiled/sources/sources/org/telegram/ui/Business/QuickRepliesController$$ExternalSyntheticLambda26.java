package org.telegram.ui.Business;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class QuickRepliesController$$ExternalSyntheticLambda26 implements RequestDelegate {
    public final /* synthetic */ QuickRepliesController f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ TLRPC.TL_messages_sendQuickReplyMessages f$2;

    public /* synthetic */ QuickRepliesController$$ExternalSyntheticLambda26(QuickRepliesController r1, ArrayList r2, TLRPC.TL_messages_sendQuickReplyMessages r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        QuickRepliesController.m6402$r8$lambda$Lt4gBLlWHHkvc0tIJsWBR12Vs(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
