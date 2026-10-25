package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda398 implements Utilities.Callback {
    public final /* synthetic */ Browser.Progress f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda398(Browser.Progress r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatActivity.$r8$lambda$ywvUJdfEqA15ZBoTlbgj01un05Y(this.f$0, this.f$1, (TLRPC.User) r2);
    }
}
