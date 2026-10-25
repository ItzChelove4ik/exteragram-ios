package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TL_stories.TL_updateStory f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda8(StoriesController r1, long r2, TL_stories.TL_updateStory r4, TLRPC.User r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesController.$r8$lambda$Hm5GPhEUlFuv5gQEaY200LPxOhY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
