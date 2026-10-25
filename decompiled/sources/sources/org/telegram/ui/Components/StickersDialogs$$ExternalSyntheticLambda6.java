package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersDialogs$$ExternalSyntheticLambda6 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ TLRPC.StickerSet f$1;

    public /* synthetic */ StickersDialogs$$ExternalSyntheticLambda6(Runnable r1, TLRPC.StickerSet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        StickersDialogs.m13134$r8$lambda$YO0w2MC46Ze4CtRzzGkru9YLik(this.f$0, this.f$1, r2, r3);
    }
}
