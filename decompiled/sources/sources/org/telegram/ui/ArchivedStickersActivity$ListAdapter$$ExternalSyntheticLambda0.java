package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ArchivedStickersActivity;
import org.telegram.ui.Cells.ArchivedStickerSetCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArchivedStickersActivity$ListAdapter$$ExternalSyntheticLambda0 implements ArchivedStickerSetCell.OnCheckedChangeListener {
    public final /* synthetic */ ArchivedStickersActivity.ListAdapter f$0;
    public final /* synthetic */ TLRPC.StickerSetCovered f$1;

    public /* synthetic */ ArchivedStickersActivity$ListAdapter$$ExternalSyntheticLambda0(ArchivedStickersActivity.ListAdapter r1, TLRPC.StickerSetCovered r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Cells.ArchivedStickerSetCell.OnCheckedChangeListener
    public final void onCheckedChanged(ArchivedStickerSetCell r2, boolean r3) {
        ArchivedStickersActivity.ListAdapter.m5983$r8$lambda$4sWeuujqpkjg6L8nAKYKItk9VA(this.f$0, this.f$1, r2, r3);
    }
}
