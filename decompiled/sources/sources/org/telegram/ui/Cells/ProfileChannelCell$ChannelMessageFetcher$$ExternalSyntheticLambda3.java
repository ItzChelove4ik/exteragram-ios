package org.telegram.ui.Cells;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ProfileChannelCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ProfileChannelCell.ChannelMessageFetcher f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessagesStorage f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ ProfileChannelCell$ChannelMessageFetcher$$ExternalSyntheticLambda3(ProfileChannelCell.ChannelMessageFetcher r1, int r2, MessagesStorage r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ProfileChannelCell.ChannelMessageFetcher.$r8$lambda$_5L58a9DKQFpxgWPt647q27VuP4(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
