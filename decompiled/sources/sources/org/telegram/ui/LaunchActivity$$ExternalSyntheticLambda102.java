package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda102 implements Utilities.Callback {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ Long f$2;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda102(LaunchActivity r1, Runnable r2, Long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        LaunchActivity.$r8$lambda$zfytYymF27Pi3oeVL54h612FKB8(this.f$0, this.f$1, this.f$2, (TL_stories.StoryItem) r3);
    }
}
