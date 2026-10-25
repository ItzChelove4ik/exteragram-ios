package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda69 implements MessagesStorage.StringCallback {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda69(SharedMediaLayout r1, TL_stories.StoryItem r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.StringCallback
    public final void run(String r2) {
        SharedMediaLayout.$r8$lambda$FgdacGBLGmdJkd1VpFzlIum1H_Q(this.f$0, this.f$1, r2);
    }
}
