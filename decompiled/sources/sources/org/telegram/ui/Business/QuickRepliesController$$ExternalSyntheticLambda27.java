package org.telegram.ui.Business;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class QuickRepliesController$$ExternalSyntheticLambda27 implements Runnable {
    public final /* synthetic */ QuickRepliesController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ TLRPC.TL_messages_sendQuickReplyMessages f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ QuickRepliesController$$ExternalSyntheticLambda27(QuickRepliesController r1, TLObject r2, ArrayList r3, TLRPC.TL_messages_sendQuickReplyMessages r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QuickRepliesController.$r8$lambda$IZyd_9A10W9tQVaRzAR4af8QwKQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
