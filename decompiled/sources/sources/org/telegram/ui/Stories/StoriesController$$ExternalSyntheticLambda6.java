package org.telegram.ui.Stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ StoriesController f$0;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda6(StoriesController r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateStoryLimit();
    }
}
