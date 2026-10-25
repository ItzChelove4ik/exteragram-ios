package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda112 implements LocationActivity.LocationActivityDelegate {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda112(int[] r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.LocationActivity.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r10, int r11, boolean r12, int r13, long r14) {
        LaunchActivity.m15622$r8$lambda$bIDyl7tdS8jkk7wBLxJ9nb5UaE(this.f$0, this.f$1, r10, r11, r12, r13, r14);
    }
}
