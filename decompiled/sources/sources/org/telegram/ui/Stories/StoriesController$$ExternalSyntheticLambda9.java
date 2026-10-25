package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TL_stories.TL_stories_getAllStories f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda9(StoriesController r1, boolean r2, TL_stories.TL_stories_getAllStories r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StoriesController.m19682$r8$lambda$Epwi9ioz3XePFuBETyXHYtHooY(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
