package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$$ExternalSyntheticLambda126 implements RequestDelegate {
    public final /* synthetic */ StoryRecorder f$0;
    public final /* synthetic */ TL_stories.TL_startLive f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ StoryRecorder$$ExternalSyntheticLambda126(StoryRecorder r1, TL_stories.TL_startLive r2, boolean r3, long r4, boolean r6, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        StoryRecorder.$r8$lambda$nWtCuO5dKXPuupBiIN1QS3VSbz8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
