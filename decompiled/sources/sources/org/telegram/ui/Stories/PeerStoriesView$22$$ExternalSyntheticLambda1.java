package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$22$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass22 f$0;
    public final /* synthetic */ TLRPC.TL_document f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Object f$3;

    public /* synthetic */ PeerStoriesView$22$$ExternalSyntheticLambda1(PeerStoriesView.AnonymousClass22 r1, TLRPC.TL_document r2, String r3, Object r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PeerStoriesView.AnonymousClass22.$r8$lambda$vY6yKBhDWZfaoJJyEiM2JPsie2Y(this.f$0, this.f$1, this.f$2, this.f$3, (Long) r4);
    }
}
