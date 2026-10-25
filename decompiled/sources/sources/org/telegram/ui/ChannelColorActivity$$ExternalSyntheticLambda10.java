package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelColorActivity$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ ChannelColorActivity f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ChannelColorActivity$$ExternalSyntheticLambda10(ChannelColorActivity r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChannelColorActivity.$r8$lambda$p39cUiUWjfJmDz_GMSJN_Nx8Gr8(this.f$0, this.f$1, r2, r3);
    }
}
