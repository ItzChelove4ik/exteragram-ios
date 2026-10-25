package org.telegram.ui;

import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileStoriesCollectionTabs$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ StoriesController.StoriesCollections f$0;

    public /* synthetic */ ProfileStoriesCollectionTabs$$ExternalSyntheticLambda3(StoriesController.StoriesCollections r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.sendOrder();
    }
}
