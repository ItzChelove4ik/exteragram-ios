package com.exteragram.messenger.feed;

import com.exteragram.messenger.feed.FeedTimelineLoader;
import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedController$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ FeedController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ FeedTimelineLoader.ChannelEnumeration f$2;
    public final /* synthetic */ FeedConfig f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Utilities.Callback f$6;
    public final /* synthetic */ FeedTimelineLoader.WindowPage f$7;
    public final /* synthetic */ ArrayList f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ FeedController$$ExternalSyntheticLambda23(FeedController r1, int r2, FeedTimelineLoader.ChannelEnumeration r3, FeedConfig r4, int r5, int r6, Utilities.Callback r7, FeedTimelineLoader.WindowPage r8, ArrayList r9, boolean r10) {
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
        FeedController.m1416$r8$lambda$zrzKOoigMK7OCGcMLwEe5eQuls(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9);
    }
}
