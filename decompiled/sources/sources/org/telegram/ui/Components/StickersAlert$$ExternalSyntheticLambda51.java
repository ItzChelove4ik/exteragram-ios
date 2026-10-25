package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda51 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda51(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StickersAlert.$r8$lambda$OSkWdZmNp7F9jNO1iDb0kTEXqb4(this.f$0, r1, r2);
    }
}
