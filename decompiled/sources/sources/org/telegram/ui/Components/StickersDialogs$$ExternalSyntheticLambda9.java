package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersDialogs$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ Object f$3;
    public final /* synthetic */ TLRPC.TL_stickers_addStickerToSet f$4;

    public /* synthetic */ StickersDialogs$$ExternalSyntheticLambda9(AlertDialog r1, int r2, TLRPC.Document r3, Object r4, TLRPC.TL_stickers_addStickerToSet r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StickersDialogs.m13136$r8$lambda$eyq0pphyCX1kZ1qcUn4PyDQmQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
