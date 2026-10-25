package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$GiftsCollections$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ StarsController.GiftsCollections f$0;

    public /* synthetic */ StarsController$GiftsCollections$$ExternalSyntheticLambda5(StarsController.GiftsCollections r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StarsController.GiftsCollections.$r8$lambda$8QrrZoQF4CmusYMir7jaH_2YsGU(this.f$0, r1, r2);
    }
}
