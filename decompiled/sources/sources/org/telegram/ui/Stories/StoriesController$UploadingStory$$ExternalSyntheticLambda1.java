package org.telegram.ui.Stories;

import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$UploadingStory$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ StoriesController.UploadingStory f$0;

    public /* synthetic */ StoriesController$UploadingStory$$ExternalSyntheticLambda1(StoriesController.UploadingStory r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.cleanup();
    }
}
