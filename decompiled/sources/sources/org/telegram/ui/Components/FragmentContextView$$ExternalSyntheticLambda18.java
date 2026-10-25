package org.telegram.ui.Components;

import org.telegram.messenger.LocationController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LocationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class FragmentContextView$$ExternalSyntheticLambda18 implements LocationActivity.LocationActivityDelegate {
    public final /* synthetic */ LocationController.SharingLocationInfo f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ FragmentContextView$$ExternalSyntheticLambda18(LocationController.SharingLocationInfo r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.LocationActivity.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r10, int r11, boolean r12, int r13, long r14) {
        FragmentContextView.$r8$lambda$CIIysyeWlPLOB0ZuV7WEd25RwO8(this.f$0, this.f$1, r10, r11, r12, r13, r14);
    }
}
