package org.telegram.ui;

import org.telegram.messenger.ImageReceiver;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda24 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ LocationActivity.LiveLocation f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda24(LocationActivity r1, LocationActivity.LiveLocation r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r7, boolean r8, boolean r9, boolean r10) {
        LocationActivity.m15711$r8$lambda$P_pH6FSGKWla3zrkzwZomELzM(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
