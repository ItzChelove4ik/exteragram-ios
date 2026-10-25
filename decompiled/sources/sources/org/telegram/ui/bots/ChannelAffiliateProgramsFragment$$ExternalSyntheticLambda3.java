package org.telegram.ui.bots;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ TL_payments.connectedBotStarRef f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda3(TL_payments.connectedBotStarRef r1, BottomSheet r2, Theme.ResourcesProvider r3, TLRPC.User r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelAffiliateProgramsFragment.$r8$lambda$SMHHNYwLqpSUSV1onZnaUGrpXFo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
