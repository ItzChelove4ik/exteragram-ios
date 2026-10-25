package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsBotsAdapter$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ DialogsBotsAdapter f$0;

    public /* synthetic */ DialogsBotsAdapter$$ExternalSyntheticLambda1(DialogsBotsAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.openBot((TLRPC.User) r1);
    }
}
