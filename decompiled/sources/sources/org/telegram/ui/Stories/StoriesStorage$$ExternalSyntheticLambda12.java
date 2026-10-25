package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesStorage$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TL_stories.TL_stories_allStories f$1;

    public /* synthetic */ StoriesStorage$$ExternalSyntheticLambda12(Utilities.Callback r1, TL_stories.TL_stories_allStories r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesStorage.$r8$lambda$Nbwyn0IjLAJPT0zX3tCmEyXD6q4(this.f$0, this.f$1);
    }
}
