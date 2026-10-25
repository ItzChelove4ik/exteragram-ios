package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.Components.HorizontalRoundTabsLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class HorizontalRoundTabsLayout$Factory$$ExternalSyntheticLambda0 implements MessagesStorage.IntCallback {
    public final /* synthetic */ UItem f$0;
    public final /* synthetic */ HorizontalRoundTabsLayout f$1;

    public /* synthetic */ HorizontalRoundTabsLayout$Factory$$ExternalSyntheticLambda0(UItem r1, HorizontalRoundTabsLayout r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r2) {
        HorizontalRoundTabsLayout.Factory.$r8$lambda$fia7JWtwHAnhE1ryYsQYOVrfwjE(this.f$0, this.f$1, r2);
    }
}
