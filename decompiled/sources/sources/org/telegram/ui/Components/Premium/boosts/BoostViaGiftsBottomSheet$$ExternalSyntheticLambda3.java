package org.telegram.ui.Components.Premium.boosts;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Premium.boosts.cells.ChatCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostViaGiftsBottomSheet$$ExternalSyntheticLambda3 implements ChatCell.ChatDeleteListener {
    public final /* synthetic */ BoostViaGiftsBottomSheet f$0;

    public /* synthetic */ BoostViaGiftsBottomSheet$$ExternalSyntheticLambda3(BoostViaGiftsBottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.ChatCell.ChatDeleteListener
    public final void onChatDeleted(TLRPC.Chat r1) {
        BoostViaGiftsBottomSheet.m12099$r8$lambda$595nZjE6JvxZvR81eC23yK5j8(this.f$0, r1);
    }
}
