package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda23 implements RequestDelegate {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TL_stories.PeerStories f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda23(StoriesController r1, long r2, boolean r4, TL_stories.PeerStories r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        StoriesController.$r8$lambda$vjQ5tOourCa7hLmJTYIqzyFPNX0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11);
    }
}
