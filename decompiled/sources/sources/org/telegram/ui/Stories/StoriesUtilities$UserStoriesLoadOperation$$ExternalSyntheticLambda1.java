package org.telegram.ui.Stories;

import android.view.View;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ StoriesUtilities.UserStoriesLoadOperation f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ View f$3;
    public final /* synthetic */ StoriesUtilities.AvatarStoryParams f$4;
    public final /* synthetic */ MessagesController f$5;

    public /* synthetic */ StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda1(StoriesUtilities.UserStoriesLoadOperation r1, TLObject r2, long r3, View r5, StoriesUtilities.AvatarStoryParams r6, MessagesController r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesUtilities.UserStoriesLoadOperation.m19734$r8$lambda$YBOwZ9NQtDixOdGcEqhgZcQUgk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
