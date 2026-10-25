package org.telegram.ui.Stories.recorder;

import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$$ExternalSyntheticLambda29 implements Runnable {
    public final /* synthetic */ StoryRecorder.WindowView f$0;

    public /* synthetic */ StoryRecorder$$ExternalSyntheticLambda29(StoryRecorder.WindowView r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.cancelGestures();
    }
}
