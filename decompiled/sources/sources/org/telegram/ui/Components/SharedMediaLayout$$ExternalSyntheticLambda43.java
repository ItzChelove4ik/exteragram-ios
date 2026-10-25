package org.telegram.ui.Components;

import java.util.HashSet;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda43 implements Utilities.Callback {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ HashSet f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;
    public final /* synthetic */ ItemOptions f$3;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda43(SharedMediaLayout r1, HashSet r2, TL_stories.StoryItem r3, ItemOptions r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        SharedMediaLayout.m12777$r8$lambda$mRxlglG_OU36UoNr235xpbRC0(this.f$0, this.f$1, this.f$2, this.f$3, (StoriesController.StoryAlbum) r4);
    }
}
