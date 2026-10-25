package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda23 implements ChatAttachAlertLocationLayout.LocationActivityDelegate {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda23(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertLocationLayout.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        ChatAttachAlertPollLayout.$r8$lambda$bo6H5FvUr1CvT4Cd5cGqZbGfeGo(this.f$0, r1, r2, r3, r4, r5);
    }
}
