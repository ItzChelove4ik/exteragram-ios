package org.telegram.ui.Adapters;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsAdapter$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ DialogsAdapter f$0;

    public /* synthetic */ DialogsAdapter$$ExternalSyntheticLambda3(DialogsAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.onOpenBot((TLRPC.User) r1);
    }
}
