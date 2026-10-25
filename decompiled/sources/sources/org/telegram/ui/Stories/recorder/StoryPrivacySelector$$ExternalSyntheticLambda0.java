package org.telegram.ui.Stories.recorder;

import java.util.HashSet;
import org.telegram.messenger.MessagesStorage;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacySelector$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ HashSet f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ StoryPrivacySelector$$ExternalSyntheticLambda0(MessagesStorage r1, HashSet r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoryPrivacySelector.$r8$lambda$1GeR3B71u4SYlnnRN3ZTI6oCWXk(this.f$0, this.f$1, this.f$2);
    }
}
