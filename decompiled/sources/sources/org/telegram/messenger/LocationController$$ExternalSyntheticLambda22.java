package org.telegram.messenger;

import org.telegram.messenger.LocationController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class LocationController$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ LocationController f$0;
    public final /* synthetic */ LocationController.SharingLocationInfo f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ TLRPC.TL_messages_editMessage f$3;

    public /* synthetic */ LocationController$$ExternalSyntheticLambda22(LocationController r1, LocationController.SharingLocationInfo r2, int[] r3, TLRPC.TL_messages_editMessage r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        LocationController.m4249$r8$lambda$NUWJvNuWIpVDCz8c2qSV3OG4Zc(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
