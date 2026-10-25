package org.telegram.messenger;

import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileLoader$$ExternalSyntheticLambda19 implements NotificationCenter.NotificationCenterDelegate {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ FileLoader$$ExternalSyntheticLambda19(String r1, Utilities.Callback r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public final void didReceivedNotification(int r7, int r8, Object[] r9) {
        FileLoader.m4034$r8$lambda$gKJsZClCE1qk51wAobZ3vhvknU(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
