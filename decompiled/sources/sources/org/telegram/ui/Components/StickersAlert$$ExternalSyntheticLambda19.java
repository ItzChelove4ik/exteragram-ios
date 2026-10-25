package org.telegram.ui.Components;

import org.telegram.messenger.MediaDataController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda19 implements RequestDelegate {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ MediaDataController f$1;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda19(StickersAlert r1, MediaDataController r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StickersAlert.m13077$r8$lambda$QtWSB1HfoSSUHb8XnHNR7L25kU(this.f$0, this.f$1, r2, r3);
    }
}
