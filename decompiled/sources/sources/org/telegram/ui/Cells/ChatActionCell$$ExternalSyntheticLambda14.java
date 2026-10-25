package org.telegram.ui.Cells;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActionCell$$ExternalSyntheticLambda14 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActionCell f$0;
    public final /* synthetic */ BaseFragment f$1;

    public /* synthetic */ ChatActionCell$$ExternalSyntheticLambda14(ChatActionCell r1, BaseFragment r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ChatActionCell.$r8$lambda$3RNgYrTMc5lGds8qF9VCYiEorOk(this.f$0, this.f$1, (TLRPC.Updates) r2, (TLRPC.TL_error) r3);
    }
}
