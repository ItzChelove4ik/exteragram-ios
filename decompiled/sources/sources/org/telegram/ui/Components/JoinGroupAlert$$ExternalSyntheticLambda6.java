package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinGroupAlert$$ExternalSyntheticLambda6 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ JoinGroupAlert f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ JoinGroupAlert$$ExternalSyntheticLambda6(JoinGroupAlert r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r2) {
        return JoinGroupAlert.$r8$lambda$lpo4j0RhrARlKJQmuKHwR2XGaWo(this.f$0, this.f$1, r2);
    }
}
