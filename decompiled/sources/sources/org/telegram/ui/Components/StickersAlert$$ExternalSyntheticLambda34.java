package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda34 implements Runnable {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_messages_getAttachedStickers f$3;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda34(StickersAlert r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_messages_getAttachedStickers r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersAlert.m13076$r8$lambda$QfZ47zoqnzTBBGgD_0G2CkSno(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
