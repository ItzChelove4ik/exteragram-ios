package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda28 implements RequestDelegate {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ TLRPC.TL_messages_getAttachedStickers f$2;
    public final /* synthetic */ RequestDelegate f$3;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda28(StickersAlert r1, Object r2, TLRPC.TL_messages_getAttachedStickers r3, RequestDelegate r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StickersAlert.$r8$lambda$pJi3sfhgz2XNAmUpPEdTqOQvLgI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
