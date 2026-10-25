package org.telegram.ui.Business;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda7 implements LocationActivity.LocationActivityDelegate {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ org.telegram.ui.LocationActivity f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda7(LocationActivity r1, org.telegram.ui.LocationActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.LocationActivity.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r9, int r10, boolean r11, int r12, long r13) {
        LocationActivity.$r8$lambda$OSqn_qBZEMMRBIgI54ZRELWwBDg(this.f$0, this.f$1, r9, r10, r11, r12, r13);
    }
}
