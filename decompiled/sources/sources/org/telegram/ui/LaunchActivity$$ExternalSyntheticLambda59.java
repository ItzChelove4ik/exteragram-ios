package org.telegram.ui;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda59 implements LocationActivity.LocationActivityDelegate {
    public final /* synthetic */ HashMap f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda59(HashMap r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.LocationActivity.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r9, int r10, boolean r11, int r12, long r13) {
        LaunchActivity.$r8$lambda$yhwxFTlNxIBKDvax4b0H2IsC3Xk(this.f$0, this.f$1, r9, r10, r11, r12, r13);
    }
}
