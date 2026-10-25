package org.telegram.ui.Stories;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Cells.ReactedUserHolderView;
import org.telegram.ui.Stories.SelfStoryViewsPage;

/* loaded from: classes7.dex */
public final /* synthetic */ class SelfStoryViewsPage$4$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SelfStoryViewsPage.AnonymousClass4 f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ ReactedUserHolderView f$4;
    public final /* synthetic */ TL_stories.StoryView f$5;

    public /* synthetic */ SelfStoryViewsPage$4$$ExternalSyntheticLambda1(SelfStoryViewsPage.AnonymousClass4 r1, MessagesController r2, TLRPC.User r3, String r4, ReactedUserHolderView r5, TL_stories.StoryView r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SelfStoryViewsPage.AnonymousClass4.$r8$lambda$dT0rGYOjTZm3f9Q4VW6lefiz1Kk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
