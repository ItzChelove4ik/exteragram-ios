package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersDialogs$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.Document f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ Object f$5;
    public final /* synthetic */ TLRPC.TL_stickers_addStickerToSet f$6;

    public /* synthetic */ StickersDialogs$$ExternalSyntheticLambda11(AlertDialog r1, TLObject r2, int r3, TLRPC.Document r4, TLRPC.TL_error r5, Object r6, TLRPC.TL_stickers_addStickerToSet r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersDialogs.$r8$lambda$PWfBenhYVsMMCsELCmX85DIeeW0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
