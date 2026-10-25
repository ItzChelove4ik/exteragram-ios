package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$GiftsCollections$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ StarsController.GiftsCollections f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_stars.TL_starGiftCollection f$2;
    public final /* synthetic */ StarsController.GiftsList f$3;
    public final /* synthetic */ Utilities.Callback f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;

    public /* synthetic */ StarsController$GiftsCollections$$ExternalSyntheticLambda6(StarsController.GiftsCollections r1, TLObject r2, TL_stars.TL_starGiftCollection r3, StarsController.GiftsList r4, Utilities.Callback r5, TLRPC.TL_error r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.GiftsCollections.$r8$lambda$pzK0WKds46Ub4gQRY8uK7BXFA14(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
