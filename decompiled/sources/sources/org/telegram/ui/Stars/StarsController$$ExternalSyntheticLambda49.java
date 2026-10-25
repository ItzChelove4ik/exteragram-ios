package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda49 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda49(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StarsController.$r8$lambda$67sUnhKcsc6RgeV2cBQjiis7CxQ(this.f$0, r1, r2);
    }
}
