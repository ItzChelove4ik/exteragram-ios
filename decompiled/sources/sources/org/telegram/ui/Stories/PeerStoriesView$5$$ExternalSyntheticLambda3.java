package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$5$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass5 f$0;
    public final /* synthetic */ StoryViewer f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;

    public /* synthetic */ PeerStoriesView$5$$ExternalSyntheticLambda3(PeerStoriesView.AnonymousClass5 r1, StoryViewer r2, Theme.ResourcesProvider r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        PeerStoriesView.AnonymousClass5.m19599$r8$lambda$1E4t2u0dKMbrMZ7UFmbteTV894(this.f$0, this.f$1, this.f$2, (TLRPC.InputStickerSet) r3);
    }
}
