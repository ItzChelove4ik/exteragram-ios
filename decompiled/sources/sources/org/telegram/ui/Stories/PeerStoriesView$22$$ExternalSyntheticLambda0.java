package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$22$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass22 f$0;
    public final /* synthetic */ TLRPC.BotInlineResult f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ PeerStoriesView$22$$ExternalSyntheticLambda0(PeerStoriesView.AnonymousClass22 r1, TLRPC.BotInlineResult r2, boolean r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PeerStoriesView.AnonymousClass22.m19596$r8$lambda$r5IF4jpaZ2_p5krM2IpskTR_Tk(this.f$0, this.f$1, this.f$2, this.f$3, (Long) r4);
    }
}
