package org.telegram.ui.Stories;

import android.view.View;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.StoriesUtilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ StoriesUtilities.UserStoriesLoadOperation f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ View f$2;
    public final /* synthetic */ StoriesUtilities.AvatarStoryParams f$3;
    public final /* synthetic */ MessagesController f$4;

    public /* synthetic */ StoriesUtilities$UserStoriesLoadOperation$$ExternalSyntheticLambda0(StoriesUtilities.UserStoriesLoadOperation r1, long r2, View r4, StoriesUtilities.AvatarStoryParams r5, MessagesController r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        StoriesUtilities.UserStoriesLoadOperation.$r8$lambda$yvxfYkylhwAW7_LLM5yj5Oc9gjo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
