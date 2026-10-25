package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.StoryCaptionView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryCaptionView$Panel$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ StoryCaptionView.Panel f$0;

    public /* synthetic */ StoryCaptionView$Panel$$ExternalSyntheticLambda0(StoryCaptionView.Panel r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        StoryCaptionView.Panel.m19744$r8$lambda$LAEuqMlk17JD4dLmra6ZTWZsZU(this.f$0, (TL_stories.StoryItem) r1);
    }
}
