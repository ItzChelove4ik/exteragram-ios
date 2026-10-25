package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ StoryRecorder f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.InputGroupCall f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ StoryRecorder$$ExternalSyntheticLambda5(StoryRecorder r1, boolean r2, TL_stories.StoryItem r3, long r4, TLRPC.InputGroupCall r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoryRecorder.$r8$lambda$xHpFCbNE0wkUYJnFCNjwxPxIevA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
