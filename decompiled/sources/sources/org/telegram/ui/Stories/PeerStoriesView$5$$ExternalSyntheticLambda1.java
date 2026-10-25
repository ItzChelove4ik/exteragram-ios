package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$5$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PeerStoriesView.AnonymousClass5 f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;

    public /* synthetic */ PeerStoriesView$5$$ExternalSyntheticLambda1(PeerStoriesView.AnonymousClass5 r1, TLRPC.Document r2, Theme.ResourcesProvider r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PeerStoriesView.AnonymousClass5.$r8$lambda$jA1NwVM4jhaNIH8WCyicHYTLyZY(this.f$0, this.f$1, this.f$2);
    }
}
