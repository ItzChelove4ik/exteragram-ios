package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda1(Utilities.Callback r1, MessagesController r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        BoostRepository.$r8$lambda$sUyLqFnw5T8JlloVbQXE6X66xhk(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
