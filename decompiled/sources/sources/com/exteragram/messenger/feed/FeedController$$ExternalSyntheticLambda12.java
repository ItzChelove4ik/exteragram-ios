package com.exteragram.messenger.feed;

import com.exteragram.messenger.feed.FeedController;
import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class FeedController$$ExternalSyntheticLambda12 implements FeedController.ChannelsCallback {
    public final /* synthetic */ FeedController f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ FeedController$$ExternalSyntheticLambda12(FeedController r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.exteragram.messenger.feed.FeedController.ChannelsCallback
    public final void onChannels(ArrayList r7, int r8, boolean r9, int r10) {
        FeedController.$r8$lambda$XtHUOrwV_3jeF0i3oWnMcUDZGIU(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
