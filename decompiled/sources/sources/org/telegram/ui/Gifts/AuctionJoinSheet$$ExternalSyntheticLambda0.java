package org.telegram.ui.Gifts;

import android.content.Context;
import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AuctionJoinSheet$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ AuctionJoinSheet$$ExternalSyntheticLambda0(Context r1, Theme.ResourcesProvider r2, int r3, long r4, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r9, Object r10) {
        AuctionJoinSheet.m14878$r8$lambda$hP_lRUuYCaP75u8lhXvsZQZlQw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (GiftAuctionController.Auction) r9, (TLRPC.TL_error) r10);
    }
}
