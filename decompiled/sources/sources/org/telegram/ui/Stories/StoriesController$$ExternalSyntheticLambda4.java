package org.telegram.ui.Stories;

import org.telegram.ui.Stories.recorder.DraftsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ DraftsController f$0;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda4(DraftsController r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.cleanup();
    }
}
