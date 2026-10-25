package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda20 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda20(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        BoostRepository.$r8$lambda$TOm6ENsklfEHu9RpiMaLZC8JNf0(this.f$0, r1, r2);
    }
}
