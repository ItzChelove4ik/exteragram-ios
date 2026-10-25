package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.ViewPagerFixed;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollItemMenu$$ExternalSyntheticLambda14 implements Utilities.Callback {
    public final /* synthetic */ ViewPagerFixed f$0;

    public /* synthetic */ PollItemMenu$$ExternalSyntheticLambda14(ViewPagerFixed r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.scrollToPosition(((Integer) r1).intValue());
    }
}
