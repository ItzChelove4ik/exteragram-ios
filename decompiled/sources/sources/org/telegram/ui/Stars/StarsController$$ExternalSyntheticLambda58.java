package org.telegram.ui.Stars;

import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda58 implements NotificationCenter.NotificationCenterDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ NotificationCenter.NotificationCenterDelegate[] f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda58(StarsController r1, boolean[] r2, long r3, NotificationCenter.NotificationCenterDelegate[] r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public final void didReceivedNotification(int r10, int r11, Object[] r12) {
        StarsController.$r8$lambda$EVfuKGOwIrJsEskQfh3TxrejZUw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11, r12);
    }
}
