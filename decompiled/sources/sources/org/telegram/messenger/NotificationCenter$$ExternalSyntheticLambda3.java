package org.telegram.messenger;

import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class NotificationCenter$$ExternalSyntheticLambda3 implements NotificationCenter.NotificationCenterDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ NotificationCenter$$ExternalSyntheticLambda3(int r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public final void didReceivedNotification(int r2, int r3, Object[] r4) {
        NotificationCenter.$r8$lambda$VGHAYRtonj4FbETBz8XLZzMdH_8(this.f$0, this.f$1, r2, r3, r4);
    }
}
