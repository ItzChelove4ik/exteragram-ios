package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$4$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback4 f$0;

    public /* synthetic */ MessagesController$4$$ExternalSyntheticLambda0(Utilities.Callback4 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagesController.AnonymousClass4.$r8$lambda$PYTsfFCelfPN1oIWiKkj850qgKU(this.f$0, r1, r2);
    }
}
