package com.exteragram.messenger.feed;

import com.exteragram.messenger.feed.FeedTimelineLoader;
import java.util.HashSet;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class FeedController$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ FeedController f$0;
    public final /* synthetic */ FeedConfig f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ Utilities.Callback f$5;
    public final /* synthetic */ HashSet f$6;
    public final /* synthetic */ HashSet f$7;
    public final /* synthetic */ FeedTimelineLoader.Cursor f$8;
    public final /* synthetic */ FeedTimelineLoader.Cursor f$9;

    public /* synthetic */ FeedController$$ExternalSyntheticLambda13(FeedController r1, FeedConfig r2, int r3, int r4, int r5, Utilities.Callback r6, HashSet r7, HashSet r8, FeedTimelineLoader.Cursor r9, FeedTimelineLoader.Cursor r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FeedController.$r8$lambda$nhEXTMi2wPg6Y_g5oHB6wB1SLrY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9);
    }
}
