package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda81 implements Utilities.Callback {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda81(SharedMediaLayout r1, TL_stories.StoryItem r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        SharedMediaLayout.$r8$lambda$U_EFxz4MB2wUE7hiT2rs6Wq_Klo(this.f$0, this.f$1, (StoriesController.StoryAlbum) r2);
    }
}
