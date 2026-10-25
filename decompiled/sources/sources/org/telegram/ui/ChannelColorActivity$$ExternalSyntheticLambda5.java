package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelColorActivity$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ ChannelColorActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ ChannelColorActivity$$ExternalSyntheticLambda5(ChannelColorActivity r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChannelColorActivity.$r8$lambda$yR62fJLXqQavXTcV_51axQtJEvQ(this.f$0, this.f$1, (TL_stories.TL_premium_boostsStatus) r2);
    }
}
