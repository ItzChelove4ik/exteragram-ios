package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda11 implements Utilities.Callback {
    public final /* synthetic */ StoriesController f$0;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda11(StoriesController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.destroyStoryList((StoriesController.StoriesList) r1);
    }
}
