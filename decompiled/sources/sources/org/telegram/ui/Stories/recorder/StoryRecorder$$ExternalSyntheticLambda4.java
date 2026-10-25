package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ StoryRecorder f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_stories.TL_startLive f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ StoryRecorder$$ExternalSyntheticLambda4(StoryRecorder r1, TLObject r2, TL_stories.TL_startLive r3, boolean r4, long r5, boolean r7, TLRPC.TL_error r8, Runnable r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoryRecorder.$r8$lambda$C3qGE0yIMp_lnIvXYp9oPrAOX7k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
