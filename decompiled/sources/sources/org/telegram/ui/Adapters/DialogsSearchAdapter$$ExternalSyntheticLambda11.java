package org.telegram.ui.Adapters;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda11 implements Utilities.Callback {
    public final /* synthetic */ DialogsSearchAdapter f$0;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda11(DialogsSearchAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.openBotApp((TLRPC.User) r1);
    }
}
