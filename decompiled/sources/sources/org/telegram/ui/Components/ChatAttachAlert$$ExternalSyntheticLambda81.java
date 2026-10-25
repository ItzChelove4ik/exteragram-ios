package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$$ExternalSyntheticLambda81 implements RequestDelegate {
    public final /* synthetic */ ChatAttachAlert f$0;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$1;

    public /* synthetic */ ChatAttachAlert$$ExternalSyntheticLambda81(ChatAttachAlert r1, TLRPC.TL_attachMenuBot r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatAttachAlert.$r8$lambda$yJRapRRYU4hG9RVTIx1EAzSzGeM(this.f$0, this.f$1, r2, r3);
    }
}
