package org.telegram.ui.Stories;

import java.util.Comparator;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes3.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ StoriesController f$0;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda0(StoriesController r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return StoriesController.m19691$r8$lambda$Yv74YCSPWPBJ8yZk_3h0opLQF4(this.f$0, (TL_stories.PeerStories) r1, (TL_stories.PeerStories) r2);
    }
}
