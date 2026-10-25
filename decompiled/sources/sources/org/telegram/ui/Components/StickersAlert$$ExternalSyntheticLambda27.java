package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda27 implements RequestDelegate {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ TLRPC.TL_messages_getAttachedStickers f$1;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda27(StickersAlert r1, TLRPC.TL_messages_getAttachedStickers r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StickersAlert.$r8$lambda$O9Zju3ZVsbkcYVyqKji7QS9rJ1s(this.f$0, this.f$1, r2, r3);
    }
}
