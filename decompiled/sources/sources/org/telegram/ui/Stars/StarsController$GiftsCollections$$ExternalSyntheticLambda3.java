package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$GiftsCollections$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ StarsController.GiftsCollections f$0;
    public final /* synthetic */ TL_stars.TL_starGiftCollection f$1;
    public final /* synthetic */ StarsController.GiftsList f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ StarsController$GiftsCollections$$ExternalSyntheticLambda3(StarsController.GiftsCollections r1, TL_stars.TL_starGiftCollection r2, StarsController.GiftsList r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarsController.GiftsCollections.m19147$r8$lambda$gfhZCB5yxKoqgvILqFd91Z7XSY(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
